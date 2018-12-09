package com.game.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Bullet {

    BufferedImage image;
    private double x;
    private double y;

    public Bullet(double x, double y, Game game) {

        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getBulletImage());

        image = ss.grabImage24(1, 4, 24, 24);

    }

    public void tick() {

        y -= 10;

    }

    public void render(Graphics g) {

        g.drawImage(image, (int) x, (int) y, null);

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

} // end class Bullet
