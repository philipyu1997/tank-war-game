package objects;

import framework.EntityA;
import framework.GameObject;
import framework.MovableObject;
import framework.Texture;
import window.Game;
import window.Handler;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Player extends MovableObject implements EntityA {

    // CONSTANTS
    private final int MOVEMENT_SPEED = 16;
//    private Handler handler;

    private int health;
    private final int ROTATION_SPEED = 8;
    //    private Texture tex;
    private Game game;
    // OBJECTS
    private BufferedImage playerImage;
    private Handler handler;
    private Texture tex;

    // VARIABLES
    private int width;
    private int height;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    private boolean isShooting;
    private int bulletType;
    private int player;

    public Player(int player, int x, int y, int velX, int velY, int angle, Texture tex, Game game, Handler handler, int health) {

        super(x, y, velX, velY, angle);
        this.handler = handler;
//        this.tex = tex;
        this.tex = Game.getInstance();
        this.game = game;
        this.health = health;
        this.width = tex.spr_tank[player].getWidth();
        this.height = tex.spr_tank[player].getHeight();
        this.isShooting = false;
        this.bulletType = 2;
        this.player = player;

        switch (player) {
            case 0:
                playerImage = tex.spr_tank[player];
                break;
            case 1:
                playerImage = tex.spr_tank[player];
                break;
            default:
                break;
        }

    }

    @Override
    public void tick(List<GameObject> object) {

//        x += velX;
//        y += velY;

        if (UpPressed) {
            moveForwards();
        }

        if (DownPressed) {
            moveBackwards();
        }

        if (LeftPressed) {
            rotateLeft();
        }

        if (RightPressed) {
            rotateRight();
        }

        if (ShootPressed && !isShooting) {
            isShooting = true;
            fire();
        } else if (!ShootPressed) {
            isShooting = false;
        }

        checkBorder();
        checkCollision();

    }

    private void checkCollision() {

//        for (int i = 0; i < game.entityListC.size(); ++i) {
//            EntityC entityC = game.entityListC.get(i);
//
//            if (Physics.checkCollision(entityC, this)) {
//                c.removeEntity(entityC);
//                health -= Bullet.getShellDamage();
//
//                if (health == 0)
//                    c.removeEntity(this);
//
//            }
//        }

        for (int i = 0; i < handler.objectList.size(); ++i) {
            GameObject tempObject = handler.objectList.get(i);

            if (tempObject instanceof Block) {
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + tex.spr_tank[player].getHeight() / 2 + 2;
                }

                if (getBoundsBottom().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - tex.spr_tank[player].getHeight() - 2;
                }

                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + tex.spr_tank[player].getWidth() / 2 + 2;
                }

                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - tex.spr_tank[player].getWidth() - 2;
                }
            }

//            if (tempObject instanceof PowerUp) {
//                if (getBounds().intersects(tempObject.getBounds())) {
//                    handler.removeObject(tempObject);
//                    bulletType = 2;
//                }
//            }

        }

    }

    private void checkBorder() {

        // LEFT
        if (x <= 0)
            x = 0;

        // RIGHT
        if (x >= Game.getGameWidth() - tex.spr_tank[player].getWidth())
            x = Game.getGameWidth() - tex.spr_tank[player].getWidth();

        // TOP
        if (y < 0)
            y = 0;

        // BOTTOM
        if (y >= Game.getGameHeight() - tex.spr_tank[player].getHeight())
            y = Game.getGameHeight() - tex.spr_tank[player].getHeight();

    }

    @Override
    public void render(Graphics g) {

//        g.drawImage(tex.spr_tank[player], x, y, null);

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), playerImage.getWidth() / 2.0, playerImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playerImage, rotation, null);

//        g2d.setColor(Color.WHITE);
//        g2d.draw(getBounds());
//        g2d.draw(getBoundsTop());
//        g2d.draw(getBoundsBottom());
//        g2d.draw(getBoundsLeft());
//        g2d.draw(getBoundsRight());

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle(x, y, tex.spr_tank[player].getWidth(), tex.spr_tank[player].getHeight()));

    }

    public Rectangle getBoundsTop() {

        return (new Rectangle(x + (width / 2) - ((width / 2) / 2), y, width / 2, height / 2));

    }

    public Rectangle getBoundsBottom() {

        return (new Rectangle(x + (width / 2) - ((width / 2) / 2), y + (height / 2), width / 2, height / 2));

    }

    public Rectangle getBoundsLeft() {

        return (new Rectangle(x, y + 5, 5, height - 10));

    }

    public Rectangle getBoundsRight() {

        return (new Rectangle(x + width - 5, y + 5, 5, height - 10));

    }

    public int getHealth() {

        return health;

    }

    public void toggleUpPressed() {

        this.UpPressed = true;

    }

    public void toggleDownPressed() {

        this.DownPressed = true;

    }

    public void toggleRightPressed() {

        this.RightPressed = true;

    }

    public void toggleLeftPressed() {

        this.LeftPressed = true;

    }

    public void toggleShootPressed() {

        this.ShootPressed = true;

    }

    public void unToggleUpPressed() {

        this.UpPressed = false;

    }

    public void unToggleDownPressed() {

        this.DownPressed = false;

    }

    public void unToggleRightPressed() {

        this.RightPressed = false;

    }

    public void unToggleLeftPressed() {

        this.LeftPressed = false;

    }

    public void unToggleShootPressed() {

        this.ShootPressed = false;

    }

    private void moveForwards() {

        velX = (int) Math.round(MOVEMENT_SPEED * Math.cos(Math.toRadians(angle)));
        velY = (int) Math.round(MOVEMENT_SPEED * Math.sin(Math.toRadians(angle)));
        x += velX;
        y += velY;
        checkBorder();

    }

    private void moveBackwards() {

        velX = (int) Math.round(MOVEMENT_SPEED * Math.cos(Math.toRadians(angle)));
        velY = (int) Math.round(MOVEMENT_SPEED * Math.sin(Math.toRadians(angle)));
        x -= velX;
        y -= velY;
        checkBorder();

    }

    private void rotateLeft() {

        this.angle -= this.ROTATION_SPEED;

    }

    private void rotateRight() {

        this.angle += this.ROTATION_SPEED;

    }

    private void fire() {

        handler.addObject(new Bullet(this, x, y, velX, velY, angle, bulletType, game, handler));

    }

    public void setHealth(int health) {

        this.health = health;

    }

    public BufferedImage getPlayerImage() {

        return playerImage;

    }

} // end class Player
