/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;

import framework.Entity;
import framework.GameState;
import framework.Peripheral;
import framework.Texture;
import objects.HealthBar;
import objects.Pickup;
import objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
    private static final int DIVIDER_WIDTH = 10;
    private static final int TANK1_ORIGIN_X = 200, TANK1_ORIGIN_Y = 192,
            TANK2_ORIGIN_X = (GAME_WIDTH - TANK1_ORIGIN_X - 64),
            TANK2_ORIGIN_Y = (GAME_HEIGHT - TANK1_ORIGIN_Y - 64 - 1);
    // OBJECTS
    public static GameState State = GameState.GAME;
    static Texture tex;
    private static Player p1, p2;
    private static Game game;
    private static Handler handler;
    private static Camera c1, c2;
    int leftViewX, leftViewY, rightViewX, rightViewY;
    int initial_health = 100;
    int lives = 3;
    // GRAPHICS
    private JFrame frame;
    private Graphics2D buffer;
    private Image miniMap;
    private BufferedImage world, leftScreen, rightScreen;
    private Menu menu;
    private HealthBar health;
    private Map map;
    private Sound sound;
    private Pickup pickup;
    private int spaceX = 15;
    private int spaceY = 105;

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
        world = new BufferedImage(getGameWidth(), getGameHeight(), BufferedImage.TYPE_INT_RGB); /* Shows width * height of game world map */

        tex = new Texture();

        handler = new Handler(this);
        map = new Map(handler);

        c1 = new Camera(0, 0);
        c2 = new Camera(0, 0);

        p1 = new Player(Entity.Player, TANK1_ORIGIN_X, TANK1_ORIGIN_Y, 0, 0, 0, tex, this, handler, initial_health, 3);
//        p2 = new Player(Entity.Enemy, TANK2_ORIGIN_X, TANK2_ORIGIN_Y, 0, 0, 180, tex, this, handler, 100);
        p2 = new Player(Entity.Enemy, 300, 192, 0, 0, 180, tex, this, handler, initial_health, 3);


        Peripheral km1 = new Peripheral(p1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_ENTER);
        Peripheral km2 = new Peripheral(p2, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_SPACE);

        handler.addObject(p1);
        handler.addObject(p2);

        map.loadWalls2();

        frame.setLayout(new BorderLayout());
        frame.add(this);

        frame.addKeyListener(km1);
        frame.addKeyListener(km2);
        frame.addMouseListener(km1);
        frame.addMouseMotionListener(km1);

        health = new HealthBar(p1, p2);

        menu = new Menu();

        pickup = new Pickup(Entity.Pickup, 100, 100, tex);
        handler.addObject(pickup);

        pickup = new Pickup(Entity.Pickup, 150, 100, tex);
        handler.addObject(pickup);

        pickup = new Pickup(Entity.Pickup, 200, 100, tex);
        handler.addObject(pickup);

        pickup = new Pickup(Entity.Pickup, 250, 100, tex);
        handler.addObject(pickup);

        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT + 22);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void renderForeground(Graphics g) {

        resetString();
        drawString(g, "");
        drawString(g, "AmountKeysPressed: " + Peripheral.getKeysPressed().size());
        drawList(g, toStringList("KeyPressed: ", Peripheral.getKeysPressed()));
        drawString(g, "");
        drawString(g, "MouseX: " + Peripheral.getMouseX());
        drawString(g, "MouseY: " + Peripheral.getMouseY());
        drawString(g, "");
        drawString(g, "Player1X: " + p1.getX());
        drawString(g, "Player1Y: " + p1.getY());
        drawString(g, "Player1Angle: " + p1.getAngle());
        drawString(g, "Player1Rev:" + p1.getRevolutions());
        drawString(g, "");
        drawString(g, "Player2X: " + p2.getX());
        drawString(g, "Player2Y: " + p2.getY());
        drawString(g, "Player2Angle: " + p2.getAngle());
        drawString(g, "Player2Rev:" + p2.getRevolutions());

    }

    private void resetString() {

        spaceY = 105;

    }

    private void drawString(Graphics g, String text) {

        g.setColor(Color.WHITE);
        g.drawString(text, spaceX, spaceY);
        spaceY += 15;

    }

    private void drawList(Graphics g, java.util.List<String> list) {

        for (String text : list) {
            drawString(g, text);
        }

    }

    private java.util.List<String> toStringList(String pre, java.util.List list) {

        java.util.List<String> stringList = new ArrayList<>();

        for (Object object : list) {
            stringList.add(pre + object.toString());
        }

        return stringList;

    }

    public void drawDemo(Graphics g) {

        // LOAD MAP
        map.loadBackground(g);
        map.loadWalls(g);

        // CAMERA VIEWPOINT
        leftViewX = c1.getX();
        leftViewY = c1.getY();
        rightViewX = c2.getX();
        rightViewY = c2.getY();

    }

    @Override
    public void paintComponent(Graphics g) {


        if (State == GameState.GAME) {

            Graphics2D g2 = (Graphics2D) g;
            buffer = world.createGraphics();
            super.paintComponent(g2);

            // MAKING MINI-MAP, LEFT AND RIGHT VIEWPOINT
            miniMap = world.getScaledInstance(MINI_MAP_WIDTH, MINI_MAP_HEIGHT, BufferedImage.SCALE_FAST);
            leftScreen = world.getSubimage(c1.getX(), c1.getY(), WINDOW_WIDTH / 2, WINDOW_HEIGHT);
            rightScreen = world.getSubimage(c2.getX(), c2.getY(), WINDOW_WIDTH / 2, WINDOW_HEIGHT);

            // LOADS MAP
            drawDemo(buffer);

            // DRAW TANKS
            handler.render(buffer);

            // DRAWING SCREEN
            g2.drawImage(world, 0, 0, null);
            g2.drawImage(leftScreen, 0, 0, this);
            g2.drawImage(rightScreen, WINDOW_WIDTH / 2, 0, this);


            // DRAWING MINI MAP AND DIVIDER
            g2.fillRect((getWindowWidth() / 2) - (DIVIDER_WIDTH / 2), 0, DIVIDER_WIDTH, getWindowHeight());
            g2.drawRect((getWindowWidth() / 2) - (MINI_MAP_WIDTH / 2), 0, MINI_MAP_WIDTH, MINI_MAP_HEIGHT);
            g2.drawImage(miniMap, (getWindowWidth() / 2) - (MINI_MAP_WIDTH / 2), 0, null);

            health.render(g);

            // USED FOR DEBUGGING - REMOVE ME
            renderForeground(g);

        } else if (State == GameState.MENU) {

            menu.render(g);

        } else if (State == GameState.END) {

            menu.render(g);

        }

    }

}
