package io.github.railroad.objects;

import io.github.railroad.utility.FileUtils;
import io.github.railroad.utility.UIUtils;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;

public class CreateNewJavaFile extends AbstractNewFileWindow {
    private final ClassType type;

    public CreateNewJavaFile(String title, String message, ClassType type) {
        super(title, message);
        this.type = type;
        /*
         * Type 1 is Class, Type 2 is Interface, Type 3 is Enums, Type 4 is Annotation
         */
    }

    @Override
    public boolean fileDialogBox(Stage window) {
        final FileChooser fileChooser = new FileChooser();

        final FileChooser.ExtensionFilter javaFilter = new FileChooser.ExtensionFilter("Java Files", "*.java");

        fileChooser.getExtensionFilters().add(javaFilter);
        final File file = fileChooser.showSaveDialog(window);
        if (file != null) {
            filePath = file.getAbsolutePath();
            pathName.setText(filePath);
        }

        fileChooser.setInitialDirectory(new File(""));
        return false;
    }

    //TODO make it open the file in editor after saving
    @Override
    protected Button saveFile(Stage window) {
        return UIUtils.createButton(message, event -> {
            //please put this somewhere else in the event that the user fails to select a path
            if (filePath == null || filePath.equals("File Path")) {
                System.out.println("Input error");
                window.close();
                return;//failed to input
            }

            final File file = FileUtils.createNewFile(filePath);
            if (file != null) {
                final String code;

                if (ClassType.CLASS.equals(type)) {
                    code = ClassType.CLASS.apply(Path.of(filePath));

                    FileUtils.updateFile(file, code);
                } else if (ClassType.ENUM.equals(type)) {
                    code = ClassType.ENUM.apply(Path.of(filePath));

                    FileUtils.updateFile(file, code);
                } else if (ClassType.INTERFACE.equals(type)) {
                    code = ClassType.INTERFACE.apply(Path.of(filePath));

                    FileUtils.updateFile(file, code);
                } else if (ClassType.RECORD.equals(type)) {
                    code = ClassType.RECORD.apply(Path.of(filePath));

                    FileUtils.updateFile(file, code);
                } else {
                    throw new IllegalStateException(type.get() + " is not a supported java file");
                }
                window.close();
            } else {
                // TODO: What happens if the file is null?
            }
        });
    }
}
