package window;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

/**
 * @author Philip Yu
 */
public class BufferedImageLoader {

    private BufferedImage image;

    public BufferedImage loadImage(String path) {

        try {
            image = read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

} // end class BufferedImageLoader
