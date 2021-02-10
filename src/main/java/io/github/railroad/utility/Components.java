package io.github.railroad.utility;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.function.Consumer;
import java.util.function.Supplier;

//TODO: get rid of this
public interface Components {

    //Only temporary, will delete this, maaybe?
    interface ButtonBuilder extends Supplier<Button> {
        static ButtonBuilder makeButton(Button button) {
            return () -> button;
        }

        static ButtonBuilder makeButton(String text) {
            return makeButton(new Button(text));
        }

        default ButtonBuilder action(EventHandler<ActionEvent> event) {
            consume(button -> button.setOnAction(event));
            return this;
        }

        default void consume(Consumer<Button> consumer) {
            consumer.accept(get());
        }
    }

    static ImageView createMenuGraphics(String imagePath) { return new ImageView(new Image(imagePath, 20, 20, true, true, true)); }
}
