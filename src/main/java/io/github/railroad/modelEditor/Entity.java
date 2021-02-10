package io.github.railroad.modelEditor;

import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;

public final class Entity {
    public final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    public final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    public final String name;
    public final Box object;

    public Entity(String n, Box o) {
        this.name = n;
        this.object = o;
        this.object.getTransforms().addAll(rotateX, rotateY);
    }
}
