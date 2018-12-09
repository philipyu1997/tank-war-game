package com.game.src.main;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Controller {

    private List<Entity> entityList = new LinkedList<>();

    private Entity entity;

    private Game game;

    public Controller(Game game) {

        this.game = game;

    }

    public void tick() {

        for (int i = 0; i < entityList.size(); ++i) {
            entity = entityList.get(i);

            if (entity.getY() < 0) {
                removeEntity(entity);
            }

            entity.tick();
        }

    }

    public void render(Graphics g) {

        for (int i = 0; i < entityList.size(); ++i) {
            entity = entityList.get(i);
            entity.render(g);
        }

    }

    public void addEntity(Entity block) {

        entityList.add(block);

    }

    public void removeEntity(Entity block) {

        entityList.remove(block);

    }

} // end class Controller
