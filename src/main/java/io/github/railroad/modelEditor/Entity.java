package io.github.railroad.modelEditor;

import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

public class Entity {

	private String name;
	private Box object;

	public Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
	public Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);

	public Entity(String n, Box o) {
		this.name = n;
		this.object = o;
		this.getObject().getTransforms().addAll(rotateX, rotateY);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Box getObject() {
		return object;
	}

	public void setObject(Box object) {
		this.object = object;
	}

}
