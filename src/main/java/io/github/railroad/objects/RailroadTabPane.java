package io.github.railroad.objects;

import io.github.railroad.layout.Saveable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class RailroadTabPane extends TabPane implements Saveable {

	private Node realParent;
	
	public RailroadTabPane(Node parent, Tab... tabs) {
		super(tabs);
		this.realParent = parent;
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
