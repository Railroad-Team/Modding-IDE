package io.github.railroad.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import static io.github.railroad.utility.Components.MenuItems.makeMenuItem;
import static io.github.railroad.utility.Components.createImage;
import static io.github.railroad.utility.Terminal.openTerminal;

// TODO make assets for all the menu items!
//TODO: looks like mess
public final class TopMenu extends MenuBar {
    public void createMenu() {
        final Menu fileMenu = new Menu("File");
        createFileMenu(fileMenu);

        final Menu editMenu = new Menu("Edit");
        createEditMenu(editMenu);

        final Menu searchMenu = new Menu("Search");
        createSearchMenu(searchMenu);

        final Menu runMenu = new Menu("Run");
        final Menu viewMenu = new Menu("View");
        createViewMenu(viewMenu);

        final Menu helpMenu = new Menu("Help");
        getMenus().addAll(fileMenu, editMenu, searchMenu, runMenu, viewMenu, helpMenu);
    }

    public void createFileMenu(Menu fileMenu) {
        createFileNewMenu(fileMenu);
        createFileGenerateMenu(fileMenu);
        fileMenu.getItems().add(new SeparatorMenuItem());

        final MenuItem open = makeMenuItem("Open").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem recent = makeMenuItem("Open recent").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem save = makeMenuItem("Save").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem saveAs = makeMenuItem("Save as").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem export = makeMenuItem("Export file").graphic(createImage("/assets/img/java_project.png")).get();

        fileMenu.getItems().addAll(open, recent, new SeparatorMenuItem(), save, saveAs, export);
    }

    public void createFileNewMenu(Menu fileMenu) {
        final Menu newMenu = new Menu("New");
        final MenuItem javaProject = makeMenuItem("Java project")
                .graphic(createImage("/assets/img/java_project.png"))
                .get();

        final MenuItem project = makeMenuItem("Project")
                .graphic(createImage("/assets/img/project.png"))
                .get();

        final MenuItem workingSet = makeMenuItem("Working set")
                .graphic(createImage("/assets/img/working_set.png"))
                .get();

        final MenuItem packageItem = makeMenuItem("Package")
                .graphic(createImage("/assets/img/package.png"))
                .get();

        final MenuItem sourceFolder = makeMenuItem("Source folder")
                .graphic(createImage("/assets/img/source_folder.png"))
                .get();

      /*  final MenuItem file = makeMenuItem("File")
                .graphic(createImage("/assets/img/file.png"))
                .action(event -> new NewFileWindow("Create New File", "Done", ""))
                .get();

        final MenuItem folderItem = makeMenuItem("Folder")
                .graphic(createImage("/assets/img/folder.png"))
                .get();

        final MenuItem classItem = makeMenuItem("Class")
                .graphic(createImage("/assets/img/class.png"))
                .action(event -> new CreateNewJavaFile("Create New Class", "Done"))
                .get();

        final MenuItem interfaceItem = makeMenuItem("Interface")
                .graphic(createImage("/assets/img/interface.png"))
                .action(event -> new CreateNewJavaFile("Create New Interface", "Done"))
                .get();

        final MenuItem enumItem = makeMenuItem("Enum")
                .graphic(createImage("/assets/img/enum.png"))
                .action(event -> new CreateNewJavaFile("Create New Enum", "Done"))
                .get();
*/
        final MenuItem annotationItem = makeMenuItem("Annotation")
                .graphic(createImage("/assets/img/annotation.png"))
                .get();

        newMenu.getItems().addAll(javaProject, project, workingSet, new SeparatorMenuItem(),
                sourceFolder, packageItem, new SeparatorMenuItem(),
                annotationItem);

        fileMenu.getItems().add(newMenu);
    }

    public void createFileGenerateMenu(Menu fileMenu) {
        final Menu generateMenu = new Menu("Generate");
        final MenuItem forgeModItem = makeMenuItem("Forge mod").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem fabricModItem = makeMenuItem("Fabric mod").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem itemModel = makeMenuItem("Item model").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem blockModel = makeMenuItem("Block model").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem blockState = makeMenuItem("Block state").graphic(createImage("/assets/img/java_project.png")).get();

        generateMenu.getItems().addAll(forgeModItem, fabricModItem, new SeparatorMenuItem(), itemModel, blockModel, blockState);
        fileMenu.getItems().add(generateMenu);
    }

    public void createEditMenu(Menu editMenu) {
        final MenuItem undo = makeMenuItem("Undo").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem redo = makeMenuItem("Redo").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem cut = makeMenuItem("Cut").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem copy = makeMenuItem("Copy").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem paste = makeMenuItem("Paste").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem delete = makeMenuItem("Delete").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem selectAll = makeMenuItem("Select all").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem findReplace = makeMenuItem("Find / Replace").graphic(createImage("/assets/img/java_project.png")).get();

        editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(), delete,
                selectAll, new SeparatorMenuItem(), findReplace);
    }

    // TODO: Give a suitable location to open terminal
    public void createViewMenu(Menu viewMenu) {
        final MenuItem terminal = makeMenuItem("Open Terminal")
                .graphic(createImage("/assets/img/java_project.png"))
                .action(event -> openTerminal()).get();

        viewMenu.getItems().addAll(terminal);
    }

    public void createSearchMenu(Menu searchMenu) {
        final MenuItem search = makeMenuItem("Search").graphic(createImage("/assets/img/java_project.png")).get();
        final Menu textMenu = new Menu("Text");
        final MenuItem workspace = makeMenuItem("Workspace").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem project = makeMenuItem("Project").graphic(createImage("/assets/img/java_project.png")).get();
        final MenuItem file = makeMenuItem("File").graphic(createImage("/assets/img/java_project.png")).get();

        searchMenu.getItems().addAll(search, new SeparatorMenuItem(), textMenu);
        textMenu.getItems().addAll(workspace, project, file);
    }
}