package io.github.railroad.objects;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static io.github.railroad.Railroad.darkMode;
import static io.github.railroad.lang.LangManager.ENGLISH;
import static io.github.railroad.lang.LangManager.getLocalization;
import static io.github.railroad.utility.Components.ButtonFactory.makeButton;
import static io.github.railroad.utility.Components.StageFactory.makeStage;
import static io.github.railroad.utility.Components.VBoxFactory.makeVBox;

/**
 * Creates the quit confirmation window.
 *
 * @author TheOnlyTails
 */
public interface ConfirmQuitWindow {
    /**
     * Creates the quit confirmation window.
     *
     * @param windowToClose the window to be closed if clicked "Yes".
     * @author TheOnlyTails
     */
    static void displayQuitWindow(Stage windowToClose) {
        var window = makeStage()
                .center()
                .modality(Modality.APPLICATION_MODAL)
                .title(getLocalization("dialog.quit", ENGLISH))
                .minWidth(250)
                .minHeight(100)
                .resizable(false).get();

        var label = new Label(getLocalization("dialog.quit.prompt", ENGLISH));

        var yesButton = makeButton("Yes").action(event -> {
            windowToClose.close();
            window.close();
        }).get();

        var noButton = makeButton("No").action(event -> window.close()).get();

        var layout = makeVBox(10)
                .children(label, yesButton, noButton)
                .alignment(Pos.CENTER).get();

        var scene = new Scene(layout);

        if (darkMode) scene.getStylesheets().add("assets/styles/mode/darkmode.css");
        else scene.getStylesheets().add("assets/styles/mode/lightmode.css");

        window.setScene(scene);
        window.showAndWait();
    }
}
