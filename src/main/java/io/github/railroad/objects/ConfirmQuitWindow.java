package io.github.railroad.objects;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static io.github.railroad.Railroad.darkMode;
import static io.github.railroad.utility.Components.ButtonFactory.makeButton;
import static io.github.railroad.utility.Components.StageFactory.makeStage;
import static io.github.railroad.utility.Components.VBoxFactory.makeVBox;

public interface ConfirmQuitWindow {
    static void displayQuitWindow(Stage windowToClose, String title, String message) {
        Stage window = makeStage()
                .center()
                .modality(Modality.APPLICATION_MODAL)
                .title(title)
                .minWidth(250)
                .minHeight(100)
                .resizable(false).get();

        Label label = new Label(message);

        Button yesButton = makeButton("Yes").action(event -> {
            windowToClose.close();
            window.close();
        }).get();

        Button noButton = makeButton("No").action(event -> window.close()).get();

        VBox layout = makeVBox(10)
                .children(label, yesButton, noButton)
                .alignment(Pos.CENTER).get();

        Scene scene = new Scene(layout);

        if (darkMode) scene.getStylesheets().add("assets/styles/mode/darkmode.css");
        else scene.getStylesheets().add("assets/styles/mode/lightmode.css");

        window.setScene(scene);
        window.showAndWait();
    }
}
