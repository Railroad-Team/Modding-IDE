package io.github.railroad.utility;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

import java.util.function.Consumer;
import java.util.function.Supplier;

//TODO: get rid of this
public interface Components {

    static ImageView createMenuGraphics(String imagePath) {
        return new ImageView(new Image(imagePath, 20, 20, true, true, true));
    }

    //Only temporary, will delete this, maaybe?
    interface Buttons extends Supplier<Button> {
        static Buttons makeButton(Button button) {
            return () -> button;
        }

        static Buttons makeButton(String text) {
            return makeButton(new Button(text));
        }

        default Buttons action(EventHandler<ActionEvent> event) {
            consume(button -> button.setOnAction(event));
            return this;
        }

        default void consume(Consumer<Button> consumer) {
            consumer.accept(get());
        }
    }

    interface MenuItems extends Supplier<MenuItem> {
        static MenuItems makeMenuItem(MenuItem menuItem) {
            menuItem.setId(menuItem.getText());
            return () -> menuItem;
        }

        static MenuItems makeMenuItem(String text) {
            return makeMenuItem(new MenuItem(text));
        }

        default MenuItems visible(boolean visible) {
            consume(menuItem -> menuItem.setVisible(visible));
            return this;
        }

        default MenuItems customUserData(Object data) {
            consume(menuItem -> menuItem.setUserData(data));
            return this;
        }

        default MenuItems accelerationKey(KeyCombination combination) {
            consume(menuItem -> menuItem.setAccelerator(combination));
            return this;
        }

        default MenuItems disable() {
            consume(menuItem -> menuItem.setDisable(true));
            return this;
        }

        default MenuItems parseText() {
            consume(menuItem -> menuItem.setMnemonicParsing(true));
            return this;
        }

        default MenuItems parseText(boolean shouldParseText) {
            consume(menuItem -> menuItem.setMnemonicParsing(shouldParseText));
            return this;
        }

        default MenuItems graphic(ImageView graphic) {
            consume(menuItem -> menuItem.setGraphic(graphic));
            return this;
        }

        default MenuItems style(String style) {
            consume(menuItem -> menuItem.setStyle(style));
            return this;
        }

        default MenuItems text(String text) {
            consume(menuItem -> menuItem.setText(text));
            return this;
        }

        default MenuItems action(EventHandler<ActionEvent> action) {
            consume(menuItem -> menuItem.setOnAction(action));
            return this;
        }

        default MenuItems menuValidation(EventHandler<Event> menuValidation) {
            consume(menuItem -> menuItem.setOnMenuValidation(menuValidation));
            return this;
        }

        default void consume(Consumer<MenuItem> consumer) {
            consumer.accept(get());
        }
    }
}
