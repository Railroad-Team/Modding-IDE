package com.turtywurty.railroad.menu;

import com.turtywurty.railroad.RailroadIDE;
import com.turtywurty.railroad.RailroadMenuItem;
import com.turtywurty.railroad.util.Utils;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class RailroadMenu {

	// TODO: Make proper textures for all these icons. Currently all just programmer
		// art! ;)
		public static Menu createNewMenu() {
			Menu menu = new Menu(RailroadIDE.config.lang.get("menu.file.new"));

			MenuItem javaProject = RailroadMenuItem.Builder.create(RailroadIDE.config.lang.get("menu.file.new.javaproject"))
					.setGraphic(Utils.createMenuGraphics("/assets/img/java_project.png")).build();

			MenuItem project = RailroadMenuItem.Builder.create(RailroadIDE.config.lang.get("menu.file.new.project"))
					.setGraphic(Utils.createMenuGraphics("/assets/img/project.png")).build();

			// TODO: Once added texture at "/assets/img/working_set.png" un-comment
			// setGraphic
			MenuItem javaWorkingSet = RailroadMenuItem.Builder.create(RailroadIDE.config.lang.get("menu.file.new.javaworkingset"))
					/* .setGraphic(Utils.createMenuGraphics("/assets/img/working_set.png") */.build();

			MenuItem packagE = RailroadMenuItem.Builder.create(RailroadIDE.config.lang.get("menu.file.new.package"))
					.setGraphic(Utils.createMenuGraphics("/assets/img/package.png")).build();

			MenuItem sourceFolder = RailroadMenuItem.Builder.create(RailroadIDE.config.lang.get("menu.file.new.sourcefolder"))
					.setGraphic(Utils.createMenuGraphics("/assets/img/source_folder.png")).build();

			MenuItem file = RailroadMenuItem.Builder.create(RailroadIDE.config.lang.get("menu.file.new.file"))
					.setGraphic(Utils.createMenuGraphics("/assets/img/file.png")).build();

			MenuItem folder = RailroadMenuItem.Builder.create(RailroadIDE.config.lang.get("menu.file.new.folder"))
					.setGraphic(Utils.createMenuGraphics("/assets/img/folder.png")).build();

			MenuItem clazz = RailroadMenuItem.Builder.create(RailroadIDE.config.lang.get("menu.file.new.class"))
					.setGraphic(Utils.createMenuGraphics("/assets/img/class.png")).build();

			MenuItem interfacE = RailroadMenuItem.Builder.create(RailroadIDE.config.lang.get("menu.file.new.interface"))
					.setGraphic(Utils.createMenuGraphics("/assets/img/interface.png")).build();

			MenuItem enuM = RailroadMenuItem.Builder.create(RailroadIDE.config.lang.get("menu.file.new.enum"))
					.setGraphic(Utils.createMenuGraphics("/assets/img/enum.png")).build();

			MenuItem annotation = RailroadMenuItem.Builder.create(RailroadIDE.config.lang.get("menu.file.new.annotation"))
					.setGraphic(Utils.createMenuGraphics("/assets/img/annotation.png")).build();

			menu.getItems().addAll(javaProject, project, javaWorkingSet, new SeparatorMenuItem(), sourceFolder, packagE,
					file, folder, new SeparatorMenuItem(), clazz, interfacE, enuM, annotation);
			return menu;
		}
}
