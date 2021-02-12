package io.github.railroad.objects;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

import static io.github.railroad.utility.Components.Buttons.makeButton;
import static java.nio.file.Files.createFile;
import static java.nio.file.Files.exists;
import static java.nio.file.Paths.get;
import static java.util.concurrent.ForkJoinPool.commonPool;

public final class CreateNewJavaFile extends NewFileWindow {
    public CreateNewJavaFile(String title, String message) {
        super(title, message, "*.java");
    }

    //TODO: get rid of this
    private final Object saveLock = new Object();

    @Override
    protected Button saveFile(Stage window) {
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
