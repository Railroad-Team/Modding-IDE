package io.github.railroad.objects;

import io.github.railroad.layout.Saveable;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;

public class RailroadSplitPane extends SplitPane implements Saveable {
	private Node realParent;

	@Override
	public void saveData() {
		Saveable.super.saveData();
		for (Node child : this.getItems()) {
			if (child instanceof Saveable)
				((Saveable) child).saveData();
		}
	}

	@Override
	public void loadData() {
		Saveable.super.loadData();
	}

	public Node getRealParent() {
		return this.realParent;
	}

	public void setRealParent(Node realParent) {
		this.realParent = realParent;
	}
}
