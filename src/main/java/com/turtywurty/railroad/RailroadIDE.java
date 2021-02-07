package com.turtywurty.railroad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RailroadIDE extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) throws Exception {
		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane);
		setupWindow(mainWindow, "Railroad IDE", scene);
	}

	public static Stage setupWindow(Stage window, String title, Scene startScene) {
		window.setTitle(title);
		window.setScene(startScene);
		window.setMaximized(true);
		window.centerOnScreen();
		window.show();
		return window;
	}
}