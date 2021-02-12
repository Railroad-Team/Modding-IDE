package io.github.railroad.objects;

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

import static io.github.railroad.utility.Components.Buttons.makeButton;
import static io.github.railroad.utility.Components.Stages.makeStage;
import static java.nio.file.Files.createFile;
import static java.nio.file.Paths.get;

//TODO: refactor this

public class NewFileWindow {
    public final String title;
    public final String message;
    /**
     * template: *.<file extension\
     */
    public String filePath;
    public Label pathName;
    public String fileExtension;

    public NewFileWindow(String title, String message, String fileExtension) {
        this.title = title;
        this.message = message;
        this.fileExtension = fileExtension;
    }

    public boolean fileDialogBox(Stage window) {
        final FileChooser fileChooser = new FileChooser();

        final FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter(
                String.format("%s Files", fileExtension),
                fileExtension);

        fileChooser.getExtensionFilters().add(fileFilter);
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
        return makeButton(message).action(event -> {
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
        }).get();
    }

    public void makeWindow() {
        final Stage window = makeStage()
                .center()
                .modality(Modality.APPLICATION_MODAL)
                .title(title)
                .minWidth(250)
                .minHeight(100)
                .resizable(false)
                .get();

        pathName = new Label("File Path");

        if (fileDialogBox(window)) { // File is successfully created
            createConfirmationWindow(window);
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
