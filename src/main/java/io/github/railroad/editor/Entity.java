package io.github.railroad.editor;

import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

import static javafx.scene.transform.Rotate.X_AXIS;
import static javafx.scene.transform.Rotate.Y_AXIS;

//TODO: get rid of this
public final class Entity {
    public final Rotate rotateX = new Rotate(0, X_AXIS);
    public final Rotate rotateY = new Rotate(0, Y_AXIS);
    public final String name;
    public final Box box;

    public Entity(String name, Box box) {
        this.name = name;
        this.box = box;
        this.box.getTransforms().addAll(rotateX, rotateY);
    }
}
