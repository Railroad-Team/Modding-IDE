package io.github.railroad.utility;

import static com.sun.javafx.util.Utils.*;
import static java.lang.Runtime.getRuntime;

/**
 * Opens the terminal.
 *
 * @author saksham4106, temedy
 */
public interface Terminal {
    /**
     * the command that opens the terminal in windows
     */
    String WINDOWS = "cmd.exe /c start",
    /**
     * the command that opens the terminal in linux
     */
    LINUX = "bash -c start %s %s",
    /**
     * the command that opens the terminal in mac
     */
    MAC = "/bin/bash -c start %s %s";

    /**
     * Opens the terminal.
     */
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
