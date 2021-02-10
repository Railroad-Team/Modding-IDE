package io.github.railroad.objects;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static io.github.railroad.utility.Components.Buttons.makeButton;
import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;

public final class CreateNewJavaFile extends AbstractNewFileWindow {

    public final ClassType type;

    public CreateNewJavaFile(String title, String message, ClassType type) {
        super(title, message);
        this.type = type;
        /*
         * Type 1 is Class, Type 2 is Interface, Type 3 is Enums, Type 4 is Annotation
         */
    }

    @Override
    public boolean fileDialogBox(Stage window) {
        final FileChooser fileChooser = new FileChooser();
        final FileChooser.ExtensionFilter javaFilter = new FileChooser.ExtensionFilter("Java Files", "*.java");

        fileChooser.getExtensionFilters().add(javaFilter);
        final File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            filePath = file.getAbsolutePath();
            pathName.setText(filePath);
        }

        fileChooser.setInitialDirectory(new File(""));
        return false;
    }

    //TODO make it open the file in editor after saving
    //TODO: Fix this system

    @Override
    protected Button saveFile(Stage window) {
        return makeButton(message).action(event -> {
            //please put this somewhere else in the event that the user fails to select a path
            if (filePath == null || filePath.equals("File Path")) {
                window.close();
                throw new RuntimeException("Input error");
            }

            try {
                final Path path = get(filePath);
                if (!exists(path)) createFile(path);
                writeString(path, type.apply(path));
                window.close();

            } catch (IOException reason) {
                throw new RuntimeException(reason);
            }
        }).get();
    }
}
