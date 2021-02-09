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
		this.createFileNewMenu(fileMenu);

		Menu editMenu = new Menu(this.langConfig.get("menu.edit"));
		this.createEditMenu(editMenu);

		Menu searchMenu = new Menu(this.langConfig.get("menu.search"));
		Menu runMenu = new Menu(this.langConfig.get("menu.run"));
		Menu viewMenu = new Menu(this.langConfig.get("menu.view"));
		Menu helpMenu = new Menu(this.langConfig.get("menu.help"));
		this.getMenus().addAll(fileMenu, editMenu, searchMenu, runMenu, viewMenu, helpMenu);
	}

	// TODO: Make proper textures for all these icons. Currently all just programmer
	// art! ;)
	public Menu createFileNewMenu(Menu fileMenu) {
		Menu newMenu = new Menu(this.langConfig.get("menu.file.new"));

		MenuItem javaProjectItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.javaproject"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem projectItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.project"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/project.png")).build();

		MenuItem javaWorkingSetItem = RailroadMenuItem.Builder
				.create(this.langConfig.get("menu.file.new.javaworkingset"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/working_set.png")).build();

		MenuItem packageItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.package"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/package.png")).build();

		MenuItem sourceFolderItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.sourcefolder"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/source_folder.png")).build();

		MenuItem fileItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.file"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/file.png")).build();

		MenuItem folderItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.folder"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/folder.png")).build();

		MenuItem clazzItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.class"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/class.png")).build();

		MenuItem interfaceItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.interface"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/interface.png")).build();

		MenuItem enumItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.enum"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/enum.png")).build();

		MenuItem annotationItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.annotation"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/annotation.png")).build();

		newMenu.getItems().addAll(javaProjectItem, projectItem, javaWorkingSetItem, new SeparatorMenuItem(),
				sourceFolderItem, packageItem, file, folderItem, new SeparatorMenuItem(), clazzItem, interfaceItem,
				enumItem, annotationItem);
		fileMenu.getItems().add(newMenu);
		return newMenu;
	}

	// TODO: Remove the -> Edit part
	// TODO: pls can someone do assets
	public Menu createEditMenu(Menu editMenu) {
		MenuItem undo = RailroadMenuItem.Builder.create(this.langConfig.get("menu.edit.undo"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();
		MenuItem redo = RailroadMenuItem.Builder.create(this.langConfig.get("menu.edit.redo"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();
		editMenu.getItems().addAll(undo, redo);
		return editMenu;
	}
}
