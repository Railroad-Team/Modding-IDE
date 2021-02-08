import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class MultipleFilesMultipleColorsExample{
	//args: 0: ext no .
	public static void main(String args[]) throws Exception{
		File colorInput = new File("ColorReplaceData.txt");
		Scanner parser = new Scanner(colorInput);
		ArrayList<String> commands = new ArrayList<String>();
		while(parser.hasNextLine()){
			commands.add(parser.nextLine());
			System.out.println(commands.get(commands.size() - 1));
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