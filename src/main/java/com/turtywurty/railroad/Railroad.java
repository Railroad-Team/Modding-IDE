package com.turtywurty.railroad;

import com.turtywurty.railroad.components.RailroadTopMenu;
import com.turtywurty.railroad.config.Configs;
import com.turtywurty.railroad.discordrichpresence.DiscordRichPresenceManager;
import com.turtywurty.railroad.util.UIUtils;
import com.turtywurty.railroad.windows.ConfirmWindow;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Railroad extends Application {

    private Scene mainScene;
    private Configs config;
    private DiscordRichPresenceManager DRPmanager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainWindow) throws Exception {
        this.config = new Configs();
        this.createComponents(new RailroadTopMenu(this.config.lang), mainWindow);
        Image[] icons = UIUtils.getIcons(new Image[2]);
        Stage window = UIUtils.setupWindow(mainWindow, this.config.lang.get("window.title"), this.mainScene, icons);
        window.setOnCloseRequest(event -> {
            event.consume();
            this.onClose(window);
        });
        DRPmanager = new DiscordRichPresenceManager();
        DRPmanager.setDetails("Working hard or hardly working").setStats("This is an ide!").setBigImage(DiscordRichPresenceManager.BigImageKeys.JOJO_PFP, "Hey this code isn't jank fool").build();
    }

    private void onClose(Stage window) {
        boolean shouldClose = ConfirmWindow.displayWindow(this.config.lang.get("dialog.quit"),
                this.config.lang.get("dialog.quit.prompt"));
        if (shouldClose)
            window.close();
    }

    // TODO: Start filling out some of these other menus.
    public void createComponents(Node topMenu, Stage window) {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        this.mainScene = new Scene(borderPane);
    }

    public Configs getConfig() {
        return this.config;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }
}