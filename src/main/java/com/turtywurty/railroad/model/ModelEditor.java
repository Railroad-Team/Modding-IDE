package com.turtywurty.railroad.model;

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

	@Override
	public void start(Stage primaryStage) {

		// Add all entities
		entities.add(new Entity("Example1", createCube()));
		entities.add(new Entity("Example2", createCube()));

		Group root = new Group(); // layout

		Button button = new Button("Add cube lol");
		ToggleButton toggleButton = new ToggleButton("Lighting");
		HBox hbox = new HBox(button, toggleButton);
		root.getChildren().add(hbox);
		
		AmbientLight light = new AmbientLight();

		button.setOnAction(value -> {
			addEntity(root, new Entity(String.valueOf(loop), createCube()));
		});

		for (Entity e : entities)
			root.getChildren().add(e.getObject());

		PerspectiveCamera camera = new PerspectiveCamera();

		Scene scene = new Scene(root, 850, 650); // show scene
		scene.setCamera(camera);

		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();

		Timeline tick = new Timeline(new KeyFrame(new Duration(10), // This is how often it updates in milliseconds
				new EventHandler<ActionEvent>() {
					public void handle(ActionEvent t) {
						for (Entity e : entities)
							e.tick();
						loop++;
						if (root.getChildren().contains(light) && toggleButton.isSelected()) {
							root.getChildren().remove(light);
						}
						else if (!root.getChildren().contains(light) && !toggleButton.isSelected()) {
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
		box.setWidth(300); // x size
		box.setHeight(300); // y size
		box.setDepth(300);// z size

		box.setTranslateX(300);
		box.setTranslateY(300);
		box.setTranslateZ(0);

		PhongMaterial mat = new PhongMaterial();
		mat.setSpecularColor(Color.BLACK);
		mat.setDiffuseColor(Color.RED);

		box.setMaterial(mat);

		return box;
	}

	public static void main(String[] args) {
		launch(args);
	}

}