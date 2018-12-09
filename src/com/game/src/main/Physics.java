package com.game.src.main;

import com.game.src.classes.EntityA;
import com.game.src.classes.EntityB;

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

} // end class Physics
