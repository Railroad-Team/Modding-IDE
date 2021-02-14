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
    interface ButtonFactory extends Supplier<Button> {
        private static ButtonFactory makeButton(Button button) {
            return () -> button;
        }

        static ButtonFactory makeButton(Object text) {
            return makeButton(new Button(text.toString()));
        }

        default ButtonFactory action(EventHandler<ActionEvent> event) {
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
    interface MenuItemFactory extends Supplier<MenuItem> {
        private static MenuItemFactory makeMenuItem(MenuItem item) {
            item.setId(item.getText());
            return () -> item;
        }

        static MenuItemFactory makeMenuItem(Object text) {
            return makeMenuItem(new MenuItem(text.toString()));
        }

        default MenuItemFactory visible(boolean visible) {
            accept(item -> item.setVisible(visible));
            return this;
        }

        default MenuItemFactory userData(Object data) {
            accept(item -> item.setUserData(data));
            return this;
        }

        default MenuItemFactory accelerationKey(KeyCombination combination) {
            accept(item -> item.setAccelerator(combination));
            return this;
        }

        default MenuItemFactory disable() {
            accept(item -> item.setDisable(true));
            return this;
        }

        default MenuItemFactory parseText() {
            accept(item -> item.setMnemonicParsing(true));
            return this;
        }

        default MenuItemFactory parseText(boolean parse) {
            accept(item -> item.setMnemonicParsing(parse));
            return this;
        }

        default MenuItemFactory graphic(ImageView graphic) {
            accept(item -> item.setGraphic(graphic));
            return this;
        }

        default MenuItemFactory style(String style) {
            accept(item -> item.setStyle(style));
            return this;
        }

        default MenuItemFactory text(String text) {
            accept(item -> item.setText(text));
            return this;
        }

        default MenuItemFactory action(EventHandler<ActionEvent> action) {
            accept(item -> item.setOnAction(action));
            return this;
        }

        default MenuItemFactory menuValidation(EventHandler<Event> handler) {
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
    interface StageFactory extends Supplier<Stage> {
        private static StageFactory makeStage(Stage stage) {
            return () -> stage;
        }

        static StageFactory convertToBuilder(Stage stage) {
            return makeStage(stage);
        }

        static StageFactory makeStage() {
            return makeStage(new Stage());
        }

        static StageFactory makeStage(StageStyle style) {
            return makeStage(new Stage(style));
        }

        default StageFactory center() {
            accept(Window::centerOnScreen);
            return this;
        }

        default StageFactory modality(Modality modality) {
            accept(stage -> stage.initModality(modality));
            return this;
        }

        default StageFactory title(Object text) {
            accept(stage -> stage.setTitle(text.toString()));
            return this;
        }

        default StageFactory resizable(boolean value) {
            accept(stage -> stage.setResizable(value));
            return this;
        }

        default StageFactory minWidth(int width) {
            accept(stage -> stage.setMinWidth(width));
            return this;
        }

        default StageFactory minHeight(int height) {
            accept(stage -> stage.setMinHeight(height));
            return this;
        }

        default StageFactory maxWidth(int width) {
            accept(stage -> stage.setMaxWidth(width));
            return this;
        }

        default StageFactory maxHeight(int height) {
            accept(stage -> stage.setMaxHeight(height));
            return this;
        }

        default StageFactory maximized() {
            accept(stage -> stage.setMaximized(true));
            return this;
        }

        default StageFactory scene(Scene scene) {
            accept(stage -> stage.setScene(scene));
            return this;
        }

        default StageFactory icons(Image... logos) {
            accept(stage -> stage.getIcons().addAll(logos));
            return this;
        }

        default StageFactory show() {
            accept(Stage::show);
            return this;
        }

        default void accept(Consumer<Stage> consumer) {
            consumer.accept(get());
        }
    }

    /**
     * @author TheOnlyTails
     */
    interface VBoxFactory extends Supplier<VBox> {
        static VBoxFactory makeVBox(int spacing) {
            return () -> new VBox(spacing);
        }

        default VBoxFactory children(Node... children) {
            accept(box -> box.getChildren().addAll(children));
            return this;
        }

        default VBoxFactory alignment(Pos alignment) {
            accept(box -> box.setAlignment(alignment));
            return this;
        }

        default void accept(Consumer<VBox> consumer) {
            consumer.accept(get());
        }
    }

    /**
     * @author TheOnlyTails
     */
    interface HBoxFactory extends Supplier<HBox> {
        static HBoxFactory makeHBox(int spacing) {
            return () -> new HBox(spacing);
        }

        default HBoxFactory children(Node... children) {
            accept(box -> box.getChildren().addAll(children));
            return this;
        }

        default HBoxFactory alignment(Pos alignment) {
            accept(box -> box.setAlignment(alignment));
            return this;
        }

        default void accept(Consumer<HBox> consumer) {
            consumer.accept(get());
        }
    }
}
