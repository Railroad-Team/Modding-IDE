package com.turtywurty.railroad.menu;

import com.turtywurty.railroad.util.Utils;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class RailroadMenu {

	// TODO: Make proper textures for all these icons. Currently all just programmer art! ;)
		// TODO: Find a way that allows us to fit an image larger than 20x20 pixels into the space this image is drawn.
		public static Menu createNewMenu() {
			Menu menu = new Menu("New");

			MenuItem javaProject = RailroadMenuItem.Builder.create("Java Project")
					.setGraphic(Utils.createMenuGraphics("/assets/img/java_project.png")).build();

			MenuItem project = RailroadMenuItem.Builder.create("Project")
					.setGraphic(Utils.createMenuGraphics("/assets/img/project.png")).build();

			MenuItem packagE = RailroadMenuItem.Builder.create("Package")
					.setGraphic(Utils.createMenuGraphics("/assets/img/package.png")).build();

			MenuItem sourceFolder = RailroadMenuItem.Builder.create("Source Folder")
					.setGraphic(Utils.createMenuGraphics("/assets/img/source_folder.png")).build();

			MenuItem file = RailroadMenuItem.Builder.create("File").setGraphic(Utils.createMenuGraphics("/assets/img/file.png"))
					.build();

			MenuItem folder = RailroadMenuItem.Builder.create("Folder")
					.setGraphic(Utils.createMenuGraphics("/assets/img/folder.png")).build();

			MenuItem clazz = RailroadMenuItem.Builder.create("Class")
					.setGraphic(Utils.createMenuGraphics("/assets/img/class.png")).build();

			MenuItem interfacE = RailroadMenuItem.Builder.create("Interface")
					.setGraphic(Utils.createMenuGraphics("/assets/img/interface.png")).build();

			MenuItem enuM = RailroadMenuItem.Builder.create("Enum").setGraphic(Utils.createMenuGraphics("/assets/img/enum.png"))
					.build();

			MenuItem annotation = RailroadMenuItem.Builder.create("Annotation")
					.setGraphic(Utils.createMenuGraphics("/assets/img/annotation.png")).build();

			menu.getItems().addAll(javaProject, project, new SeparatorMenuItem(), sourceFolder, packagE, file,
					folder, new SeparatorMenuItem(), clazz, interfacE, enuM, annotation);
			return menu;
		}
}
