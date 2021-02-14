package io.github.railroad;

import io.github.railroad.menu.TopMenu;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.InputStream;

import static io.github.railroad.objects.ConfirmQuitWindow.displayQuitWindow;
import static io.github.railroad.utility.Components.Stages.convertToBuilder;

public class Railroad extends Application {
    public static boolean darkMode = true;

    public Scene mainScene;

    @Override
    public void start(Stage stage) {
        final TopMenu menu = new TopMenu();
        menu.createMenu();
        createComponents(menu, stage);
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

        Stage window = convertToBuilder(stage)
                .title("Railroad IDE")
                .scene(this.mainScene)
                .icons(icons)
                .center()
                .show().get();

        if (darkMode) mainScene.getStylesheets().add("assets/styles/mode/darkmode.css");
        else mainScene.getStylesheets().add("assets/styles/mode/lightmode.css");

        window.setOnCloseRequest(event -> {
            event.consume();
            onClose(window);
        });
    }

    private void onClose(Stage window) {
        final boolean shouldClose = displayQuitWindow("Quit", "Are you sure you want to quit?");
        if (shouldClose) window.close();
    }

    // TODO: Start filling out some of these other menus.
    public void createComponents(Node topMenu, Stage window) {
        final BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        mainScene = new Scene(borderPane);
    }
}
