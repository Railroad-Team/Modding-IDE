import java.util.Scanner;
import java.io.*;

class ForEachInDirectoryExample{
	/*args:
	0: input file type (include ".")
	1: target file type (ditto)
	2: rTarget
	3: gTarget
	4: bTarget
	5: rReplace
	6: gReplace
	7: bReplace
	*/
	public static void main(String args[]) throws IOException{
		for (String str : args)
			System.out.println(str);
		File folder = new File(System.getProperty("user.dir"));
		for (final File fileEntry : folder.listFiles()) {
			String lineIn = fileEntry.getName();
			if (!lineIn.contains(args[0]))
				continue;
			Runtime.getRuntime().exec("java ColorSeparation " + lineIn + " " + lineIn.substring(0, lineIn.indexOf(".")) + " " + args[1].substring(1) + " " + " " + args[2] + " " + args[3] + " " + args[4] + " " + args[5] + " " + args[6] + " " + args[7]);
		}
	}
}