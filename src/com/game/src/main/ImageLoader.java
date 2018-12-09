package com.game.src.main;

import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class ImageLoader {

    private BufferedImage image;

    public ImageLoader(BufferedImage image) {

        this.image = image;

    }

    public BufferedImage grabImage() {

        return image;

    }

} // end class SpriteSheet
