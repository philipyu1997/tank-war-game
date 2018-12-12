package objects;

import framework.*;

import java.awt.*;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Explosion extends GameObject {

    // VARIABLES
    private int explosion_type;

    // OBJECTS
    private Animation explosion_large;
    private Animation explosion_small;
    private Texture tex;
    private SoundPlayer exp_large_snd;
    private SoundPlayer exp_small_snd;

    public Explosion(Entity entity, int explosion_type, int x, int y, Texture tex) {

        super(entity, x, y);
        this.explosion_type = explosion_type;
        this.tex = tex;

        exp_large_snd = new SoundPlayer(2, tex.exp_large_snd_path);
        exp_small_snd = new SoundPlayer(2, tex.exp_small_snd_path);

        explosion_large = new Animation(10, tex.explosion_large);
        explosion_small = new Animation(10, tex.explosion_small);

    }

    @Override
    public void tick(List<GameObject> object) {

        switch (explosion_type) {
            case 1:
                explosion_small.runAnimation();
                break;
            case 2:
                explosion_large.runAnimation();
                break;
            default:
                explosion_small.runAnimation();
                break;
        }

    }

    @Override
    public void render(Graphics g) {

        switch (explosion_type) {
            case 1:
                explosion_small.drawAnimation(g, x, y, 0);
                break;
            case 2:
                explosion_large.drawAnimation(g, x, y, 0);
                break;
            default:
                explosion_small.drawAnimation(g, x, y, 0);
                break;
        }

    }

    @Override
    public Rectangle getBounds() {

        switch (explosion_type) {
            case 1:
                return (new Rectangle(x, y, tex.explosion_small[0].getWidth(), tex.explosion_small[0].getHeight()));
            case 2:
                return (new Rectangle(x, y, tex.explosion_large[0].getWidth(), tex.explosion_large[0].getHeight()));
            default:
                return (new Rectangle(x, y, tex.explosion_small[0].getWidth(), tex.explosion_small[0].getHeight()));
        }

    }

    public void playExplosion() {

        switch (explosion_type) {
            case 1:
                exp_small_snd.play();
                break;
            case 2:
                exp_large_snd.play();
                break;
            default:
                exp_small_snd.play();
                break;
        }

    }

} // end class Explosion
