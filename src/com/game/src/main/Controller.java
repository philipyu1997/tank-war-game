package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;

import java.awt.*;
import java.util.LinkedList;

/**
 * @author Philip Yu
 */
public class Controller {

    private LinkedList<EntityA> entityListA = new LinkedList<>();
    private LinkedList<EntityB> entityListB = new LinkedList<>();
    private LinkedList<EntityC> entityListC = new LinkedList<>();

    private EntityA entityA;
    private EntityB entityB;
    private EntityC entityC;

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

        // CLASS C
        for (int i = 0; i < entityListC.size(); ++i) {
            entityC = entityListC.get(i);

            if (entityC.getY() < 0) {
                removeEntity(entityC);
            }

            entityC.tick();
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

        // CLASS C
        for (int i = 0; i < entityListC.size(); ++i) {
            entityC = entityListC.get(i);
            entityC.render(g);
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

    public void addEntity(EntityC block) {

        entityListC.add(block);

    }

    public void removeEntity(EntityC block) {

        entityListC.remove(block);

    }

    public LinkedList<EntityA> getEntityA() {

        return entityListA;

    }

    public LinkedList<EntityB> getEntityB() {

        return entityListB;

    }

    public LinkedList<EntityC> getEntityC() {

        return entityListC;

    }

} // end class Controller
