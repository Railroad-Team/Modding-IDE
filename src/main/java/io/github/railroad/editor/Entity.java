package io.github.railroad.editor;

import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

//TODO: get rid of this
public final class Entity {
    public final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    public final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    public final String name;
    public final Box box;

    public Entity(String name, Box box) {
        this.name = name;
        this.box = box;
        this.box.getTransforms().addAll(rotateX, rotateY);
    }
}
