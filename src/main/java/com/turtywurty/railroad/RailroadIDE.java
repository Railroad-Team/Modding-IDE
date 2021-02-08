package com.turtywurty.railroad;

import java.io.InputStream;

import com.turtywurty.railroad.util.Utils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//TODO: Split some of this off into seperate classes to avoid this main class being a giant monstrosity!
public class RailroadIDE extends Application {

	private Scene mainScene;

	public static void main(String[] args) {
		launch(args);
	}

	// TODO: Add some validation for these logo files to avoid crashes.
	// TODO: Create a file that it reads the path of the logo files from.
	@Override
	public void start(Stage mainWindow) throws Exception {
		this.createComponents(mainWindow);
		Image[] logos = new Image[2];
		InputStream logo16 = RailroadIDE.class.getResourceAsStream("/assets/img/logo16.png");
		InputStream logo32 = RailroadIDE.class.getResourceAsStream("/assets/img/logo32.png");
		logos[0] = new Image(logo16);
		logos[1] = new Image(logo32);
		logo16.close();
		logo32.close();
		Stage window = Utils.setupWindow(mainWindow, "JavaFX Testing", this.mainScene, logos);
		window.setOnCloseRequest(event -> {
			event.consume();
			this.onClose(window);
		});
	}

	// TODO: Start filling out some of these other menus.
	public void createComponents(Stage window) {
		BorderPane borderPane = new BorderPane();

		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("File");
		fileMenu.getItems().add(this.createNewMenu());

		Menu editMenu = new Menu("Edit");
		Menu sourceMenu = new Menu("Source");
		Menu refactorMenu = new Menu("Refactor");
		Menu navigateMenu = new Menu("Navigate");
		Menu searchMenu = new Menu("Search");
		Menu projectMenu = new Menu("Project");
		Menu runMenu = new Menu("Run");
		Menu windowMenu = new Menu("Window");
		Menu helpMenu = new Menu("Help");

		menuBar.getMenus().addAll(fileMenu, editMenu, sourceMenu, refactorMenu, navigateMenu, searchMenu, projectMenu,
				runMenu, windowMenu, helpMenu);
		HBox topMenu = new HBox(menuBar);
		borderPane.setTop(topMenu);
		this.mainScene = new Scene(borderPane);
	}

	// TODO: Make proper textures for all these icons. Currently all just programmer art! ;)
	// TODO: Find a way that allows us to fit an image larger than 20x20 pixels into the space this image is drawn.
	public Menu createNewMenu() {
		Menu menu = new Menu("New");

		MenuItem javaProject = RailroadMenuItem.Builder.create("Java Project")
				.setGraphic(Utils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem project = RailroadMenuItem.Builder.create("Project")
				.setGraphic(Utils.createMenuGraphics("/assets/img/project.png")).build();

		// TODO: Once added texture at "/assets/img/working_set.png" un-comment
		// setGraphic
		MenuItem javaWorkingSet = RailroadMenuItem.Builder.create("Java Working Set")
				/* .setGraphic(Utils.createMenuGraphics("/assets/img/working_set.png") */.build();

		MenuItem packagE = RailroadMenuItem.Builder.create("Package")
				.setGraphic(Utils.createMenuGraphics("/assets/img/package.png")).build();

		MenuItem sourceFolder = RailroadMenuItem.Builder.create("Source Folder")
				.setGraphic(Utils.createMenuGraphics("/assets/img/source_folder.png")).build();

		MenuItem file = RailroadMenuItem.Builder.create("File").setGraphic(Utils.createMenuGraphics("/assets/img/file.png"))
				.build();

		MenuItem folder = RailroadMenuItem.Builder.create("Folder")
				.setGraphic(Utils.createMenuGraphics("/assets/img/folder.png")).build();

		MenuItem clazz = RailroadMenuItem.Builder.create("Class")
				.setGraphic(Utils.createMenuGraphics("/assets/img/class.png")).build();

		MenuItem interfacE = RailroadMenuItem.Builder.create("Interface")
				.setGraphic(Utils.createMenuGraphics("/assets/img/interface.png")).build();

		MenuItem enuM = RailroadMenuItem.Builder.create("Enum").setGraphic(Utils.createMenuGraphics("/assets/img/enum.png"))
				.build();

		MenuItem annotation = RailroadMenuItem.Builder.create("Annotation")
				.setGraphic(Utils.createMenuGraphics("/assets/img/annotation.png")).build();

		menu.getItems().addAll(javaProject, project, javaWorkingSet, new SeparatorMenuItem(), sourceFolder, packagE, file,
				folder, new SeparatorMenuItem(), clazz, interfacE, enuM, annotation);
		return menu;
	}

	private void onClose(Stage window) {
		boolean shouldClose = ConfirmWindow.displayWindow("Quit", "Are you sure you would like to quit?");
		if (shouldClose)
			window.close();
	}
}