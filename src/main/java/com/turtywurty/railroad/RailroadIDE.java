package com.turtywurty.railroad;

import java.io.InputStream;

import com.turtywurty.railroad.config.Config;
import com.turtywurty.railroad.menu.MenuComponents;
import com.turtywurty.railroad.util.Utils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//TODO: Split some of this off into seperate classes to avoid this main class being a giant monstrosity!
public class RailroadIDE extends Application {

	public static Scene mainScene;
	public static Config config;

	// TODO create an instance variable
	public static void main(String[] args) {
		launch(args);
	}

	// TODO: Add some validation for these logo files to avoid crashes.
	// TODO: Create a file that it reads the path of the logo files from.
	@Override
	public void start(Stage mainWindow) throws Exception {
		config = new Config();
		MenuComponents.createComponents(mainWindow);
		Image[] logos = new Image[2];
		InputStream logo16 = RailroadIDE.class.getResourceAsStream("/assets/img/logo16.png");
		InputStream logo32 = RailroadIDE.class.getResourceAsStream("/assets/img/logo32.png");
		logos[0] = new Image(logo16);
		logos[1] = new Image(logo32);
		logo16.close();
		logo32.close();
		Stage window = Utils.setupWindow(mainWindow, RailroadIDE.config.lang.get("window.title"), RailroadIDE.mainScene, logos);
		window.setOnCloseRequest(event -> {
			event.consume();
			this.onClose(window);
		});
	}

	

	private void onClose(Stage window) {
		boolean shouldClose = ConfirmWindow.displayWindow(RailroadIDE.config.lang.get("dialog.quit"),
				RailroadIDE.config.lang.get("dialog.quit.prompt"));
		if (shouldClose)
			window.close();
	}
}