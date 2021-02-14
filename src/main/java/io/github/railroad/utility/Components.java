package io.github.railroad.utility;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Components {
    static ImageView createImage(String path) {
        return new ImageView(new Image(path, 20, 20, true, true, true));
    }

    /**
     * @author temedy
     */
    interface Buttons extends Supplier<Button> {
        private static Buttons makeButton(Button button) {
            return () -> button;
        }

        static Buttons makeButton(Object text) {
            return makeButton(new Button(text.toString()));
        }

        default Buttons action(EventHandler<ActionEvent> event) {
            accept(button -> button.setOnAction(event));
            return this;
        }

        default void accept(Consumer<Button> consumer) {
            consumer.accept(get());
        }
    }

    /**
     * @author TheOnlyTails
     */
    interface MenuItems extends Supplier<MenuItem> {
        private static MenuItems makeMenuItem(MenuItem item) {
            item.setId(item.getText());
            return () -> item;
        }

        static MenuItems makeMenuItem(Object text) {
            return makeMenuItem(new MenuItem(text.toString()));
        }

        default MenuItems visible(boolean visible) {
            accept(item -> item.setVisible(visible));
            return this;
        }

        default MenuItems userData(Object data) {
            accept(item -> item.setUserData(data));
            return this;
        }

        default MenuItems accelerationKey(KeyCombination combination) {
            accept(item -> item.setAccelerator(combination));
            return this;
        }

        default MenuItems disable() {
            accept(item -> item.setDisable(true));
            return this;
        }

        default MenuItems parseText() {
            accept(item -> item.setMnemonicParsing(true));
            return this;
        }

        default MenuItems parseText(boolean parse) {
            accept(item -> item.setMnemonicParsing(parse));
            return this;
        }

        default MenuItems graphic(ImageView graphic) {
            accept(item -> item.setGraphic(graphic));
            return this;
        }

        default MenuItems style(String style) {
            accept(item -> item.setStyle(style));
            return this;
        }

        default MenuItems text(String text) {
            accept(item -> item.setText(text));
            return this;
        }

        default MenuItems action(EventHandler<ActionEvent> action) {
            accept(item -> item.setOnAction(action));
            return this;
        }

        default MenuItems menuValidation(EventHandler<Event> handler) {
            accept(item -> item.setOnMenuValidation(handler));
            return this;
        }

        default void accept(Consumer<MenuItem> consumer) {
            consumer.accept(get());
        }
    }

    /**
     * @author temedy
     */
    interface Stages extends Supplier<Stage> {
        private static Stages makeStage(Stage stage) {
            return () -> stage;
        }

        default Stages center() {
            accept(Window::centerOnScreen);
            return this;
        }

        default Stages modality(Modality modality) {
            accept(stage -> stage.initModality(modality));
            return this;
        }

        default Stages title(Object text) {
            accept(stage -> stage.setTitle(text.toString()));
            return this;
        }

        default Stages resizable(boolean value) {
            accept(stage -> stage.setResizable(value));
            return this;
        }

        default Stages minWidth(int width) {
            accept(stage -> stage.setMinWidth(width));
            return this;
        }

        default Stages minHeight(int height) {
            accept(stage -> stage.setMinHeight(height));
            return this;
        }

        default Stages maxWidth(int width) {
            accept(stage -> stage.setMaxWidth(width));
            return this;
        }

        default Stages maxHeight(int height) {
            accept(stage -> stage.setMaxHeight(height));
            return this;
        }

        default Stages maximized() {
            accept(stage -> stage.setMaximized(true));
            return this;
        }

        static Stages convertToBuilder(Stage stage) {
            return makeStage(stage);
        }

        default Stages scene(Scene scene) {
            accept(stage -> stage.setScene(scene));
            return this;
        }

        default Stages icons(Image... logos) {
            accept(stage -> stage.getIcons().addAll(logos));
            return this;
        }

        static Stages makeStage() {
            return makeStage(new Stage());
        }

        default Stages show() {
            accept(Stage::show);
            return this;
        }

        static Stages makeStage(StageStyle style) {
            return makeStage(new Stage(style));
        }

        default void accept(Consumer<Stage> consumer) {
            consumer.accept(get());
        }
    }

    interface VBoxes extends Supplier<VBox> {
        static VBoxes makeVBox(int spacing) {
            return () -> new VBox(spacing);
        }

        default VBoxes children(Node... children) {
            accept(box -> box.getChildren().addAll(children));
            return this;
        }

        default VBoxes alignment(Pos alignment) {
            accept(box -> box.setAlignment(alignment));
            return this;
        }

        default void accept(Consumer<VBox> consumer) {
            consumer.accept(get());
        }
    }

    interface HBoxes extends Supplier<HBox> {
        static HBoxes makeHBox(int spacing) {
            return () -> new HBox(spacing);
        }

        default HBoxes children(Node... children) {
            accept(box -> box.getChildren().addAll(children));
            return this;
        }

        default HBoxes alignment(Pos alignment) {
            accept(box -> box.setAlignment(alignment));
            return this;
        }

        default void accept(Consumer<HBox> consumer) {
            consumer.accept(get());
        }
    }
}
