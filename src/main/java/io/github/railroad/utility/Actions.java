package io.github.railroad.utility;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static io.github.railroad.utility.Components.StageFactory.makeStage;

public interface Actions {
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

    static void createNewJavaFile(Templates.JavaTemplate<String> type, String typeName) {
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
                var fileWriter = new FileWriter(file);
                fileWriter.write(type.apply(FilenameUtils.removeExtension(file.getName())));
                fileWriter.close();
            } catch (IOException e) {
                System.err.println("fileWriter threw an IOException in createNewJavaFile.");
            } finally {
                window.close();
            }
        }
    }

    // private static String getPackageName(File root, File current) {
    //     var src = root.getAbsolutePath();
    //     var cur = current.getAbsolutePath();
    //
    //     var prefix = cur.indexOf(src);
    //     var result = cur.substring(prefix + src.length()).replace("\\", "/");
    //
    //     result = result.replace("/", ".");
    //     if (result.startsWith(".")) result = result.substring(1);
    //
    //     return result;
    // }
}
