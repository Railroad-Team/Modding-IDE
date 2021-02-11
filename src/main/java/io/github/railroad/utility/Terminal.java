package io.github.railroad.utility;

import java.nio.file.Path;

import static com.sun.javafx.util.Utils.*;
import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;

/**
 * Util class to open windows, linux or mac terminal
 *
 * @author saksham4106
 */
public interface Terminal {

    String WINDOWS = "cmd.exe /c start %s %s",
            LINUX = "bash -c start %s %s",
            MAC = "/bin/bash -c start %s %s";

    static void openTerminal(Path file) {
        try {
            if (isWindows()) getRuntime().exec(format(WINDOWS, null, file.toFile()));
            else if (isUnix()) getRuntime().exec(format(LINUX, null, file.toFile()));
            else if (isMac()) getRuntime().exec(format(MAC, null, file.toFile()));
        } catch (Exception exception) {
            throw new RuntimeException("Exception opening a terminal", exception);
        }
    }
}
