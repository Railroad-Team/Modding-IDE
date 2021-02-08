package com.turtywurty.railroad.menu;

import com.turtywurty.railroad.RailroadIDE;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuComponents {



	// TODO: Start filling out some of these other menus.
	public static void createComponents(Stage window) {
		BorderPane borderPane = new BorderPane();

		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu(RailroadIDE.config.lang.get("menu.file"));
		fileMenu.getItems().add(RailroadMenu.createNewMenu());

		Menu editMenu = new Menu(RailroadIDE.config.lang.get("menu.edit"));
		Menu sourceMenu = new Menu(RailroadIDE.config.lang.get("menu.source"));
		Menu refactorMenu = new Menu(RailroadIDE.config.lang.get("menu.refactor"));
		Menu navigateMenu = new Menu(RailroadIDE.config.lang.get("menu.navigate"));
		Menu searchMenu = new Menu(RailroadIDE.config.lang.get("menu.search"));
		Menu projectMenu = new Menu(RailroadIDE.config.lang.get("menu.project"));
		Menu runMenu = new Menu(RailroadIDE.config.lang.get("menu.run"));
		Menu windowMenu = new Menu(RailroadIDE.config.lang.get("menu.window"));
		Menu helpMenu = new Menu(RailroadIDE.config.lang.get("menu.help"));

		menuBar.getMenus().addAll(fileMenu, editMenu, sourceMenu, refactorMenu, navigateMenu, searchMenu, projectMenu,
				runMenu, windowMenu, helpMenu);
		HBox topMenu = new HBox(menuBar);
		borderPane.setTop(topMenu);
		RailroadIDE.mainScene = new Scene(borderPane);
	}
}
