package io.github.railroad.objects;

import io.github.railroad.config.LanguageConfiguration;
import io.github.railroad.objects.RailroadMenuItem.Builder;
import io.github.railroad.utility.Components;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

// TODO make assets for all the menu items!
public class RailroadTopMenu extends MenuBar {
    private final LanguageConfiguration langConfig;

    public RailroadTopMenu(LanguageConfiguration langConfigIn, Menu... menus) {
        super(menus);
        langConfig = langConfigIn;
        createMenu();
    }

    public void createMenu() {
        final Menu fileMenu = new Menu(langConfig.get("menu.file"));
        createFileNewMenu(fileMenu);

        final Menu editMenu = new Menu(langConfig.get("menu.edit"));
        createEditMenu(editMenu);

        final Menu searchMenu = new Menu(langConfig.get("menu.search"));
        createSearchMenu(searchMenu);

        final Menu runMenu = new Menu(langConfig.get("menu.run"));
        final Menu viewMenu = new Menu(langConfig.get("menu.view"));
        final Menu helpMenu = new Menu(langConfig.get("menu.help"));
        getMenus().addAll(fileMenu, editMenu, searchMenu, runMenu, viewMenu, helpMenu);
    }

    public void createFileMenu(Menu fileMenu) {

        createFileNewMenu(fileMenu);
        createFileGenerateMenu(fileMenu);
        fileMenu.getItems().add(new SeparatorMenuItem());

        final MenuItem openItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.open"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem openRecentItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.openrecent"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem saveItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.save"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem saveAsItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.saveas"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem exportItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.export"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        fileMenu.getItems().addAll(openItem, openRecentItem, new SeparatorMenuItem(), saveItem, saveAsItem, exportItem);
    }

    public void createFileNewMenu(Menu fileMenu) {
        final Menu newMenu = new Menu(langConfig.get("menu.file.new"));

        final MenuItem javaProjectItem = Builder.create(langConfig.get("menu.file.new.javaproject"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem projectItem = Builder.create(langConfig.get("menu.file.new.project"))
                .setGraphic(Components.createMenuGraphics("/assets/img/project.png")).build();

        final MenuItem javaWorkingSetItem = Builder.create(langConfig.get("menu.file.new.javaworkingset"))
                .setGraphic(Components.createMenuGraphics("/assets/img/working_set.png")).build();

        final MenuItem packageItem = Builder.create(langConfig.get("menu.file.new.package"))
                .setGraphic(Components.createMenuGraphics("/assets/img/package.png")).build();

        final MenuItem sourceFolderItem = Builder.create(langConfig.get("menu.file.new.sourcefolder"))
                .setGraphic(Components.createMenuGraphics("/assets/img/source_folder.png")).build();

        final MenuItem file = Builder.create(langConfig.get("menu.file.new.file"))
                .setGraphic(Components.createMenuGraphics("/assets/img/file.png"))
                .setActionEvent(event -> new CreateNewFileWindow("Create New File", "Done")).build();

        final MenuItem folderItem = Builder.create(langConfig.get("menu.file.new.folder"))
                .setGraphic(Components.createMenuGraphics("/assets/img/folder.png")).build();

        final MenuItem clazzItem = Builder.create(langConfig.get("menu.file.new.class"))
                .setGraphic(Components.createMenuGraphics("/assets/img/class.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Class", "Done", ClassType.CLASS)).build();

        final MenuItem interfaceItem = Builder.create(langConfig.get("menu.file.new.interface"))
                .setGraphic(Components.createMenuGraphics("/assets/img/interface.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Interface", "Done", ClassType.INTERFACE)).build();

        final MenuItem enumItem = Builder.create(langConfig.get("menu.file.new.enum"))
                .setGraphic(Components.createMenuGraphics("/assets/img/enum.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Enum", "Done", ClassType.ENUM)).build();

        final MenuItem annotationItem = Builder.create(langConfig.get("menu.file.new.annotation"))
                .setGraphic(Components.createMenuGraphics("/assets/img/annotation.png")).build();

        newMenu.getItems().addAll(javaProjectItem, projectItem, javaWorkingSetItem, new SeparatorMenuItem(),
                sourceFolderItem, packageItem, file, folderItem, new SeparatorMenuItem(), clazzItem, interfaceItem, enumItem,
                annotationItem);

        fileMenu.getItems().add(newMenu);
    }

    public void createFileGenerateMenu(Menu fileMenu) {
        final Menu generateMenu = new Menu(langConfig.get("menu.file.generate"));

        final MenuItem forgeModItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.generate.forgemod"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem fabricModItem = RailroadMenuItem.Builder.create(langConfig.get("menu.file.generate.fabricmod"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem itemModel = RailroadMenuItem.Builder.create(langConfig.get("menu.file.generate.itemmodel"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem blockModel = RailroadMenuItem.Builder.create(langConfig.get("menu.file.generate.blockmodel"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem blockState = RailroadMenuItem.Builder.create(langConfig.get("menu.file.generate.blockstate"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        generateMenu.getItems().addAll(forgeModItem, fabricModItem, new SeparatorMenuItem(), itemModel, blockModel,
                blockState);

        fileMenu.getItems().add(generateMenu);
    }

    public void createEditMenu(Menu editMenu) {
        final MenuItem undo = Builder.create(langConfig.get("menu.edit.undo"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem redo = Builder.create(langConfig.get("menu.edit.redo"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem cut = Builder.create(langConfig.get("menu.edit.cut"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem copy = Builder.create(langConfig.get("menu.edit.copy"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem paste = Builder.create(langConfig.get("menu.edit.paste"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem delete = Builder.create(langConfig.get("menu.edit.delete"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem selectAll = Builder.create(langConfig.get("menu.edit.selectAll"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem findReplace = Builder.create(langConfig.get("menu.edit.findReplace"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(), delete,
                selectAll, new SeparatorMenuItem(), findReplace);
    }

    public void createSearchMenu(Menu searchMenu) {
        final MenuItem search = Builder.create(langConfig.get("menu.search.search"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final Menu textMenu = new Menu(langConfig.get("menu.search.text"));

        final MenuItem workspace = Builder.create(langConfig.get("menu.search.text.workspace"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem project = Builder.create(langConfig.get("menu.search.text.project"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem file = Builder.create(langConfig.get("menu.search.text.file"))
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        searchMenu.getItems().addAll(search, new SeparatorMenuItem(), textMenu);
        textMenu.getItems().addAll(workspace, project, file);
    }
}
