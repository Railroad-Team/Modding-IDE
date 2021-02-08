package com.turtywurty.railroad.menu;

import com.turtywurty.railroad.RailroadIDE;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Components {
//TODO: Start filling out some of these other menus.
public static void createComponents(Stage window) {
		BorderPane borderPane = new BorderPane();

		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("File");
		fileMenu.getItems().add(RailroadMenu.createNewMenu());

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
		RailroadIDE.mainScene = new Scene(borderPane);
	}
}