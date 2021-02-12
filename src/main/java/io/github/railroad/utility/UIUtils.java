package io.github.railroad.utility;

import io.github.railroad.Railroad;
import io.github.railroad.config.Configs;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.InputStream;

public class UIUtils {
	public static final ButtonType BUTTON_TYPE_YES = new ButtonType(Configs.INSTANCE.lang.get("dialog.option.yes"),
			ButtonData.YES);
	public static final ButtonType BUTTON_TYPE_NO = new ButtonType(Configs.INSTANCE.lang.get("dialog.option.no"),
			ButtonData.NO);
	public static final ButtonType BUTTON_TYPE_OK = new ButtonType(Configs.INSTANCE.lang.get("dialog.option.ok"),
			ButtonData.OK_DONE);
	public static final ButtonType BUTTON_TYPE_CANCEL = new ButtonType(
			Configs.INSTANCE.lang.get("dialog.option.cancel"), ButtonData.CANCEL_CLOSE);
	public static final ButtonType BUTTON_TYPE_CREATE = new ButtonType(
			Configs.INSTANCE.lang.get("dialog.option.create"), ButtonData.OK_DONE);

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

	public static ImageView createMenuGraphics(String imagePath) {
		// TODO check if path exists rather than using a try catch
		try {
			return new ImageView(new Image(imagePath, 20, 20, true, true, true));
		} catch (IllegalArgumentException e) {
			System.out.println(imagePath + " did not exist. Using empty image");
			return new ImageView();
		}
	}

	// TODO: Add some sort of config for these logo files.
	public static Image[] getIcons(Image[] icons) { // Return value never used
		InputStream logo16 = Railroad.class.getResourceAsStream("/assets/img/logo16.png");
		InputStream logo32 = Railroad.class.getResourceAsStream("/assets/img/logo32.png");
		try {
			icons[0] = new Image(logo16);
			icons[1] = new Image(logo32);
			logo16.close();
			logo32.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return icons;
	}
}
