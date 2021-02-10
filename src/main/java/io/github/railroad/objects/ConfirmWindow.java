package io.github.railroad.objects;

import io.github.railroad.utility.Components;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

import static io.github.railroad.utility.Components.ButtonBuilder.makeButton;

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

		final Button buttonYes = makeButton("Yes").action(event -> {
			ANSWER.put(window, true);
			window.close();
		}).get();
		final Button buttonNo = makeButton("No").action(event -> {
			ANSWER.put(window, false);
			window.close();
		}).get();
		final VBox layout = new VBox(10);
		layout.getChildren().addAll(label, buttonYes, buttonNo);
		layout.setAlignment(Pos.CENTER);

		final Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		result = ANSWER.get(window);
		ANSWER.remove(window);
		return result;
	}
}