package io.github.railroad.utility;

import static com.sun.javafx.util.Utils.*;
import static java.lang.Runtime.getRuntime;

/**
 * Util class to open windows, linux or mac terminal
 *
 * @author saksham4106
 */
public interface Terminal {

    String WINDOWS = "cmd.exe /c start",
            LINUX = "bash -c start %s %s",
            MAC = "/bin/bash -c start %s %s";

    static void openTerminal() {
        try {
            if (isWindows()) getRuntime().exec(WINDOWS);
            else if (isUnix()) getRuntime().exec(LINUX);
            else if (isMac()) getRuntime().exec(MAC);
        } catch (Exception exception) {
            throw new RuntimeException("Exception opening a terminal", exception);
        }
    }
}
