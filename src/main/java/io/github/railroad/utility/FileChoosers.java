package io.github.railroad.utility;

import io.github.railroad.utility.Templates.JavaTemplate;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static io.github.railroad.utility.Components.StageFactory.makeStage;

/**
 * Creates file choosing/creating menus.
 *
 * @author TheOnlyTails
 */
public interface FileChoosers {
    /**
     * Creates a file creation menu with no extension filter.
     */
    static void createNewGenericFile() {
        var fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(""));

        var window = makeStage()
                .center()
                .modality(Modality.APPLICATION_MODAL)
                .title("Create new file")
                .minWidth(250)
                .minHeight(100)
                .resizable(false).get();

        fileChooser.showSaveDialog(window);

        window.close();
    }

    /**
     * Creates a file creation menu with a Java file extension filter, and applies the template of the {@link
     * JavaTemplate} to the created file.
     *
     * @param type     The type of Java object (Class, Interface, Enum, Annotation) created, including what template to
     *                 apply to the created file.
     * @param typeName the name of the Java object type created.
     */
    static void createNewJavaFile(JavaTemplate<String> type, String typeName) {
        var fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Java Files", "*.java"));

        var window = makeStage()
                .center()
                .modality(Modality.APPLICATION_MODAL)
                .title(String.format("Create new %s", typeName))
                .minWidth(250)
                .minHeight(100)
                .resizable(false).get();

        var file = fileChooser.showSaveDialog(window);

        if (file != null) {
            try {
                if (FilenameUtils.getExtension(file.getName()).equals(".java")) {
                    var fileWriter = new FileWriter(file);
                    fileWriter.write(type.apply(FilenameUtils.removeExtension(file.getName())));
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.err.println("fileWriter threw an IOException in createNewJavaFile.");
            } finally {
                window.close();
            }
        }
    }
}
