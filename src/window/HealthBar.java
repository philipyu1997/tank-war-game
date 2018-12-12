package window;

import framework.GameState;
import objects.Player;

import java.awt.*;

/**
 * @author Philip Yu
 */
public class HealthBar {

    // VARIABLES
    private int WINDOW_WIDTH = Game.getWindowWidth();
    private int WINDOW_HEIGHT = Game.getWindowHeight();

    // OBJECTS
    private Player p1, p2;

    public HealthBar(Player p1, Player p2) {

        this.p1 = p1;
        this.p2 = p2;

    }

    public void render(Graphics g) {

        // PLAYER HEALTH BAR
        g.setColor(Color.RED);
        g.fillRect(15, 15, 200, 50);

        if (p1.getHealth() > 0) {
            g.setColor(Color.GREEN);
            g.fillRect(15, 15, p1.getHealth() * 2, 50);
        } else if (p1.getLives() == 1) {
            g.setColor(Color.RED);
//            Game.setState(GameState.P2_WINS);
        }

        g.setColor(Color.GREEN);
        g.drawString("Player Lives: " + p1.getLives(), 15, 90);
        g.drawString("Player Health: " + p1.getHealth(), 15, 105);

        g.setColor(Color.BLACK);
        g.drawRect(15, 15, 200, 50);

        // ENEMY HEALTH BAR
        g.setColor(Color.BLUE);
        g.fillRect(WINDOW_WIDTH - 215, 15, 200, 50);

        if (p2.getHealth() > 0) {
            g.setColor(Color.GREEN);
            g.fillRect(WINDOW_WIDTH - 215, 15, p2.getHealth() * 2, 50);
        } else if (p2.getLives() == 1) {
            g.setColor(Color.BLUE);
//            Game.setState(GameState.P1_WINS);
        }

        g.setColor(Color.GREEN);
        g.drawString("Enemy Lives: " + p2.getLives(), WINDOW_WIDTH - 135, 90);
        g.drawString("Enemy Health: " + p2.getHealth(), WINDOW_WIDTH - 135, 105);

        g.setColor(Color.BLACK);
        g.drawRect(WINDOW_WIDTH - 215, 15, 200, 50);

    }

} // end class HealthBar
