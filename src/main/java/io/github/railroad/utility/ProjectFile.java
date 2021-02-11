package io.github.railroad.utility;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.function.Supplier;

import static io.github.railroad.utility.Components.Buttons.makeButton;
import static io.github.railroad.utility.Components.createImage;

public final class ProjectFile {
    public final String label;
    public final String path;
    public final String extension;

    public ProjectFile(String label, String path, String extension) {
        this.label = label;
        this.path = path;
        this.extension = extension;
    }

    interface FileType extends Supplier<ImageView> {
        FileType CLASS = () -> createImage("assets/img/class.png");
        FileType INTERFACE = () -> createImage("assets/img/interface.png");
        FileType PACKAGE = () -> createImage("assets/img/package.png");
        FileType ENUM = () -> createImage("assets/img/enum.png");
    }

    public static void open(Stage stage, FileType type) {
        final Button finish = makeButton("Finish").action(event -> {

        }).get();
    }
}
