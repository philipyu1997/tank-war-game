package objects;

import framework.Animation;
import framework.EntityA;
import framework.GameObject;
import framework.Texture;

import java.awt.*;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Pickup extends GameObject implements EntityA {

    private Texture tex;

    private Animation anim;

    public Pickup(int x, int y, Texture tex) {

        super(x, y);
        this.tex = tex;

//        anim = new Animation(10, tex.spr_pickup[0], tex.spr_pickup[1], tex.spr_pickup[2], tex.spr_pickup[3]);

    }

    @Override
    public void tick(List<GameObject> object) {

        anim.runAnimation();

    }

    @Override
    public void render(Graphics g) {

        anim.drawAnimation(g, x, y, 0);

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle(x, y, 32, 32));

    }

} // end class Pickup
