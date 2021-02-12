package io.github.railroad.editor;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

// TODO make this actually part of the IDE, not a stand alone run thingy
// TODO make editor
// TODO make exporter.
// TODO a lot of things.

//TODO: get rid of this

public class ModelEditor extends Application {

    public static List<Entity> entities = new ArrayList<>();
    public static int loop;

    private double mousePosX, mousePosY = 0;

    // TODO remove this and integrate into main IDE
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Add all entities
        entities.add(new Entity("Example1", createCube()));

        // Create group
        final Group root = new Group();
        final AmbientLight light = new AmbientLight();
        final PerspectiveCamera camera = new PerspectiveCamera();

        // Register entities
        for (final Entity e : entities)
            root.getChildren().add(e.box);

        // Add buttons
        final Button button = new Button("Add cube lol");
        final ToggleButton toggleButton = new ToggleButton("Lighting");
        final HBox hbox = new HBox(button, toggleButton);
        root.getChildren().add(hbox);
        button.setOnAction(value -> addEntity(root, new Entity(String.valueOf(loop), createCube())));

        // Scene
        final Scene scene = new Scene(root, 850, 650);
        scene.setCamera(camera);

        // ROTATING STUFF
        // TODO 15 is a strength, make this configurable
        scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, me -> {
            if (me.getButton() == MouseButton.PRIMARY) {
                final double dx = (mousePosX - me.getSceneX());
                final double dy = (mousePosY - me.getSceneY());

                for (final Entity e : entities) {
                    e.rotateX.setAngle(e.rotateX.getAngle() - (dy / e.box.getHeight() * 360) * (Math.PI / 180) * 15);
                    e.rotateY.setAngle(e.rotateY.getAngle() - (dx / e.box.getWidth() * -360) * (Math.PI / 180) * 15);
                }

                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
            }
        });

        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, me -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });

        primaryStage.setTitle("Model Editor? POG");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Timeline
        final Timeline tick = new Timeline(new KeyFrame(new Duration(10), event -> {
            loop++;
            if (root.getChildren().contains(light) && toggleButton.isSelected()) {
                root.getChildren().remove(light);
            } else if (!root.getChildren().contains(light) && !toggleButton.isSelected()) {
                root.getChildren().add(light);
            }
        }));

        tick.setCycleCount(Timeline.INDEFINITE);
        tick.play();// Starts the timeline
    }

    public void addEntity(Group root, Entity e) {
        entities.add(e);
        root.getChildren().add(e.box);
    }

    public Box createCube() {
        final Box box = new Box();

        // Transform
        box.setWidth(300); // x size
        box.setHeight(300); // y size
        box.setDepth(300);// z size
        box.setTranslateX(400); // X pos
        box.setTranslateY(300); // Y pos
        box.setTranslateZ(0); // Z pos

        // Material
        final PhongMaterial mat = new PhongMaterial();
        mat.setSpecularColor(Color.BLACK);
        mat.setDiffuseColor(Color.RED);
        box.setMaterial(mat);

        return box;
    }
}