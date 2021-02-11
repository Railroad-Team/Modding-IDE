package io.github.railroad.objects;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

import static io.github.railroad.utility.Components.Buttons.makeButton;
import static io.github.railroad.utility.Components.Stages.makeStage;
import static javafx.stage.Modality.APPLICATION_MODAL;

/**
 * @author temedy
 */
interface ConfirmWindow {
    static void displayWindow(String title, String message) {
        final AtomicBoolean result = new AtomicBoolean();

        final Stage window = makeStage().center().modality(APPLICATION_MODAL).title(title).minWidth(250).minHeight(100).resizable(false).get();
        final Label label = new Label(message);

        final Button yes = makeButton("Yes").action(event -> result.set(true)).get();
        final Button no = makeButton("No").action(event -> result.set(false)).get();

        final VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yes, no);
        layout.setAlignment(Pos.CENTER);

        final Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}