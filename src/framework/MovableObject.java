package framework;

import java.awt.*;
import java.util.List;

/**
 * @author Philip Yu
 */
public abstract class MovableObject extends GameObject {

    protected int velX, velY, angle;

    public MovableObject(int x, int y, int velX, int velY, int angle) {

        super(x, y);
        this.velX = velX;
        this.velY = velY;
        this.angle = angle;

    }

    @Override
    public abstract void tick(List<GameObject> object);

    @Override
    public abstract void render(Graphics g);

    @Override
    public abstract Rectangle getBounds();

    public int getAngle() {

        return angle;

    }

    public void setAngle(int angle) {

        this.angle = angle;

    }

    public int getRevolutions() {

        return angle / 360;

    }

} // end class MovableObject
