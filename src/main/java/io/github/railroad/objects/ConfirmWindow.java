package io.github.railroad.objects;

import java.util.HashMap;
import java.util.Map;

import io.github.railroad.utility.UIUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static io.github.railroad.Railroad.darkMode;

// TODO: Come up with a cleaner way of doing this. This code was rushed.
public class ConfirmWindow {

	public static Map<Stage, Boolean> ANSWER = new HashMap<Stage, Boolean>();

	public static boolean displayWindow(String title, String message) {
		boolean result;
		Stage window = new Stage();
		window.centerOnScreen();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(100);
		window.setResizable(false);

		Label label = new Label(message);

		Button yesBtn = UIUtils.createButton("Yes", event -> {
			ANSWER.put(window, true);
			window.close();
		});

		Button noBtn = UIUtils.createButton("No", event -> {
			ANSWER.put(window, false);
			window.close();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yesBtn, noBtn);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		if (darkMode) {
			scene.getStylesheets().add("assets/styles/mode/darkmode.css");
		} else {
			scene.getStylesheets().add("assets/styles/mode/lightmode.css");
		}
		window.setScene(scene);
		window.showAndWait();
		result = ANSWER.get(window);
		ANSWER.remove(window);
		return result;
	}
}