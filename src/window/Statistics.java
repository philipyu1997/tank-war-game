package window;

import framework.Peripheral;
import objects.Player;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Philip Yu
 */
public class Statistics {

    // VARIABLES
    private static int spaceX = 15;
    private static int spaceY = 120;

    // OBJECTS
    private static Player p1, p2;
    private static MapLoader mapLoader;
    private static Handler handler;

    public Statistics(Player p1, Player p2, Handler handler) {

        this.p1 = p1;
        this.p2 = p2;
        this.handler = handler;
        mapLoader = new MapLoader(handler);

    }

    public static void renderMapSkeleton(Graphics g) {

        mapLoader.loadWalls(g);

    }

    public static void renderForeground(Graphics g) {

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
        drawString(g, "Player1Health:" + p1.getHealth());
        drawString(g, "Player1Lives:" + p1.getLives());
        drawString(g, "");
        drawString(g, "Player2X: " + p2.getX());
        drawString(g, "Player2Y: " + p2.getY());
        drawString(g, "Player2Angle: " + p2.getAngle());
        drawString(g, "Player2Rev:" + p2.getRevolutions());
        drawString(g, "Player2Health:" + p2.getHealth());
        drawString(g, "Player2Lives:" + p2.getLives());

    }

    private static void resetString() {

        spaceY = 120;

    }

    private static void drawString(Graphics g, String text) {

        g.setColor(Color.WHITE);
        g.drawString(text, spaceX, spaceY);
        spaceY += 15;

    }

    private static void drawList(Graphics g, java.util.List<String> list) {

        for (String text : list) {
            drawString(g, text);
        }

    }

    private static java.util.List<String> toStringList(String pre, java.util.List list) {

        java.util.List<String> stringList = new ArrayList<>();

        for (Object object : list) {
            stringList.add(pre + object.toString());
        }

        return stringList;

    }

} // end class Statistics
