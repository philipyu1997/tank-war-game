package com.game.src.main;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;

/**
 * @author Philip Yu
 */
public class Physics {

    public static boolean checkCollision(EntityC entityC, EntityB entityB) {

        if (entityC.getBounds().intersects(entityB.getBounds())) {
            return true;
        }

        return false;

    }

    public static boolean checkCollision(EntityC entityC, EntityA entityA) {

        if (entityC.getBounds().intersects(entityA.getBounds())) {
            return true;
        }

        return false;

    }

} // end class Physics
