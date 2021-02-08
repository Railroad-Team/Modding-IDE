package com.turtywurty.railroad.util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

//TODO: Come up with a decent name for this class.
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

	// TODO: Add some sort of validation for that image path because that is
	// extremely prone to errors! lmao.
	public static ImageView createMenuGraphics(String imagePath) {
		return new ImageView(new Image(imagePath, 20, 20, true, true, true));
	}
	
	
	// some methods to get the os (type) for debugging - affehund
	private static final String OS = System.getProperty("os.name").toUpperCase();

	public static String getOSName()
	{
		return OS;
	}

	public static boolean isWindows()
	{
		return OS.contains("WIN");
	}


	public static boolean isMac()
	{
		return OS.contains("MAC");
	}

	public static boolean isUnix()
	{
		return OS.contains("NIX") || OS.contains("NUX") || OS.contains("AIX");
	}
}
