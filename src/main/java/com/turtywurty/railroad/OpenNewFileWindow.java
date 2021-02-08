package com.turtywurty.railroad;

import java.util.HashMap;
import java.util.Map;

import com.turtywurty.railroad.util.FileUtils;
import com.turtywurty.railroad.util.Utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//TODO: make chosing file path have a filedialog screen 
public class OpenNewFileWindow {
	public static Map<Stage, Boolean> ANSWER = new HashMap<Stage, Boolean>();

	public static void displayWindow(String title, String message) {
		Stage window = new Stage();
		window.centerOnScreen();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(100);
		window.setResizable(false);

		Label label = new Label(message);
		
		TextField fileName = new TextField("File Name");
		TextField filePath = new TextField("File Path");
		
		Button yesBtn = Utils.createButton("Create File", event -> {
			FileUtils.createNewFile(filePath.getText() + "\\" + fileName.getText());
			window.close();
		});

		Button noBtn = Utils.createButton("Cancel", event -> {
			ANSWER.put(window, false);
			window.close();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yesBtn, noBtn, fileName, filePath);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}
}
