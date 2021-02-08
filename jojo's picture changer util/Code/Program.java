import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Program {
  public static void main(String[] args) throws Exception {
    BufferedImage src = ImageIO.read(new File("Bolt2.png"));
    BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
    dst.getGraphics().drawImage(src, 0, 0, null);
		
    for (int col = 0; col < dst.getWidth(); col++) {
        for (int row = 0; row < dst.getHeight(); row++) {
            if (dst.getRGB(col, row) == -10240){
                dst.setRGB(col, row, -1);
            }
	    System.out.println(dst.getRGB(col, row) + " :/");
        }
    }
    ImageIO.write(dst, "png", new File("WhiteLightning.png"));
  }
}