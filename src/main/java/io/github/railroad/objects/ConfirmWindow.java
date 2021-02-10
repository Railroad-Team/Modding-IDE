package io.github.railroad.objects;

import io.github.railroad.utility.UIUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

// TODO: Come up with a cleaner way of doing this. This code was rushed.
public final class ConfirmWindow {
	public static final Map<Stage, Boolean> ANSWER = new HashMap<>();

	public static boolean displayWindow(String title, String message) {
		final boolean result;
		final Stage window = new Stage();
		window.centerOnScreen();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(100);
		window.setResizable(false);

		final Label label = new Label(message);

		final Button yesBtn = UIUtils.createButton("Yes", event -> {
			ANSWER.put(window, true);
			window.close();
		});

		final Button noBtn = UIUtils.createButton("No", event -> {
			ANSWER.put(window, false);
			window.close();
		});

		final VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yesBtn, noBtn);
		layout.setAlignment(Pos.CENTER);

		final Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		result = ANSWER.get(window);
		ANSWER.remove(window);
		return result;
	}
}