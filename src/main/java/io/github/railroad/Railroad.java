package io.github.railroad;

import io.github.railroad.config.Configuration;
import io.github.railroad.drp.DiscordRichPresenceManager;
import io.github.railroad.objects.ConfirmWindow;
import io.github.railroad.objects.RailroadTopMenu;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.InputStream;

public class Railroad extends Application {

	public Scene mainScene;
	public Configuration config = new Configuration();

	public static void boot(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) {
		createComponents(new RailroadTopMenu(config.language), mainWindow);
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
		mainWindow.setTitle(config.language.get("window.title"));
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
		final DiscordRichPresenceManager richPresenceManager = new DiscordRichPresenceManager();
		richPresenceManager.setDetails("Railroad IDE").setStats("Editing {insert file name here}")
				.setBigImage(DiscordRichPresenceManager.BigImageKeys.NONE, "Railroad IDE").build();
	}

	private void onClose(Stage window) {
		final boolean shouldClose = ConfirmWindow.displayWindow(config.language.get("dialog.quit"),
				config.language.get("dialog.quit.prompt"));
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
