package io.github.railroad.objects;

import io.github.railroad.mods.PlatformType;
import io.github.railroad.mods.forge.ForgeVersionsManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

import static io.github.railroad.utility.Components.ButtonFactory.makeButton;
import static io.github.railroad.utility.Components.HBoxFactory.makeHBox;
import static io.github.railroad.utility.Components.StageFactory.makeStage;
import static io.github.railroad.utility.Components.VBoxFactory.makeVBox;

/**
 * Displays the version selector window for workspace setup process.
 *
 * @author ChAos
 */
public class SelectVersionWindow {

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

            switch (type) {
                case FORGE -> versionList = FXCollections.observableList(Objects.requireNonNull(ForgeVersionsManager.downloadVersions()).versions);
                case FABRIC -> {
                }
            }

            ListView<String> listView = new ListView<>(versionList);
            listView.setItems(versionList);
            listView.setPrefSize(200, 500);

            Button cancelButton = makeButton("Cancel").action(event -> window.close()).get();

            Button confirmButton = makeButton("Confirm").action(event -> {
                        // TODO: Pass the selected version to a set of workspace setup configurations.
                        window.close();
                    }
            ).get();

            HBox buttonSet = makeHBox(10).alignment(Pos.CENTER).children(cancelButton, confirmButton).get();
            VBox layout = makeVBox(10).alignment(Pos.CENTER).children(listView, buttonSet).get();

            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
