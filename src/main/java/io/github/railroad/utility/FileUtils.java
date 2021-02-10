package io.github.railroad.utility;

import javafx.stage.FileChooser;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileUtils {

	public static File createFolder(String pathIn) { // Unused Method
		File newFolder = new File(pathIn);
		newFolder.mkdir(); // Return is ignored. Add check.
		return newFolder;
	}

	public static Optional<String> getExtension(String filename) {
		return Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

	public static File createNewFile(String pathIn) {
		try {
			File dir = new File(pathIn);
			dir.createNewFile(); // Return is ignored. Add check.
			return dir;
		} catch (IOException exception) {
			System.err.println("ERROR creating new file");
			return null;
		}
	}

	public static File openFile(String name) { // Unused Method
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

	public static List<File> getSubFolders(@NotNull File file) {
		return Arrays.asList(file.listFiles(File::isDirectory));
	}

	public static class Folder {
		private final File root;
		private List<File> files;
		private final int depth;
		private List<Folder> subFolders; // IJ wants these local, but that makes no sense. I suggest you make getters.

		public Folder(File rootDirectory, int depth) {
			this.root = rootDirectory;
			this.depth = depth;
			refresh();
		}

		public void refresh() {
			subFolders = new ArrayList<>();
			files = new ArrayList<>();
			for (File subfolder : FileUtils.getSubFolders(this.root))
				subFolders.add(new Folder(subfolder, this.depth + 1));
			Collections.addAll(files, Objects.requireNonNull(root.listFiles((file) -> !file.isDirectory())));
		}
	}
}
