package io.github.railroad.objects;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import static io.github.railroad.utility.Components.MenuItems.makeMenuItem;
import static io.github.railroad.utility.Components.createMenuGraphics;

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

        final MenuItem open = makeMenuItem("Open").graphic(createMenuGraphics("/assets/img/java_project.png")).get();
        final MenuItem recent = makeMenuItem("Open recent").graphic(createMenuGraphics("/assets/img/java_project.png")).get();
        final MenuItem save = makeMenuItem("Save").graphic(createMenuGraphics("/assets/img/java_project.png")).get();
        final MenuItem saveAs = makeMenuItem("Save as").graphic(createMenuGraphics("/assets/img/java_project.png")).get();
        final MenuItem export = makeMenuItem("Export file").graphic(createMenuGraphics("/assets/img/java_project.png")).get();
        fileMenu.getItems().addAll(open, recent, new SeparatorMenuItem(), save, saveAs, export);
    }

    public void createFileNewMenu(Menu fileMenu) {
        final Menu newMenu = new Menu("New");
        final MenuItem javaProjectItem = makeMenuItem("Java project")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem projectItem = makeMenuItem("Project")
                .graphic(createMenuGraphics("/assets/img/project.png")).get();

        final MenuItem javaWorkingSetItem = makeMenuItem("Working set")
                .graphic(createMenuGraphics("/assets/img/working_set.png")).get();

        final MenuItem packageItem = makeMenuItem("Package")
                .graphic(createMenuGraphics("/assets/img/package.png")).get();

        final MenuItem sourceFolderItem = makeMenuItem("Source folder")
                .graphic(createMenuGraphics("/assets/img/source_folder.png")).get();

        final MenuItem file = makeMenuItem("File")
                .graphic(createMenuGraphics("/assets/img/file.png"))
                .action(event -> new CreateNewFileWindow("Create New File", "Done")).get();

        final MenuItem folderItem = makeMenuItem("Folder")
                .graphic(createMenuGraphics("/assets/img/folder.png")).get();

        final MenuItem clazzItem = makeMenuItem("Class")
                .graphic(createMenuGraphics("/assets/img/class.png"))
                .action(event -> new CreateNewJavaFile("Create New Class", "Done", ClassType.CLASS)).get();

        final MenuItem interfaceItem = makeMenuItem("Interface")
                .graphic(createMenuGraphics("/assets/img/interface.png"))
                .action(event -> new CreateNewJavaFile("Create New Interface", "Done", ClassType.INTERFACE)).get();

        final MenuItem enumItem = makeMenuItem("Enum")
                .graphic(createMenuGraphics("/assets/img/enum.png"))
                .action(event -> new CreateNewJavaFile("Create New Enum", "Done", ClassType.ENUM)).get();

        final MenuItem annotationItem = makeMenuItem("Annotation")
                .graphic(createMenuGraphics("/assets/img/annotation.png")).get();

        newMenu.getItems().addAll(javaProjectItem, projectItem, javaWorkingSetItem, new SeparatorMenuItem(),
                sourceFolderItem, packageItem, file, folderItem, new SeparatorMenuItem(), clazzItem, interfaceItem, enumItem,
                annotationItem);

        fileMenu.getItems().add(newMenu);
    }

    public void createFileGenerateMenu(Menu fileMenu) {
        final Menu generateMenu = new Menu("Generate");

        final MenuItem forgeModItem = makeMenuItem("Forge mod")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem fabricModItem = makeMenuItem("Fabric mod")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem itemModel = makeMenuItem("Item model")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem blockModel = makeMenuItem("Block model")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem blockState = makeMenuItem("Block state")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        generateMenu.getItems().addAll(forgeModItem, fabricModItem, new SeparatorMenuItem(), itemModel, blockModel,
                blockState);

        fileMenu.getItems().add(generateMenu);
    }

    public void createEditMenu(Menu editMenu) {
        final MenuItem undo = makeMenuItem("Undo")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem redo = makeMenuItem("Redo")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem cut = makeMenuItem("Cut")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem copy = makeMenuItem("Copy")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem paste = makeMenuItem("Paste")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem delete = makeMenuItem("Delete")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem selectAll = makeMenuItem("Select all")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem findReplace = makeMenuItem("Find / Replace")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(), delete,
                selectAll, new SeparatorMenuItem(), findReplace);
    }

    public void createSearchMenu(Menu searchMenu) {
        final MenuItem search = makeMenuItem("Search")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final Menu textMenu = new Menu("Text");

        final MenuItem workspace = makeMenuItem("Workspace")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem project = makeMenuItem("Project")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        final MenuItem file = makeMenuItem("File")
                .graphic(createMenuGraphics("/assets/img/java_project.png")).get();

        searchMenu.getItems().addAll(search, new SeparatorMenuItem(), textMenu);
        textMenu.getItems().addAll(workspace, project, file);
    }
}