package com.game.src.main;

import com.game.src.main.classes.EntityA;

import java.awt.*;

/**
 * @author Philip Yu
 */
public class Bullet extends GameObject implements EntityA {

    private Textures tex;
    private Game game;
    private Controller c;

    public Bullet(double x, double y, Textures tex, Game game, Controller c) {

        super(x, y);
        this.tex = tex;
        this.game = game;
        this.c = c;

    }

    public void tick() {

        y -= 10;

        checkCollision();

    }

    private void checkCollision() {

        if (Physics.checkCollision(this, game.entityListB)) {
            System.out.println("Collision Detected!");
            c.removeEntity(this);
            System.out.println("Bullet Removed...");
        }

    }

    public void render(Graphics g) {

        g.drawImage(tex.bullet, (int) x, (int) y, null);

    }

    public Rectangle getBounds() {

        return (new Rectangle((int) x, (int) y, 24, 24));

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
