package io.github.railroad.utility;

import io.github.railroad.Railroad;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.InputStream;

public class UIUtils {

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
        final Button btn = new Button(text);
        btn.setOnAction(event);
        return btn;
    }

    public static ImageView createMenuGraphics(String imagePath) {
        return new ImageView(new Image(imagePath, 20, 20, true, true, true));
    }

    // TODO: Add some sort of config for these logo files.
    public static void getIcons(Image[] icons) { // Return value never used
        final InputStream logo16 = Railroad.class.getResourceAsStream("/assets/img/logo16.png");
        final InputStream logo32 = Railroad.class.getResourceAsStream("/assets/img/logo32.png");

        if (logo16 != null && logo32 != null) {
            try {
                icons[0] = new Image(logo16);
                icons[1] = new Image(logo32);
                logo16.close();
                logo32.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
