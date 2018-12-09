package com.game.src.main;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Controller {

    private List<Bullet> bulletList = new LinkedList<>();

    private Bullet tempBullet;

    private Game game;

    public Controller(Game game) {

        this.game = game;

    }

    public void tick() {

        for (int i = 0; i < bulletList.size(); ++i) {
            tempBullet = bulletList.get(i);

            if (tempBullet.getY() < 0) {
                removeBullet(tempBullet);
            }

            tempBullet.tick();
        }

    }

    public void render(Graphics g) {

        for (int i = 0; i < bulletList.size(); ++i) {
            tempBullet = bulletList.get(i);
            tempBullet.render(g);
        }

    }

    public void addBullet(Bullet block) {

        bulletList.add(block);

    }

    public void removeBullet(Bullet block) {

        bulletList.remove(block);

    }

} // end class Controller
