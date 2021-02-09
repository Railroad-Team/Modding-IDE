package com.turtywurty.railroad.windows;

import com.turtywurty.railroad.util.FileUtils;
import com.turtywurty.railroad.util.UIUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

//TODO: make choosing file path have a filedialog screen
public class CreateNewFileWindow {
	public static Map<Stage, Boolean> ANSWER = new HashMap<>();
	public static String filePath;

	public static void displayWindow(String title, String message) {
		Stage window = new Stage();
		window.centerOnScreen();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(100);
		window.setResizable(false);
		DirectoryChooser directoryChooser = new DirectoryChooser();

		Label label = new Label(message);

		TextField fileName = new TextField("");
		TextField pathName = new TextField("File Path");
		pathName.setEditable(false);
		Button choseFilePath = UIUtils.createButton("Chose Path", event -> {

			File file = directoryChooser.showDialog(window);
			if (file != null) {
				filePath = file.getAbsolutePath();
				pathName.setText(filePath);
			}
			directoryChooser.setInitialDirectory(new File(""));

		});

		Button yesBtn = UIUtils.createButton("Create File", event -> {

			System.out.println(filePath);
			FileUtils.createNewFile(filePath + "\\" + fileName.getText());
			window.close();
		});

		Button noBtn = UIUtils.createButton("Cancel", event -> {
			ANSWER.put(window, false);
			window.close();
		});

		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, fileName, choseFilePath, pathName, yesBtn, noBtn);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}
}