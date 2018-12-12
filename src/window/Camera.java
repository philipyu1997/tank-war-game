package window;

import objects.Player;

/**
 * @author Philip Yu
 */
public class Camera {

    private int x, y;
    private final int GAME_WIDTH = Game.getGameWidth();
    private final int GAME_HEIGHT = Game.getGameHeight();
    private final int WINDOW_WIDTH = Game.getWindowWidth();
    private final int WINDOW_HEIGHT = Game.getWindowHeight();

    public Camera(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void tick(Player player) {

        x = player.getX() - Game.getWindowWidth() / 4;
        y = player.getY() - Game.getWindowHeight() / 4;

        checkBorder();

    }

    public void checkBorder() {

        // TOP
        if (y < 0)
            y = 0;

        // BOTTOM
        if (y >= (GAME_HEIGHT - WINDOW_HEIGHT))
            y = GAME_HEIGHT - WINDOW_HEIGHT;

        // LEFT
        if (x < 0) {
            x = 0;
        }

        // RIGHT
        if (x >= GAME_WIDTH - (WINDOW_WIDTH / 2)) {
            x = GAME_WIDTH - (WINDOW_WIDTH / 2);
        }

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
