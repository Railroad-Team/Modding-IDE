package io.github.railroad.objects;

import static io.github.railroad.utility.Components.ButtonFactory.makeButton;
import static io.github.railroad.utility.Components.HBoxFactory.makeHBox;
import static io.github.railroad.utility.Components.StageFactory.makeStage;
import static io.github.railroad.utility.Components.VBoxFactory.makeVBox;

import java.util.Objects;

import io.github.railroad.mods.PlatformType;
import io.github.railroad.mods.forge.ForgeVersionsManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Displays the version selector window for workspace setup process.
 *
 * @author ChAos
 */
public final class SelectVersionWindow {
	
	private SelectVersionWindow() {}

    /**
     * Displays the version selector window for workspace setup process.
     *
     * @param title Title of the selector screen.
     * @param type  platform to be fetched and display on view list.
     * @author ChAos
     */
    public static void displayWindow(String title, PlatformType type) {
        Stage window = makeStage()
                .center()
                .modality(Modality.APPLICATION_MODAL)
                .title(title)
                .minHeight(550)
                .minWidth(250)
                .resizable(true).get();

        try {
            ObservableList<String> versionList = FXCollections.emptyObservableList();

            if (type == PlatformType.FORGE) {
                versionList = FXCollections.observableList(Objects.requireNonNull(ForgeVersionsManager.downloadVersions()).versions);
            } else if (type == PlatformType.FABRIC) {
                // TODO: add something here
            }

            ListView<String> listView = new ListView<>(versionList);
            listView.setItems(versionList);
            listView.setPrefSize(200, 500);

            var cancelButton = makeButton("Cancel").action(event -> window.close()).get();

            var confirmButton = makeButton("Confirm").action(event -> {
                // TODO: Pass the selected version to a set of workspace setup configurations.
                        window.close();
                    }
            ).get();

            HBox buttonSet = makeHBox(10).alignment(Pos.CENTER).children(cancelButton, confirmButton).get();
            VBox layout = makeVBox(10).alignment(Pos.CENTER).children(listView, buttonSet).get();

            var scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
