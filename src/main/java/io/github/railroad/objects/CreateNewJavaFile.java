package io.github.railroad.objects;

import io.github.railroad.utility.FileUtils;
import io.github.railroad.utility.UIUtils;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CreateNewJavaFile extends AbstractNewFileWindow {
	private final JavaClassTypes type;

	public CreateNewJavaFile(String title, String message, JavaClassTypes type) {
		super(title, message);
		this.type = type;
		/*
		 * Type 1 is Class, Type 2 is Interface, Type 3 is Enums, Type 4 is Annotation
		 */
	}

	@Override
	public void fileDialogBox(Stage window) {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter javaFilter = new FileChooser.ExtensionFilter("Java Files", "*.java");

		fileChooser.getExtensionFilters().add(javaFilter);
		File file = fileChooser.showSaveDialog(window);
		if (file != null) {
			filePath = file.getAbsolutePath();
			this.pathName.setText(filePath);
		}
		fileChooser.setInitialDirectory(new File(""));

	}

	//TODO make it open the file in editor after saving
	@Override
	protected Button saveFile(Stage window) {
		return UIUtils.createButton(this.message, event -> {
			//please put this somewhere else in the event that the user fails to select a path
			if (filePath == null || filePath.equals("File Path")) {
				System.out.println("Input error");
				window.close();
				return;//failed to input
			}
			File file = FileUtils.createNewFile(filePath);
			assert file != null; // Should be replaced with check
			String code;
			switch (this.type) {
				case CLASS -> {
					code = "public class " + file.getName().replace(".java", "") + "{ \n \n}";
					FileUtils.updateFile(file, code);
				}
				case ENUM -> {
					code = "public interface " + file.getName().replace(".java", "") + "{ \n \n}";
					FileUtils.updateFile(file, code);
				}
				case INTERFACE -> {
					code = "public enum " + file.getName().replace(".java", "") + "{ \n \n}";
					FileUtils.updateFile(file, code);
				}
				default -> throw new IllegalStateException(this.type.name() + " is not a supported java file");
			}
			window.close();
		});
	}

}
