package framework;

import java.awt.*;
import java.util.List;

/**
 * @author Philip Yu
 */
public abstract class GameObject {

    protected int x, y;
    protected int velX = 0, velY = 0;

    public GameObject(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public abstract void tick(List<GameObject> object);

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public int getX() {

        return x;

    }

    public void setX(int x) {

        this.x = x;

    }

    public int getY() {

        return y;

    }

    public void setY(int y) {

        this.y = y;

    }

    public int getVelX() {

        return velX;

    }

    public void setVelX(int velX) {

        this.velX = velX;

    }

    public int getVelY() {

        return velY;

    }

    public void setVelY(int velY) {

        this.velY = velY;

    }

} // end class GameObject
