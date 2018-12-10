package com.game.src.main;

import window.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Philip Yu
 */
public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mouseX = e.getX();
        int mouseY = e.getY();

        // Play Button
        if (mouseX >= Game.getScreenWidth() / 2 - 50 && mouseX <= Game.getScreenWidth() / 2 + 50) {
            if (mouseY >= 150 && mouseY <= 200) {
                // Pressed Play Button
                Game.State = Game.STATE.GAME;
            }
        }

        // Help Button
        if (mouseX >= Game.getScreenWidth() / 2 - 50 && mouseX <= Game.getScreenWidth() / 2 + 50) {
            if (mouseY >= 250 && mouseY <= 300) {
                // Pressed Help Button
                System.out.println("\nMouse Input: Help Button Pressed!");
            }
        }

        // Quit Button
        if (mouseX >= Game.getScreenWidth() / 2 - 50 && mouseX <= Game.getScreenWidth() / 2 + 50) {
            if (mouseY >= 350 && mouseY <= 400) {
                // Pressed Quit Button
                System.out.println("\nMouse Input: Quit Button Pressed...");
                System.out.println("Exiting Game...");
                System.exit(1);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

} // end class MouseInput
