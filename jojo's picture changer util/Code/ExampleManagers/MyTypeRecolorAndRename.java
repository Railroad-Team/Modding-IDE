import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/*
Purpose: take every png in current directory that contains a string and rename it and perform color-changing actions as set put in ColorReplaceData.txt

The idea here is that if u have a font in a directory, you can easilly change it.
First, it is set up such that A_green.png is the image file format where it goes <letter>_<some name or color>.<extension>
Second, this only works on png's in current directory. Because of this, it is recommended to copy one's font to a new folder and then this will rename all of the files as well as recolor them.
*/

class MultipleFilesMultipleColorsExample{
	//args: 0: ext no .
	//1: name after first 2 characters before editing
	//2: name after first 2 characters after editing
	public static void main(String args[]) throws Exception{
		File colorInput = new File("ColorReplaceData.txt");
		Scanner parser = new Scanner(colorInput);
		ArrayList<String> commands = new ArrayList<String>();
		while(parser.hasNextLine()){
			commands.add(parser.nextLine());
			if (commands.get(commands.size() - 1).split(" ").length < 6)
				throw new RuntimeException("Line " + commands.size() + " in ColorReplaceData.txt is not valid!");
		}
		parser.close();
		File folder = new File(System.getProperty("user.dir"));
		for (final File fileEntry : folder.listFiles()) {
			String lineIn = fileEntry.getName();
			if (!lineIn.contains("." + args[0]) || !lineIn.contains(args[1]))
				continue;
			Process p = Runtime.getRuntime().exec("java ColorSeparation " + lineIn + " " + lineIn.substring(0, 2) + args[2] + " " + args[0] + " 1 1 1 1 1 1");
			p.waitFor();
			File original = new File(lineIn);
			original.delete();
			System.out.println("Deleted " + lineIn);
		}
		for (final File fileEntry : folder.listFiles()) {
			String lineIn = fileEntry.getName();
			if (!lineIn.contains("." + args[0]) || !lineIn.contains(args[2]))
				continue;
			System.out.println(lineIn);
			for (String command : commands){
				Process p = Runtime.getRuntime().exec("java ColorSeparation " + lineIn.substring(0, 2) + args[2] + "." + args[0] + " " + lineIn.substring(0, 2) + args[2] + " " + args[0] + " " + command);
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					System.out.println(line);
				}
			}
		}
		System.out.println(commands.size() + " " + folder.listFiles().length);
	}
}