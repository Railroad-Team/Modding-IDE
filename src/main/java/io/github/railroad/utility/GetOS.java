package io.github.railroad.utility;

public class GetOS {
	private static final String OS_TYPE = System.getProperties().getProperty("os.name");
	
	public static boolean isWin() {
		return OS_TYPE.toLowerCase().contains("win");
	}
	
	public static boolean isLinux() {
		return OS_TYPE.toLowerCase().contains("nix") || OS_TYPE.toLowerCase().contains("nux");
	}
	
	public static boolean isMac() {
		return OS_TYPE.toLowerCase().contains("mac");
	}
}
