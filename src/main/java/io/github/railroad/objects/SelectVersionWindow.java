package io.github.railroad.objects;

import io.github.railroad.platform.PlatformType;
import io.github.railroad.platform.forge.ForgeVersion;
import io.github.railroad.utility.UIUtils;
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

public class SelectVersionWindow {

    /**
     * @author ChAoS
     *
     * Display the version selector window for workspace setup process.
     *
     * @param title Title of the selector screen
     * @param type platform to be fetched and display on view list
     */
    public static void displayWindow(String title, PlatformType type) {
        Stage window = new Stage();
        window.centerOnScreen();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(550);
        window.setResizable(true);

        try {
            ObservableList<String> versionList = FXCollections.emptyObservableList();

            switch (type) {
                case FORGE -> versionList = FXCollections.observableList(Objects.requireNonNull(ForgeVersion.downloadVersions()).versions);
                case FABRIC -> {
                }
            }

            ListView<String> listView = new ListView<>(versionList);
            listView.setItems(versionList);
            listView.setPrefSize(200, 500);

            Button cancelButton = UIUtils.createButton("Cancel", event -> {
                window.close();
            });

            Button confirmButton = UIUtils.createButton("Confirm", event -> {
                // TODO: Pass the selected version to a set of workspace setup configurations.
                window.close();
            });

            VBox layout = new VBox(10);
            HBox buttonSet = new HBox(10);
            buttonSet.getChildren().addAll(cancelButton, confirmButton);
            buttonSet.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(listView, buttonSet);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
