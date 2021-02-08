package com.turtywurty.railroad.model;

import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

public class Entity {

	private String name;
	private Box object;

	public Entity(String n, Box o) {
		this.name = n;
		this.object = o;
	}
	
	public void tick() {
		Rotate xRotation = new Rotate(1, Rotate.X_AXIS);
		Rotate yRotation = new Rotate(2, Rotate.Y_AXIS);
		object.getTransforms().addAll(xRotation, yRotation);
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
