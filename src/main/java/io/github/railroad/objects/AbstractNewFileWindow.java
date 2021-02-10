package io.github.railroad.objects;

import io.github.railroad.utility.UIUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static java.nio.file.Files.createFile;
import static java.nio.file.Paths.get;

public abstract class AbstractNewFileWindow {
    public String filePath;
    public Label pathName;
    public String title;
    public String message;

    public AbstractNewFileWindow(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public boolean fileDialogBox(Stage window) {
        final FileChooser fileChooser = new FileChooser();

        final File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            filePath = file.getAbsolutePath();
            pathName.setText(filePath);
            return true;         // Return true if file is created
        }
        //TODO make a remembering classpath
        fileChooser.setInitialDirectory(new File(""));
        return false;            // Return false if "cancel" is selected
    }

    protected Button saveFile(Stage window) {
        return UIUtils.createButton(message, event -> {
            if (filePath == null || filePath.equals("File Path")) {
                System.out.println("Input error");
                window.close();
                return;//failed to input
            }
            try {
                createFile(get(filePath));
            } catch (IOException reason) {
                throw new RuntimeException(reason);
            }
            window.close();
        });
    }

    public void makeWindow() {
        final Stage window = new Stage();
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(100);
        window.setResizable(false);

        pathName = new Label("File Path");

        if (fileDialogBox(window)) { // File is successfully created
            createConfirmationWindow(window);
        } else {
            // Do nothing because user has clicked "cancel"
        }

    }

    // Confirmation window that appears after creating a file
    private void createConfirmationWindow(Stage window) {
        final Button yesBtn = saveFile(window);
        final VBox layout = new VBox(10);
        layout.getChildren().addAll(pathName, yesBtn);
        layout.setAlignment(Pos.CENTER);

        final Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
