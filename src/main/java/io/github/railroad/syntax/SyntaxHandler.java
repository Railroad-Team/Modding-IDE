package io.github.railroad.syntax;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.Subscription;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;

import static io.github.railroad.syntax.SyntaxManager.getLanguageFromExtension;
import static io.github.railroad.syntax.SyntaxManager.initSyntax;

/**
 *
 */
public class SyntaxHandler extends Application {
	private final CodeArea codeArea = new CodeArea();
	private final ExecutorService executor = Executors.newSingleThreadExecutor();

	// Yes, static abuse. We will pass this in when implementing
	private static Scene scene;

	@SuppressWarnings("unused") // We won't need this when actually implementing
	@Override
	public void start(Stage primaryStage) {
		codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

		Subscription cleanupWhenDone = codeArea
				.multiPlainChanges()
				.successionEnds(Duration.ofMillis(500))
				.supplyTask(this::computeHighlightingAsync)
				.awaitLatest(codeArea.multiPlainChanges())
				.filterMap(t -> {
					if (t.isSuccess()) {
						return Optional.of(t.get());
					} else {
						t.getFailure().printStackTrace();
						return Optional.empty();
					}
				}).subscribe(this::applyHighlighting);

		// call when no longer need it: `cleanupWhenFinished.unsubscribe();`

		codeArea.replaceText(0, 0, "");

		Scene sceneObj = new Scene(new StackPane(new VirtualizedScrollPane<>(codeArea)), 600, 400);
		scene = sceneObj;
		primaryStage.setScene(sceneObj);
		primaryStage.setTitle("Java Keywords Demo");
		primaryStage.show();
	}

	@Override
	public void stop() {
		executor.shutdown();
	}

	// This throws some kinda exception but it clearly doesnt affect anything
	// TODO surround with a try catch or something, idk (or fix it)
	private static StyleSpans<Collection<String>> computeHighlighting(String text) {
		SyntaxObject syntax = getLanguageFromExtension("java", initSyntax());
		Matcher matcher = syntax.compiled.matcher(text);

		scene.getStylesheets().add("/assets/" + syntax.path + ".css"); // This CAN throw ConcurrentModificationException. Should probably do this when initialising.

		int lastKwEnd = 0;
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		while (matcher.find()) {
			String styleClass = syntax.expressions.values().stream()
					.filter(group -> matcher.group(group) != null)
					.findFirst()
					.orElse(null);

			spansBuilder
					.add(Collections.emptyList(), matcher.start() - lastKwEnd)
					.add(Collections.singleton(styleClass), matcher.end() - matcher.start());

			lastKwEnd = matcher.end();
		}

		spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
		return spansBuilder.create();
	}

	private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
		codeArea.setStyleSpans(0, highlighting);
	}

	private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
		var text = codeArea.getText();

		Task<StyleSpans<Collection<String>>> task = new Task<>() {
			@Override
			protected StyleSpans<Collection<String>> call() {
				return computeHighlighting(text);
			}
		};

		executor.execute(task);
		return task;
	}
}
