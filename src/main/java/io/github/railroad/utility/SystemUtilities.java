package io.github.railroad.utility;

public interface SystemUtilities {
    String OS = System.getProperty("os.name").toUpperCase();
    boolean WINDOWS = OS.contains("WIN");
    boolean MAC = OS.contains("MAC");
    boolean LINUX = OS.contains("NIX") || OS.contains("NUX") || OS.contains("AIX");
}
