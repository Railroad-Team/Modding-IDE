package io.github.railroad;

import io.github.railroad.objects.TopMenu;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.InputStream;

public class Railroad extends Application {

    public Scene mainScene;

    @Override
    public void start(Stage mainWindow) {
        final TopMenu menu = new TopMenu();
        menu.createMenu();
        createComponents(menu, mainWindow);
        final Image[] icons = new Image[2];
        // Return value never used
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
        mainWindow.setTitle("Title");
        mainWindow.setScene(mainScene);
        mainWindow.setMaximized(true);
        mainWindow.getIcons().setAll(icons);
        mainWindow.centerOnScreen();
        mainWindow.show();
        final Stage window = mainWindow;
        window.setOnCloseRequest(event -> {
            event.consume();
            onClose(window);
        });
    }

    private void onClose(Stage window) {
	/*	final boolean shouldClose = ConfirmWindow.displayWindow(config.language.get("dialog.quit"),
	//			config.language.get("dialog.quit.prompt"));
	if (shouldClose)*/
        window.close();
    }

    // TODO: Start filling out some of these other menus.
    public void createComponents(Node topMenu, Stage window) {
        final BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        mainScene = new Scene(borderPane);
    }
}
