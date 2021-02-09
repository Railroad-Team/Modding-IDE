package io.github.railroad.objects;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CreateNewFileWindow extends AbstractNewFileWindow {
	public CreateNewFileWindow(String title, String message) {
		super(title, message);
	}

	@Override
	public void fileDialogBox(Stage window) {
		FileChooser fileChooser = new FileChooser();

		File file = fileChooser.showSaveDialog(window);
		if (file != null) {
			filePath = file.getAbsolutePath();
			this.pathName.setText(filePath);
		}
		fileChooser.setInitialDirectory(new File(""));
	}

}