package window;

import objects.Player;

/**
 * @author Philip Yu
 */
public class Camera {

    private int x, y;

    public Camera(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void tick(Player player) {

        x = -player.getX() + Game.getWindowWidth() / 2;
        y = -player.getY() + Game.getWindowHeight() / 2;

    }

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

} // end class Camera
