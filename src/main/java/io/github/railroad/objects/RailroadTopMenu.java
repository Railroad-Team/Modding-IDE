package io.github.railroad.objects;

import io.github.railroad.config.LanguageConfig;
import io.github.railroad.terminal.OpenTerminal;
import io.github.railroad.utility.UIUtils;
import io.github.railroad.windows.NewProjectDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

// TODO make assets for all the menu items!
public class RailroadTopMenu extends MenuBar {
	private final LanguageConfig langConfig;

	public RailroadTopMenu(LanguageConfig langConfigIn, Menu... menus) {
		super(menus);
		this.langConfig = langConfigIn;
		this.createMenu();
	}

	public void createMenu() {
		Menu fileMenu = new Menu(this.langConfig.get("menu.file"));
		this.createFileMenu(fileMenu);

		Menu editMenu = new Menu(this.langConfig.get("menu.edit"));
		this.createEditMenu(editMenu);

		Menu searchMenu = new Menu(this.langConfig.get("menu.search"));
		this.createSearchMenu(searchMenu);

		Menu runMenu = new Menu(this.langConfig.get("menu.run"));
		Menu viewMenu = new Menu(this.langConfig.get("menu.view"));
		this.createViewMenu(viewMenu);

		Menu helpMenu = new Menu(this.langConfig.get("menu.help"));
		this.getMenus().addAll(fileMenu, editMenu, searchMenu, runMenu, viewMenu, helpMenu);
	}

	public void createFileMenu(Menu fileMenu) {

		this.createFileNewMenu(fileMenu);
		fileMenu.getItems().add(new SeparatorMenuItem());

		MenuItem openItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.open"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem openRecentItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.openrecent"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem saveItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.save"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem saveAsItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.saveas"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem exportItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.export"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		fileMenu.getItems().addAll(openItem, openRecentItem, new SeparatorMenuItem(), saveItem, saveAsItem, exportItem);
	}

	public void createFileNewMenu(Menu fileMenu) {
		Menu newMenu = new Menu(this.langConfig.get("menu.file.new"));

		MenuItem projectItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.project"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/project.png")).setActionEvent(event -> {
					new NewProjectDialog();
				}).build();

		MenuItem packageItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.package"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/package.png")).build();

		MenuItem folderItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.folder"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/folder.png")).build();

		MenuItem clazzItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.class"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/class.png"))
				.setActionEvent(event -> new CreateNewJavaFile("Create New Class", "Done", JavaClassTypes.CLASS))
				.build();

		MenuItem interfaceItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.interface"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/interface.png"))
				.setActionEvent(
						event -> new CreateNewJavaFile("Create New Interface", "Done", JavaClassTypes.INTERFACE))
				.build();

		MenuItem enumItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.enum"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/enum.png"))
				.setActionEvent(
						event -> new CreateNewJavaFile("Create New Enum", "Done", JavaClassTypes.ENUM).makeWindow())
				.build();

		MenuItem annotationItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.annotation"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/annotation.png")).build();

		MenuItem jsonItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.json"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/json.png")).build();

		MenuItem fileItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.file"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/file.png"))
				.setActionEvent(event -> new CreateNewFileWindow("Create New File", "Done").makeWindow()).build();

		MenuItem textureItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.texture"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/texture.png")).build();

		newMenu.getItems().addAll(projectItem, new SeparatorMenuItem(), packageItem, folderItem,
				new SeparatorMenuItem(), clazzItem, interfaceItem, enumItem, annotationItem, new SeparatorMenuItem(),
				jsonItem, fileItem, new SeparatorMenuItem(), textureItem, createFileNewModelMenu());

		fileMenu.getItems().add(newMenu);
	}

	public Menu createFileNewModelMenu() {
		Menu modelMenu = new Menu(this.langConfig.get("menu.file.new.model"));

		MenuItem itemModelItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.model.item"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/model.png")).build();

		MenuItem blockModelItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.model.block"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/model.png")).build();

		MenuItem entityModelItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.new.model.entity"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/model.png")).build();

		modelMenu.getItems().addAll(itemModelItem, blockModelItem, entityModelItem);

		return modelMenu;
	}

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

		editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(),
				delete, selectAll, new SeparatorMenuItem(), findReplace);
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

	// TODO: Give a suitable location to open terminal
	public void createViewMenu(Menu viewMenu) {
		MenuItem terminal = RailroadMenuItem.Builder.create(this.langConfig.get("menu.view.openterminal"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png"))
				.setActionEvent(event -> OpenTerminal.openTerminal(null)).build();

		viewMenu.getItems().addAll(terminal);
	}
}
