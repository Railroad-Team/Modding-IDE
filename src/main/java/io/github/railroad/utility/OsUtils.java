package io.github.railroad.utility;

public class OsUtils {
    private static final String OS = System.getProperty("os.name").toUpperCase();

    public static boolean isWindows = OS.contains("WIN");
    public static boolean isMac = OS.contains("MAC");
    public static boolean isLinux = OS.contains("NIX") || OS.contains("NUX") || OS.contains("AIX");
}
