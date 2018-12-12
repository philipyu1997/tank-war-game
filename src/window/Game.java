/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;

import framework.*;
import objects.Player;
import objects.PowerUp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Anthony Souza
 * @author Philip Yu
 */
public class Game extends JPanel {

    // CONSTANTS
    private static final String gameTitle = "Tank Wars!";
    private static final int GAME_WIDTH = 1280;
    private static final int GAME_HEIGHT = 1280;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int MINI_MAP_WIDTH = getWindowWidth() / 4;
    private static final int MINI_MAP_HEIGHT = getWindowHeight() / 4;
    private static final int DIVIDER_WIDTH = 1;
    private static final int TANK1_ORIGIN_X = 200, TANK1_ORIGIN_Y = 192,
            TANK2_ORIGIN_X = (GAME_WIDTH - TANK1_ORIGIN_X - 64),
            TANK2_ORIGIN_Y = (GAME_HEIGHT - TANK1_ORIGIN_Y - 64 - 1);
    private final int INITIAL_HEALTH = 100;
    private final int INITIAL_LIVES = 3;

    // GRAPHICS
    private JFrame frame;
    private Graphics2D buffer;
    private Image miniMap;
    private BufferedImage world, leftScreen, rightScreen;

    // OBJECTS
    public static GameState State = GameState.MENU;
    private static Player p1, p2;
    private static Game game;
    private static Handler handler;
    private static Camera c1, c2;
    private static Texture tex;
    private Menu menu;
    private Statistics statistics;
    private HealthBar health;
    private MapLoader mapLoader;
    private PowerUp powerUp;
    private Random rand = new Random();

    // VARIABLES
    private int rocketSpawn = 3;
    private int bouncingSpawn = 2;
    private int energySpawn = 1;

    public static void main(String[] args) {

        game = new Game();
        game.init();

        try {
            while (true) {
                tick();
                game.repaint();
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored.getMessage());
        }

    }

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    public static void tick() {

        if (State == GameState.GAME) {
            handler.tick();
            c1.tick(p1);
            c2.tick(p2);
        }

    }

    public static Texture getInstance() {

        return tex;

    }

    public static GameState getState() {

        return State;

    }

    public static void setState(GameState state) {

        State = state;

    }

    private void init() {

        requestFocus();

        frame = new JFrame(gameTitle);
        world = new BufferedImage(getGameWidth(), getGameHeight(), BufferedImage.TYPE_INT_RGB);

        tex = new Texture();
        handler = new Handler(this);
        mapLoader = new MapLoader(handler);

        // LOAD CAMERA
        c1 = new Camera(0, 0);
        c2 = new Camera(0, 0);

        // LOAD PLAYERS
        p1 = new Player(Entity.Tank1, TANK1_ORIGIN_X, TANK1_ORIGIN_Y, 0, 0, 0, tex, handler, INITIAL_HEALTH, INITIAL_LIVES);
        handler.addObject(p1);
        p2 = new Player(Entity.Tank2, TANK2_ORIGIN_X, TANK2_ORIGIN_Y, 0, 0, 180, tex, handler, INITIAL_HEALTH, INITIAL_LIVES);
        handler.addObject(p2);

        // LOAD KEYBOARD INPUTS
        Peripheral km1 = new Peripheral(p1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_ENTER);
        Peripheral km2 = new Peripheral(p2, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_SPACE);

        frame.addKeyListener(km1);
        frame.addKeyListener(km2);
        frame.addMouseListener(km1);
        frame.addMouseMotionListener(km1);

        // LOAD WALLS
        mapLoader.loadWalls2();

        // LOAD HEALTH BAR
        health = new HealthBar(p1, p2);

        // LOAD STATISTICS
        statistics = new Statistics(p1, p2, handler);

        menu = new Menu();

        // SPAWN POWER UPS
        for (int i = 0; i < rocketSpawn; ++i) {
            powerUp = new PowerUp(Entity.Rocket, rand.nextInt(1280), rand.nextInt(1280), tex);
            handler.addObject(powerUp);
        }

        for (int i = 0; i < bouncingSpawn; ++i) {
            powerUp = new PowerUp(Entity.Bouncing, rand.nextInt(1280), rand.nextInt(1280), tex);
            handler.addObject(powerUp);
        }

        for (int i = 0; i < energySpawn; ++i) {
            powerUp = new PowerUp(Entity.Energy, rand.nextInt(1280), rand.nextInt(1280), tex);
            handler.addObject(powerUp);
        }

        // SETUP FRAME
        frame.setLayout(new BorderLayout());
        frame.add(this);

        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT + 22);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    @Override
    public void paintComponent(Graphics g) {

        if (State == GameState.GAME) {
            Graphics2D g2 = (Graphics2D) g;
            buffer = world.createGraphics();
            super.paintComponent(g2);
            ////////////////////////////////
            // DRAW HERE

            // SETS MINI MAP AND VIEWPOINTS
            miniMap = world.getScaledInstance(MINI_MAP_WIDTH, MINI_MAP_HEIGHT, BufferedImage.SCALE_FAST);
            leftScreen = world.getSubimage(c1.getX(), c1.getY(), WINDOW_WIDTH / 2, WINDOW_HEIGHT);
            rightScreen = world.getSubimage(c2.getX(), c2.getY(), WINDOW_WIDTH / 2, WINDOW_HEIGHT);

            // RENDERS BACKGROUND
            mapLoader.loadBackground(buffer);

            // RENDERS OBJECTS
            handler.render(buffer);
//            statistics.renderMapSkeleton(buffer); // TODO: REMOVE ME; USED FOR DEBUGGING

            // RENDERS SCREEN
            g2.drawImage(world, 0, 0, null);
            g2.drawImage(leftScreen, 0, 0, this);
            g2.drawImage(rightScreen, WINDOW_WIDTH / 2, 0, this);

            // RENDERS MINI MAP
            g2.fillRect((getWindowWidth() / 2) - (DIVIDER_WIDTH / 2), 0, DIVIDER_WIDTH, getWindowHeight());
            g2.drawRect((getWindowWidth() / 2) - (MINI_MAP_WIDTH / 2), 0, MINI_MAP_WIDTH, MINI_MAP_HEIGHT);
            g2.drawImage(miniMap, (getWindowWidth() / 2) - (MINI_MAP_WIDTH / 2), 0, null);

            // RENDERS HEALTH BAR
            health.render(g);

            // USED FOR DEBUGGING - REMOVE ME
//            statistics.renderForeground(g); // TODO: REMOVE ME; USED FOR DEBUGGING
        } else if (State == GameState.MENU) {
            menu.render(g);
        } else if (State == GameState.HELP) {
            menu.render(g);
        } else if (State == GameState.P1_WINS || State == GameState.P2_WINS) {
            menu.render(g);
        }

    }

} // end class Game
