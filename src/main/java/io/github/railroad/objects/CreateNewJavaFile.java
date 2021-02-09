package io.github.railroad.objects;

import io.github.railroad.utility.FileUtils;
import io.github.railroad.utility.UIUtils;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CreateNewJavaFile extends AbstractNewFileWindow {

	public CreateNewJavaFile(String title, String message, int type) {
		super(title, message, type);
		/*
		 * Type 1 is Class Type 2 is Interface Type 3 is Enums Type 4 is Annotation
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

	@Override
	protected Button saveFile(Stage window) {
		return UIUtils.createButton(this.message, event -> {
			File file = FileUtils.createNewFile(filePath);
			String code = "";
			if (this.type == 1) {
				code = "public class " + file.getName().replace(".java", "") + "{ \n \n}";
				FileUtils.updateFile(file, code);
			} else if (this.type == 2) {
				code = "public interface " + file.getName().replace(".java", "") + "{ \n \n}";
				FileUtils.updateFile(file, code);
			} else if (this.type == 3) {
				code = "public enum " + file.getName().replace(".java", "") + "{ \n \n}";
				FileUtils.updateFile(file, code);
			} /*else if (this.type == 4) {

			}*/

			window.close();
		});
	}
}
