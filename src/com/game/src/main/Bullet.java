package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import window.Game;

import java.awt.*;

/**
 * @author Philip Yu
 */
public class Bullet extends GameObject implements EntityC {

    private Textures tex;
    private Game game;
    private Controller c;
    private static int shellDamage = 10;

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

        for (int i = 0; i < game.entityListB.size(); ++i) {
            EntityB entityB = game.entityListB.get(i);

            if (Physics.checkCollision(this, entityB)) {
                System.out.println("\nCollision Detected!");
//                c.removeEntity(entityB);
                c.removeEntity(this);
                System.out.println("Bullet Removed...");
            }
        }

        for (int i = 0; i < game.entityListA.size(); ++i) {
            EntityA entityA = game.entityListA.get(i);

            if (Physics.checkCollision(this, entityA)) {
                System.out.println("\nCollision Detected!");
//                c.removeEntity(entityA);
                c.removeEntity(this);
                System.out.println("Bullet Removed...");
            }
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

    public static int getShellDamage() {

        return shellDamage;

    }

} // end class Bullet
