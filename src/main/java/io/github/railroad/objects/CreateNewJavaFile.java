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
import static java.util.concurrent.ForkJoinPool.commonPool;

public final class CreateNewJavaFile extends AbstractNewFileWindow {

    public CreateNewJavaFile(String title, String message) {
        super(title, message);
    }

    @Override public boolean fileDialogBox(Stage window) {
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

    //TODO: get rid of this
    private final Object saveLock = new Object();

    @Override protected Button saveFile(Stage window) {
        return makeButton(message).action(event -> {
            synchronized (saveLock) {
                if (filePath == null || filePath.equals("File Path")) {
                    window.close();
                    throw new RuntimeException("Input error");
                }

                commonPool().execute(() -> {
                    try {
                        synchronized (saveLock) {
                            final Path path = get(filePath);
                            if (!exists(path)) createFile(path);
                            //TODO: restore functionality
                            //writeString(path, type.apply(path));
                            window.close();
                        }
                    } catch (IOException reason) {
                        throw new RuntimeException(reason);
                    }
                });
            }
        }).get();
    }
}
