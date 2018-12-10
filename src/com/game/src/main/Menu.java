package com.game.src.main;

import java.awt.*;

/**
 * @author Philip Yu
 */
public class Menu {

    public Rectangle playButton = new Rectangle(Game.getScreenWidth() / 2 - 50, 150, 100, 50);
    public Rectangle helpButton = new Rectangle(Game.getScreenWidth() / 2 - 50, 250, 100, 50);
    public Rectangle quitButton = new Rectangle(Game.getScreenWidth() / 2 - 50, 350, 100, 50);
    Graphics2D g2d;
    Textures tex = new Textures();

    public void render(Graphics g) {

        g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);

        // DRAWS BACKGROUND
        g.drawImage(tex.title, 0, 0, 800, 600, null);

        // DRAW TITLE
        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        printSimpleString("TANK GAME", fnt0.getSize(), Game.getScreenWidth() / 2, 100);

        // DRAW OPTIONS
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        printSimpleString("Play", 50, playButton.x + 50, playButton.y + 35);
        g2d.draw(playButton);
        printSimpleString("Help", 50, helpButton.x + 50, helpButton.y + 35);
        g2d.draw(helpButton);
        printSimpleString("Quit", 50, quitButton.x + 50, quitButton.y + 35);
        g2d.draw(quitButton);

    }

    private void printSimpleString(String s, int width, int x, int y) {

        int strLength = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width / 2 - strLength / 2 - width / 2;
        g2d.drawString(s, start + x, y);

    }

} // end class Menu
