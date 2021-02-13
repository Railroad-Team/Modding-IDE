package io.github.railroad.objects;

import io.github.railroad.layout.LayoutManager;
import io.github.railroad.layout.Saveable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class RailroadTabPane extends TabPane implements Saveable {

	private Node realParent;

	public RailroadTabPane(Node parent, Tab... tabs) {
		super(tabs);
		this.realParent = parent;

		this.setOnDragOver(event -> {
			final Dragboard db = event.getDragboard();
			if (db.hasString() && db.getString().equals("railroad.tab")
					&& event.getGestureSource() instanceof RailroadTabLabel) {
				event.acceptTransferModes(TransferMode.MOVE);
				event.consume();
			}
		});

		this.setOnDragDropped(event -> {
			final Dragboard db = event.getDragboard();
			if (db.hasString() && db.getString().equals("railroad.tab")
					&& event.getGestureSource() instanceof RailroadTabLabel) {
				final Tab sourceTab = ((RailroadTabLabel) event.getGestureSource()).getTab();
				if (sourceTab.getTabPane() != this && sourceTab.getTabPane() instanceof RailroadTabPane) {
					System.out.println("different tab");
					LayoutManager.addTabbedPane(this, (RailroadTabPane) sourceTab.getTabPane());
					sourceTab.getTabPane().getTabs().remove(sourceTab);
					this.getTabs().add(sourceTab);
					event.setDropCompleted(true);
					event.consume();
				}
			}
		});
	}

	public Node getRealParent() {
		return this.realParent;
	}

	@Override
	public void saveData() {
		Saveable.super.saveData();
		for (Tab tab : this.getTabs()) {
			if (tab.getContent() instanceof Saveable) {
				((Saveable) tab.getContent()).saveData();
			}
		}
	}

	@Override
	public void loadData() {
		Saveable.super.loadData();
	}
}
