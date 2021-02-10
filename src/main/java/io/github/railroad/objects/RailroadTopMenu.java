package io.github.railroad.objects;

import io.github.railroad.objects.RailroadMenuItem.Builder;
import io.github.railroad.utility.Components;
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

        final MenuItem openItem = RailroadMenuItem.Builder.create("Open")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem openRecentItem = RailroadMenuItem.Builder.create("Open recent")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem saveItem = RailroadMenuItem.Builder.create("Save")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem saveAsItem = RailroadMenuItem.Builder.create("Save as")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem exportItem = RailroadMenuItem.Builder.create("Export file")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        fileMenu.getItems().addAll(openItem, openRecentItem, new SeparatorMenuItem(), saveItem, saveAsItem, exportItem);
    }

    public void createFileNewMenu(Menu fileMenu) {
        final Menu newMenu = new Menu("New");

        final MenuItem javaProjectItem = Builder.create("Java project")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem projectItem = Builder.create("Project")
                .setGraphic(Components.createMenuGraphics("/assets/img/project.png")).build();

        final MenuItem javaWorkingSetItem = Builder.create("Working set")
                .setGraphic(Components.createMenuGraphics("/assets/img/working_set.png")).build();

        final MenuItem packageItem = Builder.create("Package")
                .setGraphic(Components.createMenuGraphics("/assets/img/package.png")).build();

        final MenuItem sourceFolderItem = Builder.create("Source folder")
                .setGraphic(Components.createMenuGraphics("/assets/img/source_folder.png")).build();

        final MenuItem file = Builder.create("File")
                .setGraphic(Components.createMenuGraphics("/assets/img/file.png"))
                .setActionEvent(event -> new CreateNewFileWindow("Create New File", "Done")).build();

        final MenuItem folderItem = Builder.create("Folder")
                .setGraphic(Components.createMenuGraphics("/assets/img/folder.png")).build();

        final MenuItem clazzItem = Builder.create("Class")
                .setGraphic(Components.createMenuGraphics("/assets/img/class.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Class", "Done", ClassType.CLASS)).build();

        final MenuItem interfaceItem = Builder.create("Interface")
                .setGraphic(Components.createMenuGraphics("/assets/img/interface.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Interface", "Done", ClassType.INTERFACE)).build();

        final MenuItem enumItem = Builder.create("Enum")
                .setGraphic(Components.createMenuGraphics("/assets/img/enum.png"))
                .setActionEvent(event -> new CreateNewJavaFile("Create New Enum", "Done", ClassType.ENUM)).build();

        final MenuItem annotationItem = Builder.create("Annotation")
                .setGraphic(Components.createMenuGraphics("/assets/img/annotation.png")).build();

        newMenu.getItems().addAll(javaProjectItem, projectItem, javaWorkingSetItem, new SeparatorMenuItem(),
                sourceFolderItem, packageItem, file, folderItem, new SeparatorMenuItem(), clazzItem, interfaceItem, enumItem,
                annotationItem);

        fileMenu.getItems().add(newMenu);
    }

    public void createFileGenerateMenu(Menu fileMenu) {
        final Menu generateMenu = new Menu("Generate");

        final MenuItem forgeModItem = RailroadMenuItem.Builder.create("Forge mod")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem fabricModItem = RailroadMenuItem.Builder.create("Fabric mod")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem itemModel = RailroadMenuItem.Builder.create("Item model")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem blockModel = RailroadMenuItem.Builder.create("Block model")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem blockState = RailroadMenuItem.Builder.create("Block state")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        generateMenu.getItems().addAll(forgeModItem, fabricModItem, new SeparatorMenuItem(), itemModel, blockModel,
                blockState);

        fileMenu.getItems().add(generateMenu);
    }

    public void createEditMenu(Menu editMenu) {
        final MenuItem undo = Builder.create("Undo")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem redo = Builder.create("Redo")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem cut = Builder.create("Cut")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem copy = Builder.create("Copy")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem paste = Builder.create("Paste")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem delete = Builder.create("Delete")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem selectAll = Builder.create("Select all")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem findReplace = Builder.create("Find / Replace")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(), delete,
                selectAll, new SeparatorMenuItem(), findReplace);
    }

    public void createSearchMenu(Menu searchMenu) {
        final MenuItem search = Builder.create("Search")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final Menu textMenu = new Menu("Text");

        final MenuItem workspace = Builder.create("Workspace")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem project = Builder.create("Project")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        final MenuItem file = Builder.create("File")
                .setGraphic(Components.createMenuGraphics("/assets/img/java_project.png")).build();

        searchMenu.getItems().addAll(search, new SeparatorMenuItem(), textMenu);
        textMenu.getItems().addAll(workspace, project, file);
    }
}