package window;

import framework.*;
import objects.Explosion;
import objects.HealthBar;
import objects.Pickup;
import objects.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Philip Yu
 */
public class Game extends Canvas implements Runnable {

    // THREADING
    private boolean running = false;
    private Thread thread;

    // CONSTANTS
    private static final long serialVersionUID = 6435385428141935074L;
    private static final int GAME_WIDTH = 1280;
    private static final int GAME_HEIGHT = 1280;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int TANK1_ORIGIN_X = 200, TANK1_ORIGIN_Y = 192,
            TANK2_ORIGIN_X = (GAME_WIDTH - TANK1_ORIGIN_X - 64),
            TANK2_ORIGIN_Y = (GAME_HEIGHT - TANK1_ORIGIN_Y - 64 - 1);
    private static final String TITLE = "Game";
    // OBJECTS
    public static GameState State = GameState.MENU;
    private static Texture tex;
    //VARIABLES
    private int spaceX = 15;
    private int spaceY = 105;
    private Handler handler;
    private HealthBar health;
    private Pickup pickup;
    private Explosion explosion;
    private Player p1, p2;
    private Camera c1, c2;
    private Map map;
    private Menu menu;

    private BufferedImage world = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage leftScreen, rightScreen;
    private BufferedImage background;
    public LinkedList<EntityA> entityListA;
    public LinkedList<EntityB> entityListB;
    public LinkedList<EntityC> entityListC;

    public static void main(String[] args) {

        new Window(WINDOW_WIDTH, WINDOW_HEIGHT, TITLE, new Game());

    }

    public static Texture getInstance() {

        return tex;

    }

    protected synchronized void start() {

        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();

    }

    protected synchronized void stop() {

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

    private void tick() {

        if (State == GameState.GAME) {
            handler.tick();
            c1.tick(p1);
        }

    }

    private void init() {

        requestFocus();

        tex = new Texture();

        background = tex.background;

        handler = new Handler(this);
        map = new Map(handler);

        c1 = new Camera(0, 0);
        c2 = new Camera(0, 0);

        p1 = new Player(0, TANK1_ORIGIN_X, TANK1_ORIGIN_Y, 0, 0, 0, tex, this, handler, 100);
        p2 = new Player(1, TANK2_ORIGIN_X, TANK2_ORIGIN_Y, 0, 0, 180, tex, this, handler, 100);

        Peripheral pe1 = new Peripheral(p1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_ENTER);
        Peripheral pe2 = new Peripheral(p2, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_SPACE);

        addKeyListener(pe1);
        addKeyListener(pe2);
        addMouseListener(pe1);
        addMouseMotionListener(pe1);

        health = new HealthBar(p1, p2);

        menu = new Menu();

//        pickup = new Pickup(100, 100, tex);
        explosion = new Explosion(1, 100, 100, tex);

        handler.addObject(p1);
        handler.addObject(p2);
//        handler.addObject(pickup);
        handler.addObject(explosion);

        map.loadWalls2();

    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        //////////////////////////////////

        g.drawImage(world, 0, 0, getWidth(), getHeight(), this);

        if (State == GameState.GAME) {

            g2d.translate(c1.getX(), c1.getY()); // beginning of camera

            map.loadBackground(g2d);
//            map.loadWalls(g2d);
            handler.render(g2d);

            g2d.translate(-c1.getX(), -c1.getY()); // end of camera

            health.render(g2d);

            renderForeground(g2d);

        } else if (State == GameState.MENU) {

            menu.render(g2d);

        } else if (State == GameState.END) {

            menu.render(g2d);

        }

        //////////////////////////////////
        g.dispose();
        bs.show();


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

} // end class Game
