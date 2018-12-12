package objects;

import framework.Entity;
import framework.GameObject;
import framework.Texture;
import window.Game;

import java.awt.*;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Block extends GameObject {

    // VARIABLES
    private int width, height;
    private int wallType;

    // OBJECTS
    private Texture tex = Game.getInstance();

    public Block(Entity entity, int wallType, int x, int y) {

        super(entity, x, y);
        this.wallType = wallType;
        this.width = tex.spr_wall[wallType].getWidth();
        this.height = tex.spr_wall[wallType].getHeight();

    }

    public void tick(List<GameObject> object) {

    }

    public void render(Graphics g) {

        switch (wallType) {
            case 0:
                g.drawImage(tex.spr_wall[wallType], x, y, null);
            case 1:
                g.drawImage(tex.spr_wall[wallType], x, y, null);
        }

    }

    public Rectangle getBounds() {

        return (new Rectangle(x, y, width, height));

    }

} // end class Block
