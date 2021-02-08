package com.turtywurty.railroad.components;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

public class RailroadMenuItem extends MenuItem {
	private Builder properties;

	private RailroadMenuItem(Builder propertiesIn) {
		this.properties = propertiesIn;
		this.setAccelerator(this.properties.acceleratorKey);
		this.setDisable(this.properties.defaultDisabled);
		this.setGraphic(this.properties.graphic);
		this.setId(this.properties.id);
		this.setMnemonicParsing(this.properties.shouldParseText);
		this.setOnAction(this.properties.action);
		this.setOnMenuValidation(this.properties.menuValidation);
		this.setParentMenu(this.properties.parent);
		this.setParentPopup(this.properties.contextMenu);
		this.setStyle(this.properties.style);
		this.setText(this.properties.text);
		this.setUserData(this.properties.customUserData);
		this.setVisible(this.properties.defaultVisible);
	}

	public static class Builder {
		private KeyCombination acceleratorKey;
		private boolean defaultDisabled, shouldParseText = true, defaultVisible = true;
		private ImageView graphic;
		private String id, style, text = "";
		private EventHandler<ActionEvent> action;
		private EventHandler<Event> menuValidation;
		private Menu parent;
		private ContextMenu contextMenu;
		private Object customUserData;

		private Builder(String textIn) {
			this.text = textIn;
		}

		public static Builder create(String textIn) {
			return new Builder(textIn);
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
