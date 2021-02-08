import java.io.*;

/*
This can be used to iterate over many different pictures to replace specific colors.
A demo is loaded up, but feel free to implement in any way that works for you.
*/

class RecolorerManager{
	public static void main(String args[]) throws Exception{
		//java ColorSeparation InputImage.ext OutputImage ext targetR targetG targetB convertR convertG convertB <optional-not shown>convert Alpha
        	Runtime.getRuntime().exec("java ColorSeparation Bolt.png WhiteLightning png 255 216 0 255 255 255");
        	Runtime.getRuntime().exec("java ColorSeparation Bolt.png WhiteLightning jpg 255 216 0 255 255 255");
        	Runtime.getRuntime().exec("java ColorSeparation Test.png TestLightning png 255 216 0 255 255 255");
        	Runtime.getRuntime().exec("java ColorSeparation Test.png TestLightning jpg 255 216 0 255 255 255");
		System.out.print("Finished");
	}
}