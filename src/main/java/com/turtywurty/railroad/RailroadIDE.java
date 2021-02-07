package com.turtywurty.railroad;

import java.io.InputStream;

import com.turtywurty.railroad.util.Utils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RailroadIDE extends Application {

	private Scene mainScene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainWindow) throws Exception {
		this.createComponents(mainWindow);
		Image[] logos = new Image[2];
		InputStream logo16 = RailroadIDE.class.getResourceAsStream("/assets/img/logo16.png");
		InputStream logo32 = RailroadIDE.class.getResourceAsStream("/assets/img/logo32.png");
		logos[0] = new Image(logo16);
		logos[1] = new Image(logo32);
		logo16.close();
		logo32.close();
		Stage window = Utils.setupWindow(mainWindow, "JavaFX Testing", this.mainScene, logos);
		window.setOnCloseRequest(event -> {
			event.consume();
			this.onClose(window);
		});
	}

	public void createComponents(Stage window) {
		BorderPane borderPane = new BorderPane();

		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("File");
		fileMenu.getItems().add(this.createNewMenu());

		Menu editMenu = new Menu("Edit");
		Menu sourceMenu = new Menu("Source");
		Menu refactorMenu = new Menu("Refactor");
		Menu navigateMenu = new Menu("Navigate");
		Menu searchMenu = new Menu("Search");
		Menu projectMenu = new Menu("Project");
		Menu runMenu = new Menu("Run");
		Menu windowMenu = new Menu("Window");
		Menu helpMenu = new Menu("Help");

		menuBar.getMenus().addAll(fileMenu, editMenu, sourceMenu, refactorMenu, navigateMenu, searchMenu, projectMenu,
				runMenu, windowMenu, helpMenu);
		HBox topMenu = new HBox(menuBar);
		borderPane.setTop(topMenu);
		this.mainScene = new Scene(borderPane);
	}

	public Menu createNewMenu() {
		Menu newMenu = new Menu("New");

		MenuItem newJavaProject = new MenuItem("Java Project");
		ImageView javaProjectImg = new ImageView("/assets/img/java_project.png");
		javaProjectImg.setFitWidth(20);
		javaProjectImg.setPreserveRatio(true);
		javaProjectImg.setSmooth(true);
		javaProjectImg.setCache(true);
		newJavaProject.setGraphic(javaProjectImg);

		MenuItem newProject = new MenuItem("Project");
		ImageView projectImg = new ImageView("/assets/img/folder.png");
		projectImg.setFitWidth(20);
		projectImg.setPreserveRatio(true);
		projectImg.setSmooth(true);
		projectImg.setCache(true);
		newProject.setGraphic(projectImg);

		MenuItem newPackage = new MenuItem("Package");
		ImageView packageImg = new ImageView("/assets/img/package.png");
		packageImg.setFitWidth(20);
		packageImg.setPreserveRatio(true);
		packageImg.setSmooth(true);
		packageImg.setCache(true);
		newPackage.setGraphic(packageImg);

		MenuItem newClass = new MenuItem("Class");
		ImageView classImg = new ImageView("/assets/img/class.png");
		classImg.setFitWidth(20);
		classImg.setPreserveRatio(true);
		classImg.setSmooth(true);
		classImg.setCache(true);
		newClass.setGraphic(classImg);

		MenuItem newInterface = new MenuItem("Interface");
		ImageView interfaceImg = new ImageView("/assets/img/interface.png");
		interfaceImg.setFitWidth(20);
		interfaceImg.setPreserveRatio(true);
		interfaceImg.setSmooth(true);
		interfaceImg.setCache(true);
		newInterface.setGraphic(interfaceImg);

		MenuItem newSourceFolder = new MenuItem("Source Folder");
		ImageView sourceFolderImg = new ImageView("/assets/img/source_folder.png");
		sourceFolderImg.setFitWidth(20);
		sourceFolderImg.setPreserveRatio(true);
		sourceFolderImg.setSmooth(true);
		sourceFolderImg.setCache(true);
		newSourceFolder.setGraphic(sourceFolderImg);

		MenuItem newEnum = new MenuItem("Enum");
		ImageView enumImg = new ImageView("/assets/img/enum.png");
		enumImg.setFitWidth(20);
		enumImg.setPreserveRatio(true);
		enumImg.setSmooth(true);
		enumImg.setCache(true);
		newEnum.setGraphic(enumImg);

		MenuItem newAnnotation = new MenuItem("Annotation");
		ImageView annotationImg = new ImageView("/assets/img/annotation.png");
		annotationImg.setFitWidth(20);
		annotationImg.setPreserveRatio(true);
		annotationImg.setSmooth(true);
		annotationImg.setCache(true);
		newAnnotation.setGraphic(annotationImg);

		newMenu.getItems().addAll(newJavaProject, newProject, new SeparatorMenuItem(), newSourceFolder, newPackage, newClass,
				newInterface, newEnum, newAnnotation);
		return newMenu;
	}

	private void onClose(Stage window) {
		boolean shouldClose = ConfirmWindow.displayWindow("Quit", "Are you sure you would like to quit?");
		if (shouldClose)
			window.close();
	}
}