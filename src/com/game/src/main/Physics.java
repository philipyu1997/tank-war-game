package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

import java.util.LinkedList;

/**
 * @author Philip Yu
 */
public class Physics {

    public static boolean checkCollision(EntityA entityA, LinkedList<EntityB> entityListB) {

        for (int i = 0; i < entityListB.size(); ++i) {
            if (entityA.getBounds().intersects(entityListB.get(i).getBounds())) {
                return true;
            }
        }

        return false;

    }

    public static boolean checkCollision(EntityB entityB, LinkedList<EntityA> entityListA) {

        for (int i = 0; i < entityListA.size(); ++i) {
            if (entityB.getBounds().intersects(entityListA.get(i).getBounds())) {
                return true;
            }
        }

        return false;

    }

} // end class Physics
