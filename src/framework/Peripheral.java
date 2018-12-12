package framework;

import objects.Player;
import window.Game;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static window.Game.State;

/**
 * @author anthony-pc
 * @author Philip Yu
 */
public class Peripheral extends KeyAdapter implements MouseListener, MouseMotionListener {

    // CONSTANTS
    private static List<Integer> keysPressed = new ArrayList<>();
    private static int mouseX, mouseY;

    // VARIABLES
    private int up, down, left, right, shoot;

    // OBJECTS
    private Player player;

    public Peripheral(Player player, int up, int down, int left, int right, int shoot) {

        this.player = player;
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.shoot = shoot;

    }

    public static List<Integer> getKeysPressed() {

        return keysPressed;

    }

    public static int getMouseX() {

        return mouseX;

    }

    public static int getMouseY() {

        return mouseY;

    }

    /**
     * HANDLES ALL KEYBOARD INPUTS
     */

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        int key = e.getKeyCode();

        if (State == GameState.GAME) {

            if (!keysPressed.contains(key))
                keysPressed.add(key);

            if (key == up) {
                player.toggleUpPressed();
            }

            if (key == down) {
                player.toggleDownPressed();
            }

            if (key == left) {
                player.toggleLeftPressed();
            }

            if (key == right) {
                player.toggleRightPressed();
            }

            if (key == shoot) {
                player.toggleShootPressed();
            }

            if (key == KeyEvent.VK_M) {
                Game.State = GameState.MENU;
            }

            if (key == KeyEvent.VK_Q) {
                System.out.println("\nExiting...");
                System.exit(1);
            }


        } else if (State == GameState.MENU) {

            if (key == KeyEvent.VK_1) {
                Game.State = GameState.GAME;
            } else if (key == KeyEvent.VK_2) {
                System.out.println("\nKey Input: Help Button Pressed");
            } else if (key == KeyEvent.VK_3) {
                System.out.println("\nQuit Button Pressed...");
                System.out.println("Exiting Game...");
                System.exit(1);
            }

        } else if (State == GameState.P1_WINS || State == GameState.P2_WINS) {

            if (key == KeyEvent.VK_Q) {
                System.out.println("\nExiting...");
                System.exit(1);
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        keysPressed.remove((Integer) key);

        if (key == up) {
            player.unToggleUpPressed();
        }

        if (key == down) {
            player.unToggleDownPressed();
        }

        if (key == left) {
            player.unToggleLeftPressed();
        }

        if (key == right) {
            player.unToggleRightPressed();
        }

        if (key == shoot) {
            player.unToggleShootPressed();
        }

    }

    /**
     * HANDLES ALL MOUSE INPUTS
     */

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mouseX = e.getX();
        int mouseY = e.getY();

        // Play Button
        if (mouseX >= Game.getWindowWidth() / 2 - 50 && mouseX <= Game.getWindowWidth() / 2 + 50) {
            if (mouseY >= 150 + 22 && mouseY <= 200 + 22) {
                // Pressed Play Button
                State = GameState.GAME;
            }
        }

        // Help Button
        if (mouseX >= Game.getWindowWidth() / 2 - 50 && mouseX <= Game.getWindowWidth() / 2 + 50) {
            if (mouseY >= 250 + 22 && mouseY <= 300 + 22) {
                // Pressed Help Button
                System.out.println("\nMouse Input: Help Button Pressed!");
            }
        }

        // Quit Button
        if (mouseX >= Game.getWindowWidth() / 2 - 50 && mouseX <= Game.getWindowWidth() / 2 + 50) {
            if (mouseY >= 350 + 22 && mouseY <= 400 + 22) {
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

    /**
     * HANDLES ALL MOUSE MOVEMENTS
     */

    @Override
    public void mouseDragged(MouseEvent e) {

        mouseX = e.getX();
        mouseY = e.getY();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        mouseX = e.getX();
        mouseY = e.getY();

    }

} // end class Peripheral
