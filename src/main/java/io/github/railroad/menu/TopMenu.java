package io.github.railroad.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import static io.github.railroad.lang.LangManger.ENGLISH;
import static io.github.railroad.lang.LangManger.getLocalization;
import static io.github.railroad.utility.Components.MenuItemFactory.makeMenuItem;
import static io.github.railroad.utility.Components.createImage;
import static io.github.railroad.utility.Terminal.openTerminal;

// TODO make assets for all the menu items!
//TODO: looks like mess
public final class TopMenu extends MenuBar {
    public void createMenu() {
        final Menu fileMenu = new Menu(getLocalization("menu.file", ENGLISH));
        createFileMenu(fileMenu);

        final Menu editMenu = new Menu(getLocalization("menu.edit", ENGLISH));
        createEditMenu(editMenu);

        final Menu searchMenu = new Menu(getLocalization("menu.search", ENGLISH));
        createSearchMenu(searchMenu);

        final Menu runMenu = new Menu(getLocalization("menu.run", ENGLISH));
        final Menu viewMenu = new Menu(getLocalization("menu.view", ENGLISH));
        createViewMenu(viewMenu);

        final Menu helpMenu = new Menu(getLocalization("menu.help", ENGLISH));
        getMenus().addAll(fileMenu, editMenu, searchMenu, runMenu, viewMenu, helpMenu);
    }

    public void createFileMenu(Menu fileMenu) {
        createFileNewMenu(fileMenu);
        createFileGenerateMenu(fileMenu);
        fileMenu.getItems().add(new SeparatorMenuItem());

        final MenuItem open = makeMenuItem(getLocalization("menu.file.open", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem recent = makeMenuItem(getLocalization("menu.file.open_recent", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem save = makeMenuItem(getLocalization("menu.file.save", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem saveAs = makeMenuItem(getLocalization("menu.file.save_as", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem export = makeMenuItem(getLocalization("menu.file.export", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        fileMenu.getItems().addAll(open, recent, new SeparatorMenuItem(), save, saveAs, export);
    }

    public void createFileNewMenu(Menu fileMenu) {
        final Menu newMenu = new Menu(getLocalization("menu.file.new", ENGLISH));
        final MenuItem javaProject = makeMenuItem(getLocalization("menu.file.new.java_project", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png"))
                .get();

        final MenuItem project = makeMenuItem(getLocalization("menu.file.new.project", ENGLISH))
                .graphic(createImage("/assets/img/project.png"))
                .get();

        final MenuItem workingSet = makeMenuItem(getLocalization("menu.file.new.java_working_set", ENGLISH))
                .graphic(createImage("/assets/img/working_set.png"))
                .get();

        final MenuItem packageItem = makeMenuItem(getLocalization("menu.file.new.package", ENGLISH))
                .graphic(createImage("/assets/img/package.png"))
                .get();

        final MenuItem sourceFolder = makeMenuItem(getLocalization("menu.file.new.source_folder", ENGLISH))
                .graphic(createImage("/assets/img/source_folder.png"))
                .get();

        // final MenuItem file = makeMenuItem(getLocalization("menu.file.new.file", ENGLISH))
        //         .graphic(createImage("/assets/img/file.png"))
        //         .action(event -> new NewFileWindow("Create New File", "Done", ""))
        //         .get();
        //
        // final MenuItem folderItem = makeMenuItem(getLocalization("menu.file.new.folder", ENGLISH))
        //         .graphic(createImage("/assets/img/folder.png"))
        //         .get();
        //
        // final MenuItem classItem = makeMenuItem(getLocalization("menu.file.new.class", ENGLISH))
        //         .graphic(createImage("/assets/img/class.png"))
        //         .action(event -> new CreateNewJavaFile("Create New Class", "Done"))
        //         .get();
        //
        // final MenuItem interfaceItem = makeMenuItem(getLocalization("menu.file.new.interface", ENGLISH))
        //         .graphic(createImage("/assets/img/interface.png"))
        //         .action(event -> new CreateNewJavaFile("Create New Interface", "Done"))
        //         .get();
        //
        // final MenuItem enumItem = makeMenuItem(getLocalization("menu.file.new.enum", ENGLISH))
        //         .graphic(createImage("/assets/img/enum.png"))
        //         .action(event -> new CreateNewJavaFile("Create New Enum", "Done"))
        //         .get();

        final MenuItem annotationItem = makeMenuItem(getLocalization("menu.file.new.annotation", ENGLISH))
                .graphic(createImage("/assets/img/annotation.png"))
                .get();

        newMenu.getItems().addAll(javaProject, project, workingSet, new SeparatorMenuItem(),
                sourceFolder, packageItem, new SeparatorMenuItem(),
                annotationItem);

        fileMenu.getItems().add(newMenu);
    }

    public void createFileGenerateMenu(Menu fileMenu) {
        final Menu generateMenu = new Menu(getLocalization("menu.file.generate", ENGLISH));
        final MenuItem forgeMod = makeMenuItem(getLocalization("menu.file.generate.forge_mod", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem fabricMod = makeMenuItem(getLocalization("menu.file.generate.fabric_mod", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem itemModel = makeMenuItem(getLocalization("menu.file.generate.item_model", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem blockModel = makeMenuItem(getLocalization("menu.file.generate.block_model", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem blockState = makeMenuItem(getLocalization("menu.file.generate.block_state", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        generateMenu.getItems().addAll(forgeMod, fabricMod, new SeparatorMenuItem(), itemModel, blockModel, blockState);
        fileMenu.getItems().add(generateMenu);
    }

    public void createEditMenu(Menu editMenu) {
        final MenuItem undo = makeMenuItem(getLocalization("menu.edit.undo", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem redo = makeMenuItem(getLocalization("menu.edit.redo", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem cut = makeMenuItem(getLocalization("menu.edit.cut", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem copy = makeMenuItem(getLocalization("menu.edit.copy", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem paste = makeMenuItem(getLocalization("menu.edit.paste", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem delete = makeMenuItem(getLocalization("menu.edit.delete", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem selectAll = makeMenuItem(getLocalization("menu.edit.select_all", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem findReplace = makeMenuItem(getLocalization("menu.edit.find_replace", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

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
        final MenuItem search = makeMenuItem(getLocalization("menu.search.search", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final Menu textMenu = new Menu(getLocalization("menu.search.text", ENGLISH));

        final MenuItem workspace = makeMenuItem(getLocalization("menu.search.text.workspace", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem project = makeMenuItem(getLocalization("menu.search.text.project", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        final MenuItem file = makeMenuItem(getLocalization("menu.search.text.file", ENGLISH))
                .graphic(createImage("/assets/img/java_project.png")).get();

        searchMenu.getItems().addAll(search, new SeparatorMenuItem(), textMenu);
        textMenu.getItems().addAll(workspace, project, file);
    }
}