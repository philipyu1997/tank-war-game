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
    private final int MOVEMENT_SPEED = 4;
    private int SHELL_DAMAGE;
    private int explosion_type;

    // OBJECTS
    private BufferedImage bulletImage;
    private Handler handler;
    private Texture tex;
    private Explosion explosion;

    public Bullet(Entity entity, int bulletType, int x, int y, int velX, int velY, int angle, Handler handler) {

        super(entity,
                x + 64 / 3,
                y + 64 / 3,
                velX * 10, velY * 10, angle);
        this.tex = Game.getInstance();
        this.handler = handler;

        switch (bulletType) {
            case 0:
                bulletImage = tex.spr_weapon[0];
                explosion_type = 1;
                SHELL_DAMAGE = 10;
                break;
            case 1:
                bulletImage = tex.spr_weapon[1];
                explosion_type = 2;
                SHELL_DAMAGE = 20;
                break;
            case 2:
                bulletImage = tex.spr_weapon[2];
                explosion_type = 3;
                SHELL_DAMAGE = 30;
                break;
            case 3:
                bulletImage = tex.spr_weapon[3];
                explosion_type = 2;
                SHELL_DAMAGE = 40;
                break;
            default:
                bulletImage = tex.spr_weapon[0];
                explosion_type = 1;
                SHELL_DAMAGE = 10;
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

    public int getShellDamage() {

        return SHELL_DAMAGE;

    }

    private void checkCollision() {

        for (int i = 0; i < handler.objectList.size(); ++i) {
            GameObject gameObject = handler.objectList.get(i);

            if (gameObject.getEntity() == Entity.Wall1) {
                Wall wall = (Wall) gameObject;

                if (getBounds().intersects(wall.getBounds())) {
                    handler.removeObject(wall);
                    explosion = new Explosion(Entity.Explosion, explosion_type, x, y, tex);
                    handler.addObject(explosion);
                    handler.removeObject(this);
                    explosion.playExplosion();
                }

            } else if (gameObject.getEntity() == Entity.Wall2) {
                Wall wall = (Wall) gameObject;

                if (getBounds().intersects(wall.getBounds())) {
                    explosion = new Explosion(Entity.Explosion, explosion_type, x, y, tex);
                    handler.addObject(explosion);
                    handler.removeObject(this);
                    explosion.playExplosion();
                }
            }

            // TODO: FIX BUG WHERE WHEN PLAYER HEALTH REACHES 0, IT DOES NOT RESET UNTIL YOU HIT IT AGAIN
            if (gameObject.getEntity() == Entity.Tank1) {
                Player player = (Player) gameObject;

                if (!player.getBulletList().contains(this)) {
                    if (getBounds().intersects(player.getBounds())) {
                        explosion = new Explosion(Entity.Explosion, explosion_type, x, y, tex);
                        handler.addObject(explosion);
                        handler.removeObject(this);
                        explosion.playExplosion();

                        if (player.getHealth() > 0) {
                            player.setHealth(player.getHealth() - SHELL_DAMAGE);
                        } else if (player.getHealth() <= 0 && player.getLives() > 1) {
                            player.setLives(player.getLives() - 1);
                            player.setHealth(100);
                        } else if (player.getLives() == 1) {
                            player.setLives(player.getLives() - 1);
                            explosion.playExplosion();
                            explosion = new Explosion(Entity.Explosion, explosion_type, x, y, tex);
                            handler.addObject(explosion);
                            handler.removeObject(player);
                        }
                    }
                }
            }

            // TODO: FIX BUG WHERE WHEN PLAYER HEALTH REACHES 0, IT DOES NOT RESET UNTIL YOU HIT IT AGAIN
            if (gameObject.getEntity() == Entity.Tank2) {
                Player enemy = (Player) gameObject;

                if (!enemy.getBulletList().contains(this)) {
                    if (getBounds().intersects(enemy.getBounds())) {
                        explosion = new Explosion(Entity.Explosion, explosion_type, x, y, tex);
                        handler.addObject(explosion);
                        handler.removeObject(this);
                        explosion.playExplosion();

                        if (enemy.getHealth() > 0) {
                            enemy.setHealth(enemy.getHealth() - SHELL_DAMAGE);
                        } else if (enemy.getHealth() <= 0 && enemy.getLives() > 1) {
                            enemy.setLives(enemy.getLives() - 1);
                            enemy.setHealth(100);
                        } else if (enemy.getLives() == 1) {
                            enemy.setLives(enemy.getLives() - 1);
                            explosion.playExplosion();
                            explosion = new Explosion(Entity.Explosion, explosion_type, x, y, tex);
                            handler.addObject(explosion);
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

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle(x, y, bulletImage.getWidth(), bulletImage.getHeight()));

    }

    private void checkBorder() {

        if (x < 0) {
            handler.removeObject(this);
        }

        if (x >= Game.getGameWidth() - tex.spr_weapon[0].getWidth()) {
            handler.removeObject(this);

        }

        if (y < 0) {
            handler.removeObject(this);

        }

        if (y >= Game.getGameHeight() - tex.spr_weapon[0].getWidth()) {
            handler.removeObject(this);

        }

    }

} // end class Bullet
