package io.github.railroad.utility;

public class Utils {

	private static final String OS = System.getProperty("os.name").toUpperCase();

	public static String getOSName() {
		return OS;
	}

	public static boolean isWindows() {
		return OS.contains("WIN");
	}

	public static boolean isMac() {
		return OS.contains("MAC");
	}

	public static boolean isUnix() {
		return OS.contains("NIX") || OS.contains("NUX") || OS.contains("AIX");
	}
}
