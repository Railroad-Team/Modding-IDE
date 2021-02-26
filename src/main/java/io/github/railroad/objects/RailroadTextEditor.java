package io.github.railroad.objects;

import io.github.railroad.layout.Saveable;
import javafx.scene.control.TextArea;

public class RailroadTextEditor extends TextArea implements Saveable {

	@Override
	public void saveData() {
		Saveable.super.saveData();
	}

	@Override
	public void loadData() {
		Saveable.super.loadData();
	}
}
