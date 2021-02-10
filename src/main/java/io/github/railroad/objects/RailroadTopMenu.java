package io.github.railroad.objects;

import io.github.railroad.utility.Components;
import io.github.railroad.utility.Components.MenuItems;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

// TODO make assets for all the menu items!
public final class RailroadTopMenu extends MenuBar {

    public void createMenu() {
        final Menu fileMenu = new Menu("File");
        createFileMenu(fileMenu);

        final Menu editMenu = new Menu("Edit");
        createEditMenu(editMenu);

        final Menu searchMenu = new Menu("Search");
        createSearchMenu(searchMenu);

        final Menu runMenu = new Menu("Run");
        final Menu viewMenu = new Menu("View");
        final Menu helpMenu = new Menu("Help");
        getMenus().addAll(fileMenu, editMenu, searchMenu, runMenu, viewMenu, helpMenu);
    }

    public void createFileMenu(Menu fileMenu) {
        createFileNewMenu(fileMenu);
        createFileGenerateMenu(fileMenu);
        fileMenu.getItems().add(new SeparatorMenuItem());

        final MenuItem openItem = MenuItems.makeMenuItem("Open")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem openRecentItem = MenuItems.makeMenuItem("Open recent")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem saveItem = MenuItems.makeMenuItem("Save")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem saveAsItem = MenuItems.makeMenuItem("Save as")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem exportItem = MenuItems.makeMenuItem("Export file")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        fileMenu.getItems().addAll(openItem, openRecentItem, new SeparatorMenuItem(), saveItem, saveAsItem, exportItem);
    }

    public void createFileNewMenu(Menu fileMenu) {
        final Menu newMenu = new Menu("New");

        final MenuItem javaProjectItem = MenuItems.makeMenuItem("Java project")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem projectItem = MenuItems.makeMenuItem("Project")
                .graphic(Components.createMenuGraphics("/assets/img/project.png")).get();

        final MenuItem javaWorkingSetItem = MenuItems.makeMenuItem("Working set")
                .graphic(Components.createMenuGraphics("/assets/img/working_set.png")).get();

        final MenuItem packageItem = MenuItems.makeMenuItem("Package")
                .graphic(Components.createMenuGraphics("/assets/img/package.png")).get();

        final MenuItem sourceFolderItem = MenuItems.makeMenuItem("Source folder")
                .graphic(Components.createMenuGraphics("/assets/img/source_folder.png")).get();

        final MenuItem file = MenuItems.makeMenuItem("File")
                .graphic(Components.createMenuGraphics("/assets/img/file.png"))
                .action(event -> new CreateNewFileWindow("Create New File", "Done")).get();

        final MenuItem folderItem = MenuItems.makeMenuItem("Folder")
                .graphic(Components.createMenuGraphics("/assets/img/folder.png")).get();

        final MenuItem clazzItem = MenuItems.makeMenuItem("Class")
                .graphic(Components.createMenuGraphics("/assets/img/class.png"))
                .action(event -> new CreateNewJavaFile("Create New Class", "Done", ClassType.CLASS)).get();

        final MenuItem interfaceItem = MenuItems.makeMenuItem("Interface")
                .graphic(Components.createMenuGraphics("/assets/img/interface.png"))
                .action(event -> new CreateNewJavaFile("Create New Interface", "Done", ClassType.INTERFACE)).get();

        final MenuItem enumItem = MenuItems.makeMenuItem("Enum")
                .graphic(Components.createMenuGraphics("/assets/img/enum.png"))
                .action(event -> new CreateNewJavaFile("Create New Enum", "Done", ClassType.ENUM)).get();

        final MenuItem annotationItem = MenuItems.makeMenuItem("Annotation")
                .graphic(Components.createMenuGraphics("/assets/img/annotation.png")).get();

        newMenu.getItems().addAll(javaProjectItem, projectItem, javaWorkingSetItem, new SeparatorMenuItem(),
                sourceFolderItem, packageItem, file, folderItem, new SeparatorMenuItem(), clazzItem, interfaceItem, enumItem,
                annotationItem);

        fileMenu.getItems().add(newMenu);
    }

    public void createFileGenerateMenu(Menu fileMenu) {
        final Menu generateMenu = new Menu("Generate");

        final MenuItem forgeModItem = MenuItems.makeMenuItem("Forge mod")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem fabricModItem = MenuItems.makeMenuItem("Fabric mod")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem itemModel = MenuItems.makeMenuItem("Item model")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem blockModel = MenuItems.makeMenuItem("Block model")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem blockState = MenuItems.makeMenuItem("Block state")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        generateMenu.getItems().addAll(forgeModItem, fabricModItem, new SeparatorMenuItem(), itemModel, blockModel,
                blockState);

        fileMenu.getItems().add(generateMenu);
    }

    public void createEditMenu(Menu editMenu) {
        final MenuItem undo = MenuItems.makeMenuItem("Undo")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem redo = MenuItems.makeMenuItem("Redo")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem cut = MenuItems.makeMenuItem("Cut")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem copy = MenuItems.makeMenuItem("Copy")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem paste = MenuItems.makeMenuItem("Paste")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem delete = MenuItems.makeMenuItem("Delete")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem selectAll = MenuItems.makeMenuItem("Select all")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem findReplace = MenuItems.makeMenuItem("Find / Replace")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(), delete,
                selectAll, new SeparatorMenuItem(), findReplace);
    }

    public void createSearchMenu(Menu searchMenu) {
        final MenuItem search = MenuItems.makeMenuItem("Search")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final Menu textMenu = new Menu("Text");

        final MenuItem workspace = MenuItems.makeMenuItem("Workspace")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem project = MenuItems.makeMenuItem("Project")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem file = MenuItems.makeMenuItem("File")
                .graphic(Components.createMenuGraphics("/assets/img/java_project.png")).get();

        searchMenu.getItems().addAll(search, new SeparatorMenuItem(), textMenu);
        textMenu.getItems().addAll(workspace, project, file);
    }
}