package framework;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Animation {


    private int speed;
    private int frames;
    private int index = 0;
    private int count = 0;

    private BufferedImage[] bimg;

    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage[] bimg) {

        this.speed = speed;
        this.bimg = bimg;
        frames = bimg.length;

    }

    public void runAnimation() {

        index++;

        if (index > speed) {
            index = 0;
            nextFrame();
        }

    }

    public void nextFrame() {

        for (int i = 0; i < frames; ++i) {
            if (count == i)
                currentImg = bimg[i];
        }

        count++;

        if (count > frames) {
            count = 0;
//            frames = -1;
        }

    }

    public void drawAnimation(Graphics g, double x, double y, int offset) {

        g.drawImage(currentImg, (int) x - offset, (int) y, null);

    }

    public int getCount() {

        return count;

    }

    public void setCount(int count) {

        this.count = count;

    }

    public int getSpeed() {

        return speed;

    }

    public void setSpeed(int speed) {

        this.speed = speed;

    }

} // end class Animation
