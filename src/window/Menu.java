package window;

import framework.Texture;

import java.awt.*;

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

        g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);

        // DRAWS BACKGROUND
        g.drawImage(tex.title, 0, 0, 800, 600, null);

        // DRAW TITLE
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

    }

    private void printSimpleString(String s, int width, int x, int y) {

        int strLength = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width / 2 - strLength / 2 - width / 2;
        g2d.drawString(s, start + x, y);

    }

} // end class Menu
