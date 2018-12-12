package objects;

import framework.Entity;
import framework.GameObject;
import framework.MovableObject;
import framework.Texture;
import window.Game;
import window.Handler;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Player extends MovableObject {

    // CONSTANTS
    private final int MOVEMENT_SPEED = 4;
    private final int ROTATION_SPEED = 2;

    // VARIABLES
    private int bulletType;
    private int health;
    private int lives;
    private int width;
    private int height;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    private boolean isShooting;

    // OBJECTS
    private List<Bullet> bulletList = new ArrayList<>();
    private BufferedImage playerImage;
    private Entity bulletEntity;
    private Handler handler;
    private Texture tex;

    public Player(Entity entity, int x, int y, int velX, int velY, int angle, Texture tex, Handler handler, int health, int lives) {

        super(entity, x, y, velX, velY, angle);
        this.handler = handler;
        this.tex = Game.getInstance();
        this.health = health;
        this.lives = lives;
        this.width = tex.spr_tank[0].getWidth();
        this.height = tex.spr_tank[0].getHeight();
        this.isShooting = false;
        bulletType = 0;

        if (entity == Entity.Tank1) {
            playerImage = tex.spr_tank[0];
        } else if (entity == Entity.Tank2) {
            playerImage = tex.spr_tank[1];
        }

    }

    @Override
    public void tick(List<GameObject> object) {

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

        for (int i = 0; i < handler.objectList.size(); ++i) {
            GameObject gameObject = handler.objectList.get(i);

            if (gameObject instanceof Wall) {
                if (getBoundsTop().intersects(gameObject.getBounds())) {
                    y = gameObject.getY() + tex.spr_tank[0].getHeight() / 2 + 2;
                }

                if (getBoundsBottom().intersects(gameObject.getBounds())) {
                    y = gameObject.getY() - tex.spr_tank[0].getHeight() - 2;
                }

                if (getBoundsLeft().intersects(gameObject.getBounds())) {
                    x = gameObject.getX() + tex.spr_tank[0].getWidth() / 2 + 2;
                }

                if (getBoundsRight().intersects(gameObject.getBounds())) {
                    x = gameObject.getX() - tex.spr_tank[0].getWidth() - 2;
                }
            }

            if (gameObject instanceof PowerUp) {
                if (gameObject.getEntity() == Entity.Rocket) {
                    if (getBounds().intersects(gameObject.getBounds())) {
                        handler.removeObject(gameObject);
                        bulletType = 1;
                    }
                } else if (gameObject.getEntity() == Entity.Bouncing) {
                    if (getBounds().intersects(gameObject.getBounds())) {
                        handler.removeObject(gameObject);
                        bulletType = 2;
                    }
                } else if (gameObject.getEntity() == Entity.Energy) {
                    if (getBounds().intersects(gameObject.getBounds())) {
                        handler.removeObject(gameObject);
                        bulletType = 3;
                    }
                }
            }

        }

    }

    private void checkBorder() {

        // TOP BORDER
        if (y < 0)
            y = 0;

        // BOTTOM BORDER
        if (y >= Game.getGameHeight() - tex.spr_tank[0].getHeight())
            y = Game.getGameHeight() - tex.spr_tank[0].getHeight();

        // LEFT BORDER
        if (x <= 0)
            x = 0;

        // RIGHT BORDER
        if (x >= Game.getGameWidth() - tex.spr_tank[0].getWidth())
            x = Game.getGameWidth() - tex.spr_tank[0].getWidth();

    }

    @Override
    public void render(Graphics g) {

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), playerImage.getWidth() / 2.0, playerImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playerImage, rotation, null);

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle(x, y, tex.spr_tank[0].getWidth(), tex.spr_tank[0].getHeight()));

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

    public void setHealth(int health) {

        this.health = health;

    }

    private void fire() {

        Bullet bullet = new Bullet(bulletEntity, bulletType, x, y, velX, velY, angle, handler);

        bulletList.add(bullet);
        handler.addObject(bullet);

    }

    public BufferedImage getPlayerImage() {

        return playerImage;

    }

    public List<Bullet> getBulletList() {

        return bulletList;

    }

    public void setBulletList(List<Bullet> bulletList) {

        this.bulletList = bulletList;

    }

    public int getLives() {

        return lives;

    }

    public void setLives(int lives) {

        this.lives = lives;

    }

} // end class Player
