package com.game.src.main;

import javax.swing.*;
import java.awt.*;

/**
 * @author Philip Yu
 */
public class Game extends Canvas implements Runnable {

    // CONSTANTS
    private static final int SCREEN_WIDTH = 320;
    private static final int SCREEN_HEIGHT = SCREEN_WIDTH / 12 * 9;
    private static final int SCREEN_SCALE = 2;
    private final String TITLE = "Game";

    // THREADING
    private boolean running = false;
    private Thread thread;

    private synchronized void start() {

        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();

    }

    private synchronized void stop() {

        if (!running)
            return;

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(1);

    }

    @Override
    public void run() {

        while (running) {
            // this would be the game loop
            System.out.println("WORKING");
        }

    }

    public static void main(String[] args) {

        Game game = new Game();

        game.setPreferredSize(new Dimension(SCREEN_WIDTH * SCREEN_SCALE, SCREEN_HEIGHT * SCREEN_SCALE));
        game.setMaximumSize(new Dimension(SCREEN_WIDTH * SCREEN_SCALE, SCREEN_HEIGHT * SCREEN_SCALE));
        game.setMinimumSize(new Dimension(SCREEN_WIDTH * SCREEN_SCALE, SCREEN_HEIGHT * SCREEN_SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();

    }

} // end class Game
