package objects;

import framework.Entity;
import framework.GameObject;
import framework.Texture;
import window.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Wall extends GameObject {

    // VARIABLES
    private int width, height;
    private int wallType;

    // OBJECTS
    private Texture tex = Game.getInstance();
    private BufferedImage wallImage;

    public Wall(Entity entity, int wallType, int x, int y) {

        super(entity, x, y);
        this.wallType = wallType;
        this.width = tex.spr_wall[wallType].getWidth();
        this.height = tex.spr_wall[wallType].getHeight();

        if (entity == Entity.Wall1) {
            wallImage = tex.spr_wall[0];
        } else if (entity == Entity.Wall2) {
            wallImage = tex.spr_wall[1];
        } else {
            wallImage = tex.spr_wall[0];
        }

    }

    public void tick(List<GameObject> object) {

    }

    public void render(Graphics g) {

        g.drawImage(wallImage, x, y, null);

    }

    public Rectangle getBounds() {

        return (new Rectangle(x, y, width, height));

    }

} // end class Wall
