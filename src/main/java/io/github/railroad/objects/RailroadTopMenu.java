package io.github.railroad.objects;

import io.github.railroad.config.LanguageConfig;
import io.github.railroad.objects.RailroadMenuItem.Builder;
import io.github.railroad.utility.UIUtils;
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

	public void createFileMenu(Menu fileMenu) {

		this.createFileNewMenu(fileMenu);
		this.createFileGenerateMenu(fileMenu);
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

        MenuItem javaProjectItem = Builder.create(this.langConfig.get("menu.file.new.javaproject"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        MenuItem projectItem = Builder.create(this.langConfig.get("menu.file.new.project"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/project.png")).build();

        MenuItem javaWorkingSetItem = Builder.create(this.langConfig.get("menu.file.new.javaworkingset"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/working_set.png")).build();

        MenuItem packageItem = Builder.create(this.langConfig.get("menu.file.new.package"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/package.png")).build();

        MenuItem sourceFolderItem = Builder.create(this.langConfig.get("menu.file.new.sourcefolder"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/source_folder.png")).build();

        MenuItem file = Builder.create(this.langConfig.get("menu.file.new.file"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/file.png"))
                .setActionEvent(event -> new CreateNewFileWindow("Create New File", "Done")).build();

        MenuItem folderItem = Builder.create(this.langConfig.get("menu.file.new.folder"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/folder.png")).build();

        MenuItem clazzItem = Builder.create(this.langConfig.get("menu.file.new.class"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/class.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Class", "Done", ClassType.CLASS)).build();

        MenuItem interfaceItem = Builder.create(this.langConfig.get("menu.file.new.interface"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/interface.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Interface", "Done", ClassType.INTERFACE)).build();

        MenuItem enumItem = Builder.create(this.langConfig.get("menu.file.new.enum"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/enum.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Enum", "Done", ClassType.ENUM)).build();

        MenuItem annotationItem = Builder.create(this.langConfig.get("menu.file.new.annotation"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/annotation.png")).build();

        newMenu.getItems().addAll(javaProjectItem, projectItem, javaWorkingSetItem, new SeparatorMenuItem(),
                sourceFolderItem, packageItem, file, folderItem, new SeparatorMenuItem(), clazzItem, interfaceItem, enumItem,
                annotationItem);

        fileMenu.getItems().add(newMenu);
    }

	public void createFileGenerateMenu(Menu fileMenu) {
		Menu generateMenu = new Menu(this.langConfig.get("menu.file.generate"));

		MenuItem forgeModItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.generate.forgemod"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem fabricModItem = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.generate.fabricmod"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem itemModel = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.generate.itemmodel"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem blockModel = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.generate.blockmodel"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		MenuItem blockState = RailroadMenuItem.Builder.create(this.langConfig.get("menu.file.generate.blockstate"))
				.setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

		generateMenu.getItems().addAll(forgeModItem, fabricModItem, new SeparatorMenuItem(), itemModel, blockModel,
				blockState);

		fileMenu.getItems().add(generateMenu);
	}

	public void createEditMenu(Menu editMenu) {
        MenuItem undo = Builder.create(this.langConfig.get("menu.edit.undo"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        MenuItem redo = Builder.create(this.langConfig.get("menu.edit.redo"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        MenuItem cut = Builder.create(this.langConfig.get("menu.edit.cut"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        MenuItem copy = Builder.create(this.langConfig.get("menu.edit.copy"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        MenuItem paste = Builder.create(this.langConfig.get("menu.edit.paste"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        MenuItem delete = Builder.create(this.langConfig.get("menu.edit.delete"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        MenuItem selectAll = Builder.create(this.langConfig.get("menu.edit.selectAll"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        MenuItem findReplace = Builder.create(this.langConfig.get("menu.edit.findReplace"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(), delete,
                selectAll, new SeparatorMenuItem(), findReplace);
    }

    public void createSearchMenu(Menu searchMenu) {
        MenuItem search = Builder.create(this.langConfig.get("menu.search.search"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        Menu textMenu = new Menu(this.langConfig.get("menu.search.text"));

        MenuItem workspace = Builder.create(this.langConfig.get("menu.search.text.workspace"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        MenuItem project = Builder.create(this.langConfig.get("menu.search.text.project"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        MenuItem file = Builder.create(this.langConfig.get("menu.search.text.file"))
                .setGraphic(UIUtils.createMenuGraphics("/assets/img/java_project.png")).build();

        searchMenu.getItems().addAll(search, new SeparatorMenuItem(), textMenu);
        textMenu.getItems().addAll(workspace, project, file);
    }
}
