package io.github.railroad.terminal;

import java.io.File;
import java.io.IOException;

import org.jetbrains.annotations.Nullable;

import io.github.railroad.utility.GetOS;

/*
 * Util class to open windows, linux or mac terminal 
 * 
 * @author saksham4106
 * 
 */
public class OpenTerminal {

	
	public static void openTerminal(@Nullable File file) {
		if(GetOS.isWin()) {
			openWindowsTerminalAtFile(file);
		}else if(GetOS.isLinux()) {
			openLinuxTerminalAtFile(file);
		}else if(GetOS.isMac()) {
			openMacTerminalAtFile(file);
		}
	}

	private static void openWindowsTerminalAtFile(File file) {
		try {
			Runtime.getRuntime().exec("cmd.exe /c start", null, file);
		}catch (IOException exception){
			System.out.println("Error launching windows terminal at File");
		}
	}
	
	private static void openLinuxTerminalAtFile(File file) {
		try {
			Runtime.getRuntime().exec("bash -c start", null, file);
		}catch(IOException exception) {
			System.out.println("Error launching linux terminal at File");
		}
	}

	private static void openMacTerminalAtFile(File file) {
		try {
			Runtime.getRuntime().exec("/bin/bash -c start", null, file);
		}catch(IOException exception) {
			System.out.println("Error launching Mac terminal at File");
		}
	}
}
