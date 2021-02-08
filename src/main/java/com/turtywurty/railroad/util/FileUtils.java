package com.turtywurty.railroad.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.FileChooser;

public class FileUtils {

	public static File createFolder(String pathIn) {
		File newFolder = new File(pathIn);
		newFolder.mkdir();
		return newFolder;
	}

	public static void deleteFile(File file) {
		file.deleteOnExit();
	}

	public static File createNewFile(String pathIn) {
		try {
			File dir = new File(pathIn);
			dir.createNewFile();
			return dir;
		} catch (IOException exception) {
			System.err.println("ERROR creating new file");
			return null;
		}
	}

	public static File openFile(String name) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(name);

		return fileChooser.showOpenDialog(null);
	}

	public static void updateFile(File file, String content) {
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException iox) {
			System.err.println("Error: File does not exist");
		}
	}
}
