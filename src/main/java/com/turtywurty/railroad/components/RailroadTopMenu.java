package com.turtywurty.railroad.components;

import com.turtywurty.railroad.config.LanguageConfig;
import com.turtywurty.railroad.util.UIUtils;
import com.turtywurty.railroad.windows.CreateNewJavaFile;
import com.turtywurty.railroad.windows.CreateNewFileWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		this.createSearchMenu(searchMenu);
		
		Menu runMenu = new Menu(this.langConfig.get("menu.run"));
		Menu viewMenu = new Menu(this.langConfig.get("menu.view"));
		Menu helpMenu = new Menu(this.langConfig.get("menu.help"));
		this.getMenus().addAll(fileMenu, editMenu, searchMenu, runMenu, viewMenu, helpMenu);
	}

	// TODO: Make proper textures for all these icons. Currently all just programmer
	// art! ;)
	public Menu createFileNewMenu(Menu fileMenu) {
		Menu newMenu = new Menu(this.langConfig.get("menu.file.new"));

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
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/file.png")).setActionEvent(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						new CreateNewFileWindow("Create New File", "Done");
					}
				}).build();

		MenuItem folder = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.folder"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/folder.png")).build();

		MenuItem clazz = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.class"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/class.png")).setActionEvent(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						new CreateNewJavaFile("Create New Class", "Done", 1);
					}
				}).build();

		MenuItem interfacE = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.interface"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/interface.png")).setActionEvent(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						new CreateNewJavaFile("Create New Interface", "Done", 2);
					}
				}).build();

		MenuItem enuM = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.enum"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/enum.png")).setActionEvent(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						new CreateNewJavaFile("Create New Enum", "Done", 3);
					}
				}).build();

		MenuItem annotation = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.annotation"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/annotation.png")).setActionEvent(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						new CreateNewJavaFile("Create New Annotation", "Done", 4 	);
					}
				}).build();

		newMenu.getItems().addAll(javaProject, project, javaWorkingSet, new SeparatorMenuItem(), sourceFolder, packagE,
				file, folder, new SeparatorMenuItem(), clazz, interfacE, enuM, annotation);
		fileMenu.getItems().add(newMenu);
		return newMenu;
	}

	// TODO: pls can someone do assets
	public Menu createEditMenu(Menu editMenu) {
		MenuItem undo = RailroadMenuItem.Builder.create(this.langConfig.get("menu.edit.undo"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem redo = RailroadMenuItem.Builder.create(this.langConfig.get("menu.edit.redo"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem cut = RailroadMenuItem.Builder.create(this.langConfig.get("menu.edit.cut"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem copy = RailroadMenuItem.Builder.create(this.langConfig.get("menu.edit.copy"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem paste = RailroadMenuItem.Builder.create(this.langConfig.get("menu.edit.paste"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem delete = RailroadMenuItem.Builder.create(this.langConfig.get("menu.edit.delete"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem selectAll = RailroadMenuItem.Builder.create(this.langConfig.get("menu.edit.selectAll"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem findReplace = RailroadMenuItem.Builder.create(this.langConfig.get("menu.edit.findReplace"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(),
				delete, selectAll, new SeparatorMenuItem(), findReplace);
		return editMenu;
	}

	public Menu createSearchMenu(Menu searchMenu) {
		MenuItem search = RailroadMenuItem.Builder.create(this.langConfig.get("menu.search.search"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();
		
		Menu textMenu = new Menu(this.langConfig.get("menu.search.text"));
		
		MenuItem workspace = RailroadMenuItem.Builder.create(this.langConfig.get("menu.search.text.workspace"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();
		
		MenuItem project = RailroadMenuItem.Builder.create(this.langConfig.get("menu.search.text.project"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();
		
		MenuItem file = RailroadMenuItem.Builder.create(this.langConfig.get("menu.search.text.file"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();
		
		
		searchMenu.getItems().addAll(search, new SeparatorMenuItem(), textMenu);
		textMenu.getItems().addAll(workspace, project, file);
		return textMenu;
	}
}
