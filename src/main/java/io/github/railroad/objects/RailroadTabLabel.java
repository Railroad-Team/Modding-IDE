package io.github.railroad.objects;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class RailroadTabLabel extends Label {
	private Tab parent;

	public RailroadTabLabel(Tab parent, String text) {
		super(text);
		this.parent = parent;
		this.setOnDragDetected(event -> {
			final Dragboard db = this.startDragAndDrop(TransferMode.MOVE);
			ClipboardContent clipboardContent = new ClipboardContent();
			clipboardContent.putString("railroad.tab");
			db.setContent(clipboardContent);
			event.consume();
		});

		this.setOnDragDropped(event -> {
			final Dragboard db = event.getDragboard();
			if (db.hasString() && db.getString().equals("railroad.tab")
					&& event.getGestureSource() instanceof RailroadTabLabel) {
				System.out.println("same tab");
				final Tab sourceTab = ((RailroadTabLabel) event.getGestureSource()).getTab();
				sourceTab.getTabPane().getTabs().remove(sourceTab);
				this.getTab().getTabPane().getTabs().add(sourceTab);
				event.setDropCompleted(true);
				event.consume();
			}
		});
	}

	public Tab getTab() {
		return this.parent;
	}
}
