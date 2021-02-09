package io.github.railroad.objects;

import java.io.File;

import io.github.railroad.utility.FileUtils;
import io.github.railroad.utility.UIUtils;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateNewJavaFile extends AbstractNewFileWindow {

	public CreateNewJavaFile(String title, String message, JavaClassTypes type) {
		super(title, message, type.ID);
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

	//TODO make it open the file in editor after saving
	@Override
	protected Button saveFile(Stage window) {
		Button yesBtn = UIUtils.createButton(this.message, event -> {
			File file = FileUtils.createNewFile(filePath);
			String code = "";
			if (this.type == JavaClassTypes.CLASS.ID) {
				code = "public class " + file.getName().replace(".java", "") + "{ \n \n}";
				FileUtils.updateFile(file, code);
			} else if (this.type == JavaClassTypes.INTERFACE.ID) {
				code = "public interface " + file.getName().replace(".java", "") + "{ \n \n}";
				FileUtils.updateFile(file, code);
			} else if (this.type == JavaClassTypes.ENUM.ID) {
				code = "public enum " + file.getName().replace(".java", "") + "{ \n \n}";
				FileUtils.updateFile(file, code);
			} else if (this.type == 4) {

			}

			window.close();
		});
		return yesBtn;
	}

}
