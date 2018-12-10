package window;

import framework.EntityA;
import framework.EntityB;
import framework.EntityC;

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
