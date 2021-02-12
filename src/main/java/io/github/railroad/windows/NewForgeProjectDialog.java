package io.github.railroad.windows;

import java.util.Optional;

import io.github.railroad.config.Configs;
import io.github.railroad.moddedVersionFetcher.forge.ForgeVersionHelper;
import io.github.railroad.moddedVersionFetcher.game.MinecraftVersionHelper;
import io.github.railroad.utility.UIUtils;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class NewForgeProjectDialog {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void newDialogA(String modName) {
		// Setup Window
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle(Configs.INSTANCE.lang.get("dialog.new_forge") + " | " + modName);
		dialog.getDialogPane().getButtonTypes().addAll(UIUtils.BUTTON_TYPE_NEXT, ButtonType.CANCEL);

		// Create grid
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 200, 10, 10));

		// Create Input Fields
		TextField modId = new TextField();
		modId.setPromptText(Configs.INSTANCE.lang.get("dialog.new_forge.mod_id"));

		ChoiceBox projectType = new ChoiceBox();
		projectType.setValue(Configs.INSTANCE.lang.get("1.16.4"));
		projectType.getItems().addAll(MinecraftVersionHelper.getVersions(true));

		// Add Input Fields
		grid.add(new Label(Configs.INSTANCE.lang.get("dialog.new_forge.mod_id") + ":"), 0, 0);
		grid.add(modId, 1, 0);
		grid.add(new Label(Configs.INSTANCE.lang.get("dialog.new_forge.version") + ":"), 0, 1);
		grid.add(projectType, 1, 1);

		Node createButton = dialog.getDialogPane().lookupButton(UIUtils.BUTTON_TYPE_NEXT);
		createButton.setDisable(true);
		modId.textProperty().addListener((observable, oldValue, newValue) -> {
			createButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> modId.requestFocus());

		// Convert the result to a username-password-pair when the login button is
		// clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == UIUtils.BUTTON_TYPE_NEXT) {
				return new Pair<>(modId.getText(), (String) projectType.getValue());
			}
			return null;
		});

		// This is a bad way of doing it. Too bad!
		Optional<Pair<String, String>> result = dialog.showAndWait();
		result.ifPresent(op -> {
			NewForgeProjectDialog.newDialogB(modName, op.getKey(), op.getValue());
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void newDialogB(String modName, String modId, String minecraftVersion) {
		// Setup Window
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle(Configs.INSTANCE.lang.get("dialog.new_forge") + " | " + modName);
		dialog.getDialogPane().getButtonTypes().addAll(UIUtils.BUTTON_TYPE_NEXT, ButtonType.CANCEL);

		// Create grid
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 200, 10, 10));

		// Create Input Fields

		ChoiceBox projectType = new ChoiceBox();
		projectType.getItems().addAll(new ForgeVersionHelper().getAllForgeVersionsForMinecraft(minecraftVersion));

		grid.add(new Label(Configs.INSTANCE.lang.get("dialog.new_forge.forge_version") + ":"), 0, 1);
		grid.add(projectType, 1, 1);

		Node createButton = dialog.getDialogPane().lookupButton(UIUtils.BUTTON_TYPE_NEXT);
		createButton.setDisable(true);
//		modId.textProperty().addListener((observable, oldValue, newValue) -> {
//			createButton.setDisable(newValue.trim().isEmpty());
//		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
//		Platform.runLater(() -> modId.requestFocus());

		// Convert the result to a username-password-pair when the login button is
		// clicked.
//		dialog.setResultConverter(dialogButton -> {
//			if (dialogButton == UIUtils.BUTTON_TYPE_NEXT) {
//				return new Pair<>(modId.getText(), (String) projectType.getValue());
//			}
//			return null;
//		});

		// This is a bad way of doing it. Too bad!
		Optional<Pair<String, String>> result = dialog.showAndWait();
		result.ifPresent(op -> {
			if (op.getValue() == Configs.INSTANCE.lang.get("dialog.new_project.forge_mod")) {
				new NewForgeProjectDialog();
			}
		});
	}

}
