package io.github.railroad;

import io.github.railroad.config.Configs;
import io.github.railroad.drp.DiscordRichPresenceManager;
import io.github.railroad.objects.ConfirmWindow;
import io.github.railroad.objects.RailroadTopMenu;
import io.github.railroad.utility.UIUtils;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Railroad extends Application {

	public Scene mainScene;
	public Configs config = new Configs();

	public static void boot(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) {
		createComponents(new RailroadTopMenu(config.lang), mainWindow);
		final Image[] icons = new Image[2];
		UIUtils.getIcons(icons);
		final Stage window = UIUtils.setupWindow(mainWindow, config.lang.get("window.title"), mainScene, icons);
		window.setOnCloseRequest(event -> {
			event.consume();
			onClose(window);
		});
		final DiscordRichPresenceManager richPresenceManager = new DiscordRichPresenceManager();
		richPresenceManager.setDetails("Railroad IDE").setStats("Editing {insert file name here}")
				.setBigImage(DiscordRichPresenceManager.BigImageKeys.NONE, "Railroad IDE").build();
	}

	private void onClose(Stage window) {
		final boolean shouldClose = ConfirmWindow.displayWindow(config.lang.get("dialog.quit"),
				config.lang.get("dialog.quit.prompt"));
		if (shouldClose)
			window.close();
	}

	// TODO: Start filling out some of these other menus.
	public void createComponents(Node topMenu, Stage window) {
		final BorderPane borderPane = new BorderPane();
		borderPane.setTop(topMenu);
		mainScene = new Scene(borderPane);
	}
}
