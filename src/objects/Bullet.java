package objects;

import framework.EntityC;
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
public class Bullet extends MovableObject implements EntityC {


    private final static int shellDamage = 10;
    // CONSTANTS
    private final int MOVEMENT_SPEED = 8;
    // OBJECTS
    private BufferedImage bulletImage;
    private Game game;
    private Handler handler;
    private Texture tex;
    private Explosion explosion;

    public Bullet(Player player, int x, int y, int velX, int velY, int angle, int bulletType, Game game, Handler handler) {

        super(x + player.getPlayerImage().getWidth(null) / 3,
                y + player.getPlayerImage().getHeight(null) / 3,
                velX * 10, velY * 10, angle);
        this.tex = Game.getInstance();
//        this.explosion = new Explosion(1, x, y, tex);
        this.game = game;
        this.handler = handler;

//        handler.addObject(explosion);

        switch (bulletType) {
            case 0:
                this.bulletImage = tex.spr_weapon[bulletType];
                break;
            case 1:
                this.bulletImage = tex.spr_weapon[bulletType];
                break;
            case 2:
                this.bulletImage = tex.spr_weapon[bulletType];
                break;
            default:
                this.bulletImage = tex.spr_weapon[2];
                break;
        }

    }

    @Override
    public void tick(List<GameObject> object) {

        velX = (int) Math.round(MOVEMENT_SPEED * Math.cos(Math.toRadians(angle)));
        velY = (int) Math.round(MOVEMENT_SPEED * Math.sin(Math.toRadians(angle)));
        x += velX;
        y += velY;

        checkBorder();
        checkCollision();

    }

    private boolean checkCollision() {

        boolean collide = false;

//        for (int i = 0; i < game.entityListB.size(); ++i) {
//            EntityB entityB = game.entityListB.get(i);
//
//            if (Physics.checkCollision(this, entityB)) {
//                System.out.println("\nCollision Detected!");
////                c.removeEntity(entityB);
//                c.removeEntity(this);
//                System.out.println("Bullet Removed...");
//            }
//        }
//
//        for (int i = 0; i < game.entityListA.size(); ++i) {
//            EntityA entityA = game.entityListA.get(i);
//
//            if (Physics.checkCollision(this, entityA)) {
//                System.out.println("\nCollision Detected!");
////                c.removeEntity(entityA);
//                c.removeEntity(this);
//                System.out.println("Bullet Removed...");
//            }
//        }

        for (int i = 0; i < handler.objectList.size(); ++i) {
            GameObject tempObject = handler.objectList.get(i);

            if (tempObject instanceof Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    collide = true;
                    handler.removeObject(tempObject);
                    explosion = new Explosion(1, x, y, tex);
                    handler.addObject(explosion);
                    handler.removeObject(this);
                }
            }

        }


        return collide;

    }

    public void checkBorder() {

        if (x < 0) {
            handler.removeObject(this);
        }

        if (x >= Game.getWindowWidth() - 16) {
            handler.removeObject(this);

        }

        if (y < 0) {
            handler.removeObject(this);

        }

        if (y >= Game.getWindowHeight() - 16) {
            handler.removeObject(this);

        }

    }

    @Override
    public void render(Graphics g) {

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), bulletImage.getWidth() / 2.0, bulletImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bulletImage, rotation, null);

//        g2d.setColor(Color.BLACK);
//        g2d.draw(getBounds());

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle(x, y, bulletImage.getWidth(), bulletImage.getHeight()));

    }

    public static int getShellDamage() {

        return shellDamage;

    }

} // end class Bullet
