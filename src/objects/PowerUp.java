package objects;

import framework.Entity;
import framework.GameObject;
import framework.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author Philip Yu
 */
public class PowerUp extends GameObject {

    // VARIABLES
    private BufferedImage powerUpImage;
    private Texture tex;

    public PowerUp(Entity entity, int x, int y, Texture tex) {

        super(entity, x, y);
        this.tex = tex;

        if (entity == Entity.Rocket) {
            powerUpImage = tex.spr_pickup[0];
        } else if (entity == Entity.Bouncing) {
            powerUpImage = tex.spr_pickup[1];
        } else if (entity == Entity.Energy) {
            powerUpImage = tex.spr_pickup[2];
        } else {
            powerUpImage = tex.spr_weapon[0];
        }

    }

    @Override
    public void tick(List<GameObject> object) {

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(powerUpImage, x, y, null);

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle(x, y, tex.spr_pickup[0].getWidth(), tex.spr_pickup[0].getHeight()));

    }

} // end class PowerUp
