package com.game.src.main;

import com.game.src.main.classes.EntityA;

import java.awt.*;

/**
 * @author Philip Yu
 */
public class Player extends GameObject implements EntityA {

    private double velX;
    private double velY;

    private Textures tex;

    public Player(double x, double y, Textures tex) {

        super(x, y);
        this.tex = tex;

    }

    public void tick() {

        x += velX;
        y += velY;

        // LEFT
        if (x <= 0)
            x = 0;

        // RIGHT
        if (x >= Game.getScreenWidth() - tex.player.getWidth())
            x = Game.getScreenWidth() - tex.player.getWidth();

        // TOP
        if (y < 0)
            y = 0;

        // BOTTOM
        if (y >= Game.getScreenHeight() - tex.player.getHeight())
            y = Game.getScreenHeight() - tex.player.getHeight();

    }

    public void render(Graphics g) {

        g.drawImage(tex.player, (int) x, (int) y, null);

    }

    public Rectangle getBounds() {

        return (new Rectangle((int) x, (int) y, 64, 64));

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
