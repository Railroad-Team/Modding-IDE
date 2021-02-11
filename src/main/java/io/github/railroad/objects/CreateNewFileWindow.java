package io.github.railroad.objects;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CreateNewFileWindow extends AbstractNewFileWindow {
    public CreateNewFileWindow(String title, String message) {
        super(title, message);
    }

    @Override
    public boolean fileDialogBox(Stage window) {
        final FileChooser fileChooser = new FileChooser();

        final File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            filePath = file.getAbsolutePath();
            pathName.setText(filePath);
            return true;         // Return true if file is created
        }
        fileChooser.setInitialDirectory(new File(""));
        return false;            // Return false if "cancel" is selected
    }
}