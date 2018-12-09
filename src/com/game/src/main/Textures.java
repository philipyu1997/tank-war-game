package com.game.src.main;

import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Textures {

    public BufferedImage player, bullet, background, enemy;
    public BufferedImage[] obj_pickup = new BufferedImage[4];

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
            obj_pickup[0] = loader.loadImage("src/assets/images/png/frames/transparent/Pickup_transparent/pickup_frame_0.png");
            obj_pickup[1] = loader.loadImage("src/assets/images/png/frames/transparent/Pickup_transparent/pickup_frame_1.png");
            obj_pickup[2] = loader.loadImage("src/assets/images/png/frames/transparent/Pickup_transparent/pickup_frame_2.png");
            obj_pickup[3] = loader.loadImage("src/assets/images/png/frames/transparent/Pickup_transparent/pickup_frame_3.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

} // end class Textures
