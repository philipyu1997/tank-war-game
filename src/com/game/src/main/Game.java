package com.game.src.main;

import com.game.src.classes.EntityA;
import com.game.src.classes.EntityB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

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
    private boolean isShooting = false;

    // OBJECTS
    private Player player;
    private Controller c;
    private Textures tex;
    private Enemy enemy;

    public LinkedList<EntityA> entityListA;
    public LinkedList<EntityB> entityListB;


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

        player.tick();
        enemy.tick();
        c.tick();

    }

    public static int getScreenWidth() {

        return SCREEN_WIDTH;

    }

    public static int getScreenHeight() {

        return SCREEN_HEIGHT;

    }

    private void init() {

        requestFocus();

        tex = new Textures();

        addKeyListener(new KeyInput(this));

        player = new Player(200, 200, tex);
        enemy = new Enemy(300, 200, tex);
        c = new Controller(this);

        entityListA = c.getEntityA();
        entityListB = c.getEntityB();

        entityListB.add(enemy);

    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D) {
            player.setVelX(0);
        } else if (key == KeyEvent.VK_A) {
            player.setVelX(0);
        } else if (key == KeyEvent.VK_S) {
            player.setVelY(0);
        } else if (key == KeyEvent.VK_D) {
            player.setVelY(0);
        } else if (key == KeyEvent.VK_ENTER) {
            isShooting = false;
        }

        if (key == KeyEvent.VK_L) {
            enemy.setVelX(0);
        } else if (key == KeyEvent.VK_J) {
            enemy.setVelX(0);
        } else if (key == KeyEvent.VK_K) {
            enemy.setVelY(0);
        } else if (key == KeyEvent.VK_I) {
            enemy.setVelY(0);
//        } else if (key == KeyEvent.VK_SPACE) {
//            isShooting = false;
        }

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

        int bgWidth = tex.background.getWidth();
        int bgHeight = tex.background.getHeight();

        int fillBgX = SCREEN_WIDTH / bgWidth;
        int fillBgY = SCREEN_HEIGHT / bgHeight;

        for (int col = 0; col <= fillBgY; ++col) {
            for (int row = 0; row <= fillBgX; ++row) {
                g.drawImage(tex.background, row * bgWidth, col * bgHeight, bgWidth, bgHeight, null);
            }
        }

        player.render(g);
        enemy.render(g);
        c.render(g);

        //////////////////////////////////
        g.dispose();
        bs.show();


    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_D) {
            player.setVelX(5);
        } else if (key == KeyEvent.VK_A) {
            player.setVelX(-5);
        } else if (key == KeyEvent.VK_S) {
            player.setVelY(5);
        } else if (key == KeyEvent.VK_D) {
            player.setVelY(-5);
        } else if (key == KeyEvent.VK_ENTER && !isShooting) {
            isShooting = true;
            c.addEntity(new Bullet(player.getX(), player.getY(), tex, this));
        }

        if (key == KeyEvent.VK_Q) {
            System.out.println("\nExiting...");
            System.exit(1);
        }

        if (key == KeyEvent.VK_L) {
            enemy.setVelX(5);
        } else if (key == KeyEvent.VK_J) {
            enemy.setVelX(-5);
        } else if (key == KeyEvent.VK_K) {
            enemy.setVelY(5);
        } else if (key == KeyEvent.VK_I) {
            enemy.setVelY(-5);
//        } else if (key == KeyEvent.VK_SPACE && !isShooting) {
//            isShooting = true;
//            c.addEntity(new Bullet(enemy.getX(), enemy.getY(), tex));
        }

    }

} // end class Game
