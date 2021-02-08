package com.turtywurty.railroad.components;

import com.turtywurty.railroad.config.LanguageConfig;
import com.turtywurty.railroad.util.UIUtils;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class RailroadTopMenu extends MenuBar {
	private LanguageConfig langConfig;

	public RailroadTopMenu(LanguageConfig langConfigIn, Menu... menus) {
		super(menus);
		this.langConfig = langConfigIn;
		this.createMenu();
	}

	public void createMenu() {
		Menu fileMenu = new Menu(this.langConfig.get("menu.file"));
		fileMenu.getItems().add(this.createNewMenu());

		Menu editMenu = new Menu(this.langConfig.get("menu.edit"));
		Menu sourceMenu = new Menu(this.langConfig.get("menu.source"));
		Menu refactorMenu = new Menu(this.langConfig.get("menu.refactor"));
		Menu navigateMenu = new Menu(this.langConfig.get("menu.navigate"));
		Menu searchMenu = new Menu(this.langConfig.get("menu.search"));
		Menu projectMenu = new Menu(this.langConfig.get("menu.project"));
		Menu runMenu = new Menu(this.langConfig.get("menu.run"));
		Menu windowMenu = new Menu(this.langConfig.get("menu.window"));
		Menu helpMenu = new Menu(this.langConfig.get("menu.help"));
		this.getMenus().addAll(fileMenu, editMenu, sourceMenu, refactorMenu, navigateMenu, searchMenu, projectMenu, runMenu,
				windowMenu, helpMenu);
	}

	// TODO: Make proper textures for all these icons. Currently all just programmer
	// art! ;)
	public Menu createNewMenu() {
		Menu menu = new Menu(this.langConfig.get("menu.file.new"));

		MenuItem javaProject = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.javaproject"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem project = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.project"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/project.png")).build();

		MenuItem javaWorkingSet = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.javaworkingset"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/working_set.png")).build();

		MenuItem packagE = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.package"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/package.png")).build();

		MenuItem sourceFolder = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.sourcefolder"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/source_folder.png")).build();

		MenuItem file = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.file"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/file.png")).build();

		MenuItem folder = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.folder"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/folder.png")).build();

		MenuItem clazz = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.class"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/class.png")).build();

		MenuItem interfacE = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.interface"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/interface.png")).build();

		MenuItem enuM = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.enum"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/enum.png")).build();

		MenuItem annotation = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.annotation"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/annotation.png")).build();

		menu.getItems().addAll(javaProject, project, javaWorkingSet, new SeparatorMenuItem(), sourceFolder, packagE, file,
				folder, new SeparatorMenuItem(), clazz, interfacE, enuM, annotation);
		return menu;
	}
}
