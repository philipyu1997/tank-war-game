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
import java.util.List;

/**
 * @author Philip Yu
 */
public class Bullet extends MovableObject {


    // CONSTANTS
    private final static double SHELL_DAMAGE = 10;
    private final int MOVEMENT_SPEED = 4;
    private int explosion_type;

    // OBJECTS
    private BufferedImage bulletImage;
    private Game game;
    private Handler handler;
    private Texture tex;
    private Explosion explosion;

    public Bullet(Entity entity, Player player, int x, int y, int velX, int velY, int angle, int bulletType, Game game, Handler handler) {


        super(entity,
                x + player.getPlayerImage().getWidth() / 3,
                y + player.getPlayerImage().getHeight() / 3,
                velX * 10, velY * 10, angle);
        this.tex = Game.getInstance();
        this.game = game;
        this.handler = handler;

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
                this.bulletImage = tex.spr_weapon[0];
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

    public static double getShellDamage() {

        return SHELL_DAMAGE;

    }

    private void checkCollision() {

        for (int i = 0; i < handler.objectList.size(); ++i) {
            GameObject gameObject = handler.objectList.get(i);

            if (gameObject.getEntity() == Entity.Block) {
                Block block = (Block) gameObject;

                if (getBounds().intersects(block.getBounds())) {
                    handler.removeObject(block);
                    explosion = new Explosion(Entity.Explosion, explosion_type, x, y, tex);
                    handler.addObject(explosion);
                    handler.removeObject(this);
                }
            }

            if (gameObject.getEntity() == Entity.Player) {
                Player player = (Player) gameObject;

                if (!player.getBulletList().contains(this)) {
                    if (getBounds().intersects(player.getBounds())) {
                        explosion = new Explosion(Entity.Explosion, explosion_type, x, y, tex);
                        handler.addObject(explosion);
                        handler.removeObject(this);

                        if (player.getHealth() > 0) {
                            player.setHealth(player.getHealth() - SHELL_DAMAGE);
                        } else if (player.getHealth() == 0) {
                            handler.removeObject(player);
                        }
                    }
                }
            }

            if (gameObject.getEntity() == Entity.Enemy) {
                Player enemy = (Player) gameObject;

                if (!enemy.getBulletList().contains(this)) {
                    if (getBounds().intersects(enemy.getBounds())) {
                        explosion = new Explosion(Entity.Explosion, explosion_type, x, y, tex);
                        handler.addObject(explosion);
                        handler.removeObject(this);

                        if (enemy.getHealth() > 0) {
                            enemy.setHealth(enemy.getHealth() - SHELL_DAMAGE);
                        } else if (enemy.getHealth() == 0) {
                            handler.removeObject(enemy);
                        }
                    }
                }
            }

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

    public void checkBorder() {

        if (x < 0) {
            handler.removeObject(this);
        }

        if (x >= Game.getGameWidth() - 16) {
            handler.removeObject(this);

        }

        if (y < 0) {
            handler.removeObject(this);

        }

        if (y >= Game.getGameHeight() - 16) {
            handler.removeObject(this);

        }

    }

} // end class Bullet
