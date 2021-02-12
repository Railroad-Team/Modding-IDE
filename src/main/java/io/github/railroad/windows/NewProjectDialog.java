package io.github.railroad.windows;

import java.util.Optional;

import io.github.railroad.config.Configs;
import io.github.railroad.utility.UIUtils;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class NewProjectDialog {

	// TODO make this not static.
	// WHY IS IT STATIC? WHY!?!
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean display() {
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle(Configs.INSTANCE.lang.get("dialog.new_project"));

		// Create button type
		dialog.getDialogPane().getButtonTypes().addAll(UIUtils.BUTTON_TYPE_CREATE, ButtonType.CANCEL);

		// Create grid
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 200, 10, 10));

		// Create Fields
		TextField projectName = new TextField();
		projectName.setPromptText(Configs.INSTANCE.lang.get("dialog.new_project.name"));
		ChoiceBox projectType = new ChoiceBox();
		projectType.setValue(Configs.INSTANCE.lang.get("dialog.new_project.forge_mod"));
		projectType.getItems().addAll(Configs.INSTANCE.lang.get("dialog.new_project.forge_mod"),
				Configs.INSTANCE.lang.get("dialog.new_project.fabric_mod"),
				Configs.INSTANCE.lang.get("dialog.new_project.java_project"));
		grid.add(new Label(Configs.INSTANCE.lang.get("dialog.new_project.name") + ":"), 0, 0);
		grid.add(projectName, 1, 0);
		grid.add(new Label(Configs.INSTANCE.lang.get("dialog.new_project.type") + ":"), 0, 1);
		grid.add(projectType, 1, 1);

		// Enable/Disable login button depending on whether a username was entered.
		Node createButton = dialog.getDialogPane().lookupButton(UIUtils.BUTTON_TYPE_CREATE);
		createButton.setDisable(true);
		projectName.textProperty().addListener((observable, oldValue, newValue) -> {
			createButton.setDisable(newValue.trim().isEmpty());
		});
		

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> projectName.requestFocus());

		// Convert the result to a username-password-pair when the login button is
		// clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == UIUtils.BUTTON_TYPE_CREATE) {
				return new Pair<>(projectName.getText(), (String) projectType.getValue());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
			System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
		});
		return false;
	}
}
