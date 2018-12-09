package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.libs.Animation;

import java.awt.*;

/**
 * @author Philip Yu
 */
public class Pickup extends GameObject implements EntityA {

    private Textures tex;

    private Animation anim;

    public Pickup(double x, double y, Textures tex) {

        super(x, y);
        this.tex = tex;

        anim = new Animation(10, tex.obj_pickup[0], tex.obj_pickup[1], tex.obj_pickup[2], tex.obj_pickup[3]);

    }

    @Override
    public void tick() {

        anim.runAnimation();

    }

    @Override
    public void render(Graphics g) {

        anim.drawAnimation(g, x, y, 0);

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle((int) x, (int) y, 32, 32));

    }

    @Override
    public double getX() {

        return x;

    }

    @Override
    public double getY() {

        return y;

    }

} // end class Pickup
