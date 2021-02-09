package com.turtywurty.railroad.util;

import javafx.stage.FileChooser;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

	public static List<File> getSubfolders(@NotNull File file){
		return Arrays.asList(Objects.requireNonNull(file.listFiles(File::isDirectory)));
	}

	public static class Folder {
		private List<Folder> subfolders;
		private List<File> files;
		private final File root;
		private final int depth;

		public Folder(File rootDirectory, int depth) {
			this.root = rootDirectory;
			this.depth = depth;
			refresh();
		}

		public void refresh() {
			subfolders = new ArrayList<>();
			files = new ArrayList<>();
			for (File subfolder : FileUtils.getSubfolders(this.root))
				subfolders.add(new Folder(subfolder, this.depth + 1));
			Collections.addAll(files, Objects.requireNonNull(root.listFiles((file) -> !file.isDirectory())));
		}
	}
}
