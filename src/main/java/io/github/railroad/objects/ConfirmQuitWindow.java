package io.github.railroad.objects;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

import static io.github.railroad.Railroad.darkMode;
import static io.github.railroad.utility.Components.Buttons.makeButton;
import static io.github.railroad.utility.Components.Stages.makeStage;
import static io.github.railroad.utility.Components.VBoxes.makeVBox;

public interface ConfirmQuitWindow {
    static boolean displayQuitWindow(String title, String message) {
        AtomicBoolean result = new AtomicBoolean(false);

        Stage window = makeStage()
                .center()
                .modality(Modality.APPLICATION_MODAL)
                .title(title)
                .minWidth(250)
                .minHeight(100)
                .resizable(false).get();

        Label label = new Label(message);

        Button yesButton = makeButton("Yes").action(event -> {
            result.set(true);
            window.close();
        }).get();

        Button noButton = makeButton("No").action(event -> {
            result.set(true);
            window.close();
        }).get();

        VBox layout = makeVBox(10)
                .children(label, yesButton, noButton)
                .alignment(Pos.CENTER).get();

        Scene scene = new Scene(layout);
        window.setScene(scene);

        if (darkMode) scene.getStylesheets().add("assets/styles/mode/darkmode.css");
        else scene.getStylesheets().add("assets/styles/mode/lightmode.css");

        window.setScene(scene);
        window.showAndWait();

        return result.get();
    }
}
