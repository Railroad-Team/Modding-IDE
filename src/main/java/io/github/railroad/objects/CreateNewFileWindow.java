package io.github.railroad.objects;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CreateNewFileWindow extends AbstractNewFileWindow {
	public CreateNewFileWindow(String title, String message) {
		super(title, message);
	}

	@Override
	public boolean fileDialogBox(Stage window) {
		FileChooser fileChooser = new FileChooser();

		File file = fileChooser.showSaveDialog(window);
		if (file != null) {
			filePath = file.getAbsolutePath();
			this.pathName.setText(filePath);
			return true;         // Return true if file is created
		}
		fileChooser.setInitialDirectory(new File(""));
		return false;            // Return false if "cancel" is selected
	}

}