package io.github.railroad.debugger.syntax;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;

import io.github.railroad.config.Configs;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SyntaxHandler extends Application {
	public static void main(String[] args) {
		launch(args); // TODO Remove this later
	}

	private CodeArea codeArea;
	private ExecutorService executor;

	@SuppressWarnings("unused")
	@Override
	public void start(Stage primaryStage) {
		executor = Executors.newSingleThreadExecutor();
		codeArea = new CodeArea();
		codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
		Subscription cleanupWhenDone = codeArea.multiPlainChanges().successionEnds(Duration.ofMillis(500))
				.supplyTask(this::computeHighlightingAsync).awaitLatest(codeArea.multiPlainChanges()).filterMap(t -> {
					if (t.isSuccess()) {
						return Optional.of(t.get());
					} else {
						t.getFailure().printStackTrace();
						return Optional.empty();
					}
				}).subscribe(this::applyHighlighting);

		// call when no longer need it: `cleanupWhenFinished.unsubscribe();`

		codeArea.replaceText(0, 0, "");

		Scene scene = new Scene(new StackPane(new VirtualizedScrollPane<>(codeArea)), 600, 400);
		scene.getStylesheets().add("java-keywords.css"); // Make this support config files
		primaryStage.setScene(scene);
		primaryStage.setTitle("Java Keywords Demo");
		primaryStage.show();
	}

	@Override
	public void stop() {
		executor.shutdown();
	}

	private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
		String text = codeArea.getText();
		Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
			@Override
			protected StyleSpans<Collection<String>> call() throws Exception {
				return computeHighlighting(text);
			}
		};
		executor.execute(task);
		return task;
	}

	private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
		codeArea.setStyleSpans(0, highlighting);
	}

	private static StyleSpans<Collection<String>> computeHighlighting(String text) {

		// TODO reference the main class
		SyntaxObject syntax = new Configs().syntax.getByExt("java");
		Matcher matcher = syntax.getCompiled().matcher(text);
		int lastKwEnd = 0;
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		while (matcher.find()) {

			// TODO make this automatic, to stop errors. All things need to be present in
			// syntax config right now.
			String styleClass = matcher.group("KEYWORD") != null ? "keyword"
					: matcher.group("STRING") != null ? "string"
							: matcher.group("FUNCTION") != null ? "function"
									: matcher.group("NUMBER") != null ? "number"
											: matcher.group("COMMENT") != null ? "comment"
													: matcher.group("CLASS") != null ? "class" : null;
			/* never happens */ assert styleClass != null;

			spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
			spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
			lastKwEnd = matcher.end();
		}
		spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
		return spansBuilder.create();
	}
}