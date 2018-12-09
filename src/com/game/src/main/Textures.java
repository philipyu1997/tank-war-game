package com.game.src.main;

import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Textures {

    public BufferedImage player, bullet, background, enemy;

    public Textures() {

        getTextures();

    }

    private void getTextures() {

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            player = loader.loadImage("src/assets/images/png/transparent/Tank1.png");
            bullet = loader.loadImage("src/assets/images/png/transparent/Shell.png");
            background = loader.loadImage("src/assets/images/bmp/Background.bmp");
            enemy = loader.loadImage("src/assets/images/png/transparent/Tank2.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

} // end class Textures
