package com.game.src.main;

import java.awt.*;

/**
 * @author Philip Yu
 */
public class GameObject {

    protected double x, y;

    public GameObject(double x, double y) {

        this.x = x;
        this.y = y;

    }

    public Rectangle getBounds(int width, int height) {

        return (new Rectangle((int) x, (int) y, width, height));

    }

} // end class GameObject
