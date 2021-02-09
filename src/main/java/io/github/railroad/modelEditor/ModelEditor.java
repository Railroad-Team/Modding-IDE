package io.github.railroad.modelEditor;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

// TODO make this actually part of the IDE, not a stand alone run thingy
// TODO make editor
// TODO make exporter.
// TODO a lot of things.
public class ModelEditor extends Application {

	public static List<Entity> entities = new ArrayList<Entity>();
	public static int loop;

	private double mousePosX, mousePosY = 0;

	@Override
	public void start(Stage primaryStage) {

		// Add all entities
		entities.add(new Entity("Example1", createCube()));

		// Create group
		Group root = new Group();
		AmbientLight light = new AmbientLight();
		PerspectiveCamera camera = new PerspectiveCamera();

		// Register entities
		for (Entity e : entities)
			root.getChildren().add(e.getObject());

		// Add buttons
		Button button = new Button("Add cube lol");
		ToggleButton toggleButton = new ToggleButton("Lighting");
		HBox hbox = new HBox(button, toggleButton);
		root.getChildren().add(hbox);
		button.setOnAction(value -> {
			addEntity(root, new Entity(String.valueOf(loop), createCube()));
		});

		// Scene
		Scene scene = new Scene(root, 850, 650);
		scene.setCamera(camera);

		// ROTATING STUFF
		// TODO 15 is a strength, make this configurable
		scene.addEventHandler(MouseEvent.MOUSE_DRAGGED, me -> {
			if (me.getButton() == MouseButton.PRIMARY) {
				double dx = (mousePosX - me.getSceneX());
				double dy = (mousePosY - me.getSceneY());

				for (Entity e : entities) {
					e.rotateX.setAngle(e.rotateX.getAngle() - (dy / e.getObject().getHeight() * 360) * (Math.PI / 180) * 15);
					e.rotateY.setAngle(e.rotateY.getAngle() - (dx / e.getObject().getWidth() * -360) * (Math.PI / 180) * 15);
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
		Timeline tick = new Timeline(new KeyFrame(new Duration(10), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				loop++;
				if (root.getChildren().contains(light) && toggleButton.isSelected()) {
					root.getChildren().remove(light);
				} else if (!root.getChildren().contains(light) && !toggleButton.isSelected()) {
					root.getChildren().add(light);
				}
			}
		}));
		tick.setCycleCount(Timeline.INDEFINITE);
		tick.play();// Starts the timeline
	}

	public void addEntity(Group root, Entity e) {
		entities.add(e);
		root.getChildren().add(e.getObject());
	}

	Box createCube() {
		Box box = new Box();

		// Transform
		box.setWidth(300); // x size
		box.setHeight(300); // y size
		box.setDepth(300);// z size
		box.setTranslateX(400); // X pos
		box.setTranslateY(300); // Y pos
		box.setTranslateZ(0); // Z pos

		// Material
		PhongMaterial mat = new PhongMaterial();
		mat.setSpecularColor(Color.BLACK);
		mat.setDiffuseColor(Color.RED);
		box.setMaterial(mat);

		return box;
	}

	// TODO remove this and integrate into main IDE
	public static void main(String[] args) {
		launch(args);
	}

}