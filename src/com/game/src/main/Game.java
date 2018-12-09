package com.game.src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Game extends Canvas implements Runnable {

    // THREADING
    private boolean running = false;
    private Thread thread;

    // CONSTANTS
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private final String TITLE = "Game";

    // VARIABLES
    private BufferedImage world = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage player = null, bullet = null, background = null;
    private BufferedImage image = null;
    private boolean isShooting = false;

    // OBJECTS
    private Player p;
    private Controller c;

    public static void main(String[] args) {

        Game game = new Game();

        game.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        game.setMaximumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        game.setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();

    }

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

        init();

        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();

            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }

            render();
            frames++;


            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("\nFPS: " + frames);
                System.out.println("TICKS: " + updates);
                updates = 0;
                frames = 0;
            }
        }

        stop();

    }

    private void tick() {

        p.tick();
        c.tick();

    }

    private void init() {

        requestFocus();

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            player = loader.loadImage("src/assets/images/png/spritesheet/transparent/Tank1.png");
            bullet = loader.loadImage("src/assets/images/png/spritesheet/transparent/Shell.png");
            background = loader.loadImage("src/assets/images/bmp/Background.bmp");
//            image = loader.loadImage("src/assets/images/png/transparent/Tank1.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyInput(this));

        p = new Player(200, 200, this);
        c = new Controller(this);

    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //////////////////////////////////

        g.drawImage(world, 0, 0, getWidth(), getHeight(), this);

        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();

        int fillBgX = SCREEN_WIDTH / bgWidth;
        int fillBgY = SCREEN_HEIGHT / bgHeight;

        /**
         * Apply background image to map
         */
        for (int col = 0; col <= fillBgY; ++col) {
            for (int row = 0; row <= fillBgX; ++row) {
                g.drawImage(background, row * bgWidth, col * bgHeight, bgWidth, bgHeight, null);
            }
        }

        p.render(g);
        c.render(g);

        //////////////////////////////////
        g.dispose();
        bs.show();


    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(5);
        } else if (key == KeyEvent.VK_LEFT) {
            p.setVelX(-5);
        } else if (key == KeyEvent.VK_DOWN) {
            p.setVelY(5);
        } else if (key == KeyEvent.VK_UP) {
            p.setVelY(-5);
        } else if (key == KeyEvent.VK_SPACE && !isShooting) {
            isShooting = true;
            c.addBullet(new Bullet(p.getX(), p.getY(), this));
        } else if (key == KeyEvent.VK_Q) {
            System.out.println("\nExiting...");
            System.exit(1);
        }

    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_LEFT) {
            p.setVelX(0);
        } else if (key == KeyEvent.VK_DOWN) {
            p.setVelY(0);
        } else if (key == KeyEvent.VK_UP) {
            p.setVelY(0);
        } else if (key == KeyEvent.VK_SPACE) {
            isShooting = false;
        }

    }

    public BufferedImage getPlayerImage() {

        return player;

    }

    public BufferedImage getBulletImage() {

        return bullet;

    }

    public BufferedImage getImage() {

        return image;

    }

} // end class Game
