package io.github.railroad.objects;

import io.github.railroad.layout.LayoutManager;
import io.github.railroad.layout.Saveable;
import javafx.geometry.Orientation;
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
//              if the dropped tabPane is a TabPane
				final Tab sourceTab = ((RailroadTabLabel) event.getGestureSource()).getTab();
				if (sourceTab.getTabPane() != this
						|| (sourceTab.getTabPane() == this && sourceTab.getTabPane().getTabs().size() > 1)) {
					LayoutManager.splitTabPane(this, sourceTab, Orientation.HORIZONTAL);
				}
				event.setDropCompleted(true);
				event.consume();
//				if (sourceTab.getTabPane() instanceof RailroadTabPane
//						&& (sourceTab.getTabPane() == this && sourceTab.getTabPane().getTabs().size() > 1)) {
//					RailroadTabPane sourceTabPane = (RailroadTabPane) sourceTab.getTabPane();
//					System.out.println("Splitting Target TabPlane");
//					// get local mouse coords
//					// if coords are less then center
//					// add it first
//					// else add it last
//					sourceTab.getTabPane().getTabs().remove(sourceTab);
//					RailroadTabPane newPane = LayoutManager.splitTabPane(this, sourceTab, Orientation.HORIZONTAL);
//					if (sourceTabPane.getTabs().isEmpty()) {
//						if (sourceTabPane.getRealParent() instanceof Region) {
//							LayoutManager.removeTabPane(sourceTabPane);
//							// clean up if sourceParent is splitpane with now only 1 tabpane
//							if (sourceTabPane.getRealParent() instanceof RailroadSplitPane) {
//								RailroadSplitPane sourceParent = (RailroadSplitPane) sourceTabPane.getRealParent();
//								if (sourceParent.getItems().size() <= 1) {
//									LayoutManager.removeFromPane(sourceParent.getRealParent(), sourceParent);
//									if (!sourceParent.getItems().isEmpty()) {
//										LayoutManager.addToPane(sourceParent.getRealParent(),
//												sourceParent.getItems().get(0));
//									}
//								}
//							}
//						}
//					}
//					newPane.getTabs().add(sourceTab);
//					event.setDropCompleted(true);
//					event.consume();
//				}
			}
		});
	}

	public Node getRealParent() {
		return this.realParent;
	}

	public void setRealParent(Node parent) {
		this.realParent = parent;
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
