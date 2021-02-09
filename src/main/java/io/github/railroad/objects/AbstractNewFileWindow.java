package io.github.railroad.objects;

import io.github.railroad.utility.FileUtils;
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

public abstract class AbstractNewFileWindow {
    public String filePath;
    public Label pathName;
    public String title;
    public String message;
    protected int type;

    public AbstractNewFileWindow(String title, String message, int type) {
        this.title = title;
        this.message = message;
        this.type = type;
        this.makeWindow();
    }

    public AbstractNewFileWindow(String title, String message) {
        this.title = title;
        this.message = message;
        this.makeWindow();
    }

    public void fileDialogBox(Stage window) {
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            filePath = file.getAbsolutePath();
            this.pathName.setText(filePath);
        }
        fileChooser.setInitialDirectory(new File(""));

    }

    protected Button saveFile(Stage window) {
        return UIUtils.createButton(this.message, event -> {
            FileUtils.createNewFile(filePath);
            window.close();
        });
    }

    public void makeWindow() {
        Stage window = new Stage();
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(this.title);
        window.setMinWidth(250);
        window.setMinHeight(100);
        window.setResizable(false);

        this.pathName = new Label("File Path");

        Button yesBtn = saveFile(window);
        fileDialogBox(window);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(pathName, yesBtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
