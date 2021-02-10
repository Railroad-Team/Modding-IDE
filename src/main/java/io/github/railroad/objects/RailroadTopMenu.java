package io.github.railroad.objects;

import io.github.railroad.config.LanguageConfig;
import io.github.railroad.utility.UIUtils;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class RailroadTopMenu extends MenuBar {
	private final LanguageConfig langConfig;

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
	public void createFileNewMenu(Menu fileMenu) {
		Menu newMenu = new Menu(this.langConfig.get("menu.file.new"));

		MenuItem javaProjectItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.javaproject"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem projectItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.project"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/project.png")).build();

		MenuItem javaWorkingSetItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.javaworkingset"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/working_set.png")).build();

		MenuItem packageItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.package"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/package.png")).build();

		MenuItem sourceFolderItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.sourcefolder"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/source_folder.png")).build();

		MenuItem file = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.file"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/file.png"))
				.setActionEvent(event -> new CreateNewFileWindow("Create New File", "Done")).build();

		MenuItem folderItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.folder"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/folder.png")).build();

		MenuItem clazzItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.class"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/class.png"))
				.setActionEvent(event -> new CreateNewJavaFile("Create New Class", "Done", JavaClassTypes.CLASS)).build();

		MenuItem interfaceItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.interface"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/interface.png"))
				.setActionEvent(event -> new CreateNewJavaFile("Create New Interface", "Done", JavaClassTypes.INTERFACE)).build();

		MenuItem enumItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.enum"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/enum.png"))
				.setActionEvent(event -> new CreateNewJavaFile("Create New Enum", "Done", JavaClassTypes.ENUM)).build();

		MenuItem annotationItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.annotation"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/annotation.png")).build();

		newMenu.getItems().addAll(javaProjectItem, projectItem, javaWorkingSetItem, new SeparatorMenuItem(),
				sourceFolderItem, packageItem, file, folderItem, new SeparatorMenuItem(), clazzItem, interfaceItem, enumItem,
				annotationItem);

		fileMenu.getItems().add(newMenu);
	}

	// TODO: pls can someone do assets
	public void createEditMenu(Menu editMenu) {
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

		editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(), delete,
				selectAll, new SeparatorMenuItem(), findReplace);
	}

	public void createSearchMenu(Menu searchMenu) {
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
	}
}
