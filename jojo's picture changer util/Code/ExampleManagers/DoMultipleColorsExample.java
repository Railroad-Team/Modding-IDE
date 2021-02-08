import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.RuntimeException;

class DoMultipleColorsExample{
	/*args:
	0: filename with ext
	*/
	public static void main(String args[]) throws Exception{
		String parsedArgs[] = args[0].split("\\.");
		File colorInput = new File("ColorReplaceData.txt");
		Scanner parser = new Scanner(colorInput);
		ArrayList<String> commands = new ArrayList<String>();
		while(parser.hasNextLine()){
			commands.add(parser.nextLine());
			if (commands.get(commands.size() - 1).split(" ").length < 6)
				throw new RuntimeException("Line " + commands.size() + " in ColorReplaceData.txt is not valid!");
		}
		parser.close();
		System.out.println(args[0] + " " + parsedArgs.length);
		for (String command : commands){
			Runtime.getRuntime().exec("java ColorSeparation " + parsedArgs[0] + "." + parsedArgs[1] + " " + parsedArgs[0] + " " + parsedArgs[1] + " " + command);
			Thread.sleep(50);
		}
	}
}