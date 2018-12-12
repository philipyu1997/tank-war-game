package window;

import framework.GameState;
import framework.Texture;

import java.awt.*;

import static window.Game.State;
import static window.Game.getWindowWidth;

/**
 * @author Philip Yu
 */
public class Menu {

    public Rectangle playButton = new Rectangle(Game.getWindowWidth() / 2 - 60, 150, 120, 50);
    public Rectangle helpButton = new Rectangle(Game.getWindowWidth() / 2 - 60, 250, 120, 50);
    public Rectangle quitButton = new Rectangle(Game.getWindowWidth() / 2 - 60, 350, 120, 50);
    Graphics2D g2d;
    Texture tex = new Texture();

    public void render(Graphics g) {

        if (State == GameState.MENU) {
            g2d = (Graphics2D) g;

            // DRAWS BACKGROUND
            g.drawImage(tex.title, 0, 0, 800, 600, null);

            // DRAW TITLE
            Font fnt0 = new Font("arial", Font.BOLD, 50);
            g.setFont(fnt0);

            g.setColor(Color.WHITE);
            printSimpleString("TANK GAME", fnt0.getSize(), Game.getWindowWidth() / 2, 100);

            // DRAW OPTIONS
            Font fnt1 = new Font("arial", Font.BOLD, 30);
            g.setFont(fnt1);

            g.setColor(Color.BLACK);
            g.fillRect(playButton.x, playButton.y, 120, 50);
            g.setColor(Color.WHITE);
            printSimpleString("1. Play", 50, playButton.x + 60, playButton.y + 35);
            g2d.draw(playButton);

            g.setColor(Color.BLACK);
            g.fillRect(helpButton.x, helpButton.y, 120, 50);
            g.setColor(Color.WHITE);
            printSimpleString("2. Help", 50, helpButton.x + 60, helpButton.y + 35);
            g2d.draw(helpButton);

            g.setColor(Color.BLACK);
            g.fillRect(quitButton.x, quitButton.y, 120, 50);
            g.setColor(Color.WHITE);
            printSimpleString("3. Quit", 50, quitButton.x + 60, quitButton.y + 35);
            g2d.draw(quitButton);
        } else if (State == GameState.END) {

            g2d = (Graphics2D) g;

            // DRAWS BACKGROUND
            g.fillRect(0, 0, 800, 600);

            Font fnt0 = new Font("arial", Font.BOLD, 50);
            g.setFont(fnt0);
            g.setColor(Color.WHITE);

            printSimpleString("GAME OVER", fnt0.getSize(), Game.getWindowWidth() / 2, 200);

            Font fnt1 = new Font("arial", Font.BOLD, 30);
            g.setFont(fnt1);

            printSimpleString("Press M for Menu", fnt1.getSize(), getWindowWidth() / 2, 300);
            printSimpleString("Press Q to Quit", fnt1.getSize(), getWindowWidth() / 2, 350);

        }

    }

    private void printSimpleString(String s, int width, int x, int y) {

        int strLength = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width / 2 - strLength / 2 - width / 2;
        g2d.drawString(s, start + x, y);

    }

} // end class Menu
