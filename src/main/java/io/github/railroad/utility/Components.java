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

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * All kinds of builders for JavaFX components.
 *
 * @author temedy, TheOnlyTails
 */
public interface Components {
    static ImageView createImage(String path) {
        return new ImageView(new Image(path, 20, 20, true, true, true));
    }

    /**
     * A builder for the {@link Button} component.
     *
     * @author temedy
     */
    interface ButtonFactory extends Supplier<Button> {
        /**
         * Converts an existing {@link Button} to a {@link ButtonFactory}.
         *
         * @param button the button.
         * @return a {@link ButtonFactory} from the button.
         */
        private static ButtonFactory makeButton(Button button) {
            return () -> button;
        }

        /**
         * Creates a new {@link ButtonFactory} that creates a {@link Button} with text.
         *
         * @param text the text that appears on the button.
         * @return a {@link ButtonFactory} that creates a {@link Button} with text.
         */
        static ButtonFactory makeButton(Object text) {
            return makeButton(new Button(text.toString()));
        }

        /**
         * Adds an action (in the form of an {@link EventHandler<ActionEvent>}) to the button.
         *
         * @param event the action lambda.
         * @return the {@link ButtonFactory} with the action added.
         */
        default ButtonFactory action(EventHandler<ActionEvent> event) {
            accept(button -> button.setOnAction(event));
            return this;
        }

        /**
         * Used to add a new property to the button via a setter.
         *
         * @param consumer the consumer of the button.
         */
        default void accept(Consumer<Button> consumer) {
            consumer.accept(get());
        }
    }

    /**
     * A builder for the {@link MenuItem} component.
     *
     * @author TheOnlyTails
     */
    interface MenuItemFactory extends Supplier<MenuItem> {
        /**
         * Converts an existing {@link MenuItem} to a {@link MenuItemFactory}.
         *
         * @param item the menu item.
         * @return a {@link MenuItemFactory} from the item.
         */
        private static MenuItemFactory makeMenuItem(MenuItem item) {
            item.setId(item.getText());
            return () -> item;
        }

        /**
         * Creates a new {@link MenuItemFactory} that creates a {@link MenuItem} with text.
         *
         * @param text the text that appears on the menu item.
         * @return a {@link MenuItemFactory} that creates a {@link MenuItem} with text.
         */
        static MenuItemFactory makeMenuItem(Object text) {
            return makeMenuItem(new MenuItem(text.toString()));
        }

        /**
         * The builder version of {@link MenuItem#setVisible(boolean)}.
         *
         * @param visible sets the visibility of this item.
         * @return the {@link MenuItemFactory} with the visibility property added.
         */
        default MenuItemFactory visible(boolean visible) {
            accept(item -> item.setVisible(visible));
            return this;
        }

        /**
         * The builder version of {@link MenuItem#setUserData(Object)}.
         *
         * @param data sets the user data of this item.
         * @return the {@link MenuItemFactory} with the user data property added.
         */
        default MenuItemFactory userData(Object data) {
            accept(item -> item.setUserData(data));
            return this;
        }

        /**
         * The builder version of {@link MenuItem#setAccelerator(KeyCombination)}.
         *
         * @param combination sets the acceleration key of this item.
         * @return the {@link MenuItemFactory} with the accelerator property added.
         */
        default MenuItemFactory accelerationKey(KeyCombination combination) {
            accept(item -> item.setAccelerator(combination));
            return this;
        }

        /**
         * The builder version of {@link MenuItem#setDisable(boolean)}.
         *
         * @return the {@link MenuItemFactory} with the disabled property added.
         */
        default MenuItemFactory disable() {
            accept(item -> item.setDisable(true));
            return this;
        }

        /**
         * The builder version of {@link MenuItem#setMnemonicParsing(boolean)}, with {@code true} as the default.
         *
         * @return the {@link MenuItemFactory} with the accelerator property added.
         */
        default MenuItemFactory parseText() {
            accept(item -> item.setMnemonicParsing(true));
            return this;
        }

        /**
         * The builder version of {@link MenuItem#setMnemonicParsing(boolean)}.
         *
         * @param parse whether to enable/disable mnemonic parsing.
         * @return the {@link MenuItemFactory} with the mnemonic parsing property added.
         */
        default MenuItemFactory parseText(boolean parse) {
            accept(item -> item.setMnemonicParsing(parse));
            return this;
        }

        /**
         * The builder version of {@link MenuItem#setGraphic(Node)}.
         *
         * @param graphic a graphic fore the menu item.
         * @return the {@link MenuItemFactory} with the graphic added.
         */
        default MenuItemFactory graphic(ImageView graphic) {
            accept(item -> item.setGraphic(graphic));
            return this;
        }

        /**
         * The builder version of {@link MenuItem#setStyle(String)}.
         *
         * @param style a string representation of the CSS style associated with the menu item.
         * @return the {@link MenuItemFactory} with the style added.
         */
        default MenuItemFactory style(String style) {
            accept(item -> item.setStyle(style));
            return this;
        }

        /**
         * The builder version of {@link MenuItem#setText(String)}.
         *
         * @param text the menu item's text.
         * @return the {@link MenuItemFactory} with the text added.
         */
        default MenuItemFactory text(String text) {
            accept(item -> item.setText(text));
            return this;
        }

        /**
         * The builder version of {@link MenuItem#setOnAction(EventHandler)}.
         *
         * @param action the action lambda.
         * @return the {@link MenuItemFactory} with the action added.
         */
        default MenuItemFactory action(EventHandler<ActionEvent> action) {
            accept(item -> item.setOnAction(action));
            return this;
        }

        /**
         * The builder version of {@link MenuItem#setOnMenuValidation(EventHandler)}.
         *
         * @param handler the handler lambda.
         * @return the {@link MenuItemFactory} with the menu validation handler added.
         */
        default MenuItemFactory menuValidation(EventHandler<Event> handler) {
            accept(item -> item.setOnMenuValidation(handler));
            return this;
        }

        /**
         * Used to add a new property to the menu item via a setter.
         *
         * @param consumer the consumer of the menu item.
         */
        default void accept(Consumer<MenuItem> consumer) {
            consumer.accept(get());
        }
    }

    /**
     * A builder for the {@link Stage} component.
     *
     * @author temedy
     */
    interface StageFactory extends Supplier<Stage> {
        /**
         * Converts an existing {@link Stage} to a {@link StageFactory}.
         *
         * @param stage the stage.
         * @return a {@link StageFactory} from the stage.
         */
        static StageFactory makeStage(Stage stage) {
            return () -> stage;
        }

        /**
         * Creates a new {@link StageFactory} that creates a {@link Stage} with text.
         *
         * @return a {@link StageFactory} that creates a {@link Stage}.
         */
        static StageFactory makeStage() {
            return makeStage(new Stage());
        }

        /**
         * Creates a new {@link StageFactory} that creates a {@link Stage} with a {@link StageStyle}.
         *
         * @param style the {@link StageStyle} of the stage.
         * @return a {@link StageFactory} that creates a {@link Stage} with text.
         */
        static StageFactory makeStage(StageStyle style) {
            return makeStage(new Stage(style));
        }

        /**
         * The builder version of {@link Window#centerOnScreen()}.
         *
         * @return the {@link StageFactory} centered on screen.
         */
        default StageFactory center() {
            accept(Window::centerOnScreen);
            return this;
        }

        /**
         * The builder version of {@link Stage#initModality(Modality)}.
         *
         * @param modality sets the modality of this stage.
         * @return the {@link StageFactory} with the modality property added.
         */
        default StageFactory modality(Modality modality) {
            accept(stage -> stage.initModality(modality));
            return this;
        }

        /**
         * The builder version of {@link Stage#setTitle(String)}.
         *
         * @param text sets the title of this item.
         * @return the {@link StageFactory} with the title property added.
         */
        default StageFactory title(Object text) {
            accept(stage -> stage.setTitle(text.toString()));
            return this;
        }

        /**
         * The builder version of {@link Stage#setResizable(boolean)}.
         *
         * @param value whether this stage is resizable.
         * @return the {@link StageFactory} with the resizable property added.
         */
        default StageFactory resizable(boolean value) {
            accept(stage -> stage.setResizable(value));
            return this;
        }

        /**
         * The builder version of {@link Stage#setMinWidth(double)}.
         *
         * @param width sets the minimum width of this stage.
         * @return the {@link StageFactory} with the minimum width property added.
         */
        default StageFactory minWidth(int width) {
            accept(stage -> stage.setMinWidth(width));
            return this;
        }

        /**
         * The builder version of {@link Stage#setMinHeight(double)}.
         *
         * @param height sets the minimum width of this stage.
         * @return the {@link StageFactory} with the minimum height property added.
         */
        default StageFactory minHeight(int height) {
            accept(stage -> stage.setMinHeight(height));
            return this;
        }

        /**
         * The builder version of {@link Stage#setMaxWidth(double)}.
         *
         * @param width sets the maximum width of this stage.
         * @return the {@link StageFactory} with the maximum width property added.
         */
        default StageFactory maxWidth(int width) {
            accept(stage -> stage.setMaxWidth(width));
            return this;
        }

        /**
         * The builder version of {@link Stage#setMaxHeight(double)}.
         *
         * @param height sets the maximum height of this stage.
         * @return the {@link StageFactory} with the maximum height property added.
         */
        default StageFactory maxHeight(int height) {
            accept(stage -> stage.setMaxHeight(height));
            return this;
        }

        /**
         * The builder version of {@link Stage#setMaximized(boolean)} with {@code true} as default.
         *
         * @return the {@link StageFactory} as maximized.
         */
        default StageFactory maximized() {
            accept(stage -> stage.setMaximized(true));
            return this;
        }

        /**
         * The builder version of {@link Stage#setScene(Scene)}.
         *
         * @param scene sets the scene of this stage.
         * @return the {@link StageFactory} with the scene property added.
         */
        default StageFactory scene(Scene scene) {
            accept(stage -> stage.setScene(scene));
            return this;
        }

        /**
         * The builder version of {@link Stage#getIcons()}, which then uses {@link ArrayList#addAll(Collection)} to all
         * all of the icons.
         *
         * @param logos sets the icons used in the window decoration of this stage.
         * @return the {@link StageFactory} with the icons property added.
         */
        default StageFactory icons(Image... logos) {
            accept(stage -> stage.getIcons().addAll(logos));
            return this;
        }

        /**
         * The builder version of {@link Stage#show()}.
         *
         * @return the {@link StageFactory}, and attempting to show it: {@code visibility = true}.
         */
        default StageFactory show() {
            accept(Stage::show);
            return this;
        }

        /**
         * Used to add a new property to the stage via a setter.
         *
         * @param consumer the consumer of the stage.
         */
        default void accept(Consumer<Stage> consumer) {
            consumer.accept(get());
        }
    }

    /**
     * A builder for the {@link VBox} component.
     *
     * @author TheOnlyTails
     */
    interface VBoxFactory extends Supplier<VBox> {
        /**
         * Converts an existing {@link VBox} to a {@link VBoxFactory}.
         *
         * @param vBox the vBox.
         * @return a {@link VBoxFactory} from the vBox.
         */
        private static VBoxFactory makeVBox(VBox vBox) {
            return () -> vBox;
        }

        /**
         * Creates a new {@link VBoxFactory} that creates a {@link VBox} with spacing.
         *
         * @param spacing spacing of the vBox.
         * @return a {@link VBoxFactory} that creates a {@link VBox} with spacing.
         */
        static VBoxFactory makeVBox(int spacing) {
            return makeVBox(new VBox(spacing));
        }

        /**
         * The builder version of {@link VBox#getChildren()}, which then uses {@link ArrayList#addAll(Collection)} to
         * all all of the children.
         *
         * @param children sets the children of this VBox.
         * @return the {@link VBoxFactory} with the children added.
         */
        default VBoxFactory children(Node... children) {
            accept(box -> box.getChildren().addAll(children));
            return this;
        }

        /**
         * The builder version of {@link VBox#setAlignment(Pos)}.
         *
         * @param alignment sets the alignment of this VBox.
         * @return the {@link VBoxFactory} with the alignment property added.
         */
        default VBoxFactory alignment(Pos alignment) {
            accept(box -> box.setAlignment(alignment));
            return this;
        }

        /**
         * Used to add a new property to the VBox via a setter.
         *
         * @param consumer the consumer of the VBox.
         */
        default void accept(Consumer<VBox> consumer) {
            consumer.accept(get());
        }
    }

    /**
     * A builder for the {@link HBox} component.
     *
     * @author TheOnlyTails
     */
    interface HBoxFactory extends Supplier<HBox> {
        /**
         * Converts an existing {@link HBox} to a {@link HBoxFactory}.
         *
         * @param hBox the hBox.
         * @return a {@link HBoxFactory} from the vBox.
         */
        private static HBoxFactory makeHBox(HBox hBox) {
            return () -> hBox;
        }

        /**
         * Creates a new {@link HBoxFactory} that creates a {@link HBox} with spacing.
         *
         * @param spacing spacing of the hBox.
         * @return a {@link HBoxFactory} that creates a {@link HBox} with spacing.
         */
        static HBoxFactory makeHBox(int spacing) {
            return makeHBox(new HBox(spacing));
        }

        /**
         * The builder version of {@link HBox#getChildren()}, which then uses {@link ArrayList#addAll(Collection)} to
         * all all of the children.
         *
         * @param children sets the children of this HBox.
         * @return the {@link HBoxFactory} with the children added.
         */
        default HBoxFactory children(Node... children) {
            accept(box -> box.getChildren().addAll(children));
            return this;
        }

        /**
         * The builder version of {@link HBox#setAlignment(Pos)}.
         *
         * @param alignment sets the alignment of this HBox.
         * @return the {@link HBoxFactory} with the alignment property added.
         */
        default HBoxFactory alignment(Pos alignment) {
            accept(box -> box.setAlignment(alignment));
            return this;
        }

        /**
         * Used to add a new property to the HBox via a setter.
         *
         * @param consumer the consumer of the HBox.
         */
        default void accept(Consumer<HBox> consumer) {
            consumer.accept(get());
        }
    }
}
