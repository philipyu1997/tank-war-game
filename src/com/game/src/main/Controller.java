package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

import java.awt.*;
import java.util.LinkedList;

/**
 * @author Philip Yu
 */
public class Controller {

    private LinkedList<EntityA> entityListA = new LinkedList<>();
    private LinkedList<EntityB> entityListB = new LinkedList<>();

    private EntityA entityA;
    private EntityB entityB;

    private Game game;

    public Controller(Game game) {

        this.game = game;

    }

    public void tick() {

        // CLASS A
        for (int i = 0; i < entityListA.size(); ++i) {
            entityA = entityListA.get(i);

            if (entityA.getY() < 0) {
                removeEntity(entityA);
            }

            entityA.tick();
        }

        // CLASS B
        for (int i = 0; i < entityListB.size(); ++i) {
            entityB = entityListB.get(i);

            if (entityB.getY() < 0) {
                removeEntity(entityB);
            }

            entityB.tick();
        }

    }

    public void render(Graphics g) {

        // CLASS A
        for (int i = 0; i < entityListA.size(); ++i) {
            entityA = entityListA.get(i);
            entityA.render(g);
        }

        // CLASS B
        for (int i = 0; i < entityListB.size(); ++i) {
            entityB = entityListB.get(i);
            entityB.render(g);
        }

    }

    public void addEntity(EntityA block) {

        entityListA.add(block);

    }

    public void removeEntity(EntityA block) {

        entityListA.remove(block);

    }

    public void addEntity(EntityB block) {

        entityListB.add(block);

    }

    public void removeEntity(EntityB block) {

        entityListB.remove(block);

    }

    public LinkedList<EntityA> getEntityA() {

        return entityListA;

    }

    public LinkedList<EntityB> getEntityB() {

        return entityListB;

    }

} // end class Controller
