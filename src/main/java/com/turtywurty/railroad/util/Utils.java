package com.turtywurty.railroad.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Utils {

	public static Stage setupWindow(Stage window, String title, Scene startScene, Image[] logos) {
		window.setTitle(title);
		window.setScene(startScene);
		window.setMaximized(true);
		window.getIcons().setAll(logos);
		window.centerOnScreen();
		window.show();
		return window;
	}

	public static Button createButton(String text, EventHandler<ActionEvent> event) {
		Button btn = new Button(text);
		btn.setOnAction(event);
		return btn;
	}
}
