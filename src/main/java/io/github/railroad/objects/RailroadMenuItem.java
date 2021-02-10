package io.github.railroad.objects;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

public class RailroadMenuItem extends MenuItem {

    private RailroadMenuItem(Builder propertiesIn) {
        this.setAccelerator(propertiesIn.acceleratorKey);
        this.setDisable(propertiesIn.defaultDisabled);
        this.setGraphic(propertiesIn.graphic);
        this.setId(propertiesIn.id);
        this.setMnemonicParsing(propertiesIn.shouldParseText);
        this.setOnAction(propertiesIn.action);
        this.setOnMenuValidation(propertiesIn.menuValidation);
        this.setParentMenu(propertiesIn.parent);
        this.setParentPopup(propertiesIn.contextMenu);
        this.setStyle(propertiesIn.style);
        this.setText(propertiesIn.text);
        this.setUserData(propertiesIn.customUserData);
        this.setVisible(propertiesIn.defaultVisible);
    }

    public static class Builder {
        public KeyCombination acceleratorKey;
        public boolean defaultDisabled, shouldParseText = true, defaultVisible = true;
        public ImageView graphic;
        public String id, style, text;
        public EventHandler<ActionEvent> action;
        public EventHandler<Event> menuValidation;
        public Menu parent;
        public ContextMenu contextMenu;
        public Object customUserData;

        public Builder(String text) {
            this.text = text;
        }

        public static Builder create(String text) {
            return new Builder(text);
        }

        public Builder setAccelerationKey(KeyCombination combination) {
            this.acceleratorKey = combination;
            return this;
        }

        public Builder disable() {
            this.defaultDisabled = true;
            return this;
        }

        public Builder setGraphic(ImageView graphicIn) {
            this.graphic = graphicIn;
            return this;
        }

        public Builder setID(String idIn) {
            this.id = idIn;
            return this;
        }

        public Builder parseText(boolean parse) {
            this.shouldParseText = parse;
            return this;
        }

        public Builder setActionEvent(EventHandler<ActionEvent> event) {
            this.action = event;
            return this;
        }

        public Builder setMenuValidation(EventHandler<Event> event) {
            this.menuValidation = event;
            return this;
        }

        public Builder setParentMenu(Menu parentIn) {
            this.parent = parentIn;
            return this;
        }

        public Builder setContextMenu(ContextMenu menu) {
            this.contextMenu = menu;
            return this;
        }

        public Builder setStyle(String styleIn) {
            this.style = styleIn;
            return this;
        }

        public Builder setText(String textIn) {
            this.text = textIn;
            return this;
        }

        public Builder setCustomUserData(Object data) {
            this.customUserData = data;
            return this;
        }

        public Builder setVisible(boolean visible) {
            this.defaultVisible = visible;
            return this;
        }

        public RailroadMenuItem build() {
            if (this.id == null) {
                this.id = this.text;
            }
            return new RailroadMenuItem(this);
        }
    }
}
