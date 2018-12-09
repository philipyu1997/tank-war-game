package com.game.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Player {

    private double x;
    private double y;

    private BufferedImage player;

    public Player(double x, double y, Game game) {

        this.x = x;
        this.y = y;

//        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
        ImageLoader il = new ImageLoader(game.getImage());

//        player = ss.grabImage64(1, 4, 64, 64);
        player = il.grabImage();

    }

    public void tick() {

        x++;

    }

    public void render(Graphics g) {

        g.drawImage(player, (int) x, (int) y, null);

    }

} // end class Player
