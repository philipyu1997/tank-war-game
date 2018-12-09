package com.game.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Player {

    private double x;
    private double y;
    private double velX;
    private double velY;

    private BufferedImage player;

    public Player(double x, double y, Game game) {

        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
//        ImageLoader il = new ImageLoader(game.getImage());

        player = ss.grabImage64(1, 4, 64, 64);
//        player = il.grabImage();

    }

    public void tick() {

        x += velX;
        y += velY;

    }

    public void render(Graphics g) {

        g.drawImage(player, (int) x, (int) y, null);

    }

    public double getX() {

        return x;

    }

    public void setX(double x) {

        this.x = x;

    }

    public double getY() {

        return y;

    }

    public void setY(double y) {

        this.y = y;

    }

    public double getVelX() {

        return velX;

    }

    public void setVelX(double velX) {

        this.velX = velX;

    }

    public double getVelY() {

        return velY;

    }

    public void setVelY(double velY) {

        this.velY = velY;

    }

} // end class Player
