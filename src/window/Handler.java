package window;

import framework.GameObject;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Handler {

//    private LinkedList<EntityA> entityListA = new LinkedList<>();
//    private LinkedList<EntityB> entityListB = new LinkedList<>();
//    private LinkedList<EntityC> entityListC = new LinkedList<>();
//
//    private EntityA entityA;
//    private EntityB entityB;
//    private EntityC entityC;

    public List<GameObject> objectList = new LinkedList<>();

    private GameObject gameObject;

    private Game game;

    private int GAME_WIDTH = Game.getWindowWidth();
    private int GAME_HEIGHT = Game.getWindowHeight();

    public Handler(Game game) {

        this.game = game;

    }

    public void tick() {

//        // CLASS A
//        for (int i = 0; i < entityListA.size(); ++i) {
//            entityA = entityListA.get(i);
//
//            if (entityA.getY() < 0) {
//                removeEntity(entityA);
//            }
//
//            entityA.tick();
//        }
//
//        // CLASS B
//        for (int i = 0; i < entityListB.size(); ++i) {
//            entityB = entityListB.get(i);
//
//            if (entityB.getY() < 0) {
//                removeEntity(entityB);
//            }
//
//            entityB.tick();
//        }
//
//        // CLASS C
//        for (int i = 0; i < entityListC.size(); ++i) {
//            entityC = entityListC.get(i);
//
//            if (entityC.getY() < 0) {
//                removeEntity(entityC);
//            }
//
//            entityC.tick();
//        }

        for (int i = 0; i < objectList.size(); ++i) {
            gameObject = objectList.get(i);
            gameObject.tick(objectList);
        }

    }

    public void render(Graphics g) {

//        // CLASS A
//        for (int i = 0; i < entityListA.size(); ++i) {
//            entityA = entityListA.get(i);
//            entityA.render(g);
//        }
//
//        // CLASS B
//        for (int i = 0; i < entityListB.size(); ++i) {
//            entityB = entityListB.get(i);
//            entityB.render(g);
//        }
//
//        // CLASS C
//        for (int i = 0; i < entityListC.size(); ++i) {
//            entityC = entityListC.get(i);
//            entityC.render(g);
//        }

        for (int i = 0; i < objectList.size(); ++i) {
            gameObject = objectList.get(i);
            gameObject.render(g);
        }

    }

//    public void addEntity(EntityA block) {
//
//        entityListA.add(block);
//
//    }
//
//    public void removeEntity(EntityA block) {
//
//        entityListA.remove(block);
//
//    }
//
//    public void addEntity(EntityB block) {
//
//        entityListB.add(block);
//
//    }
//
//    public void removeEntity(EntityB block) {
//
//        entityListB.remove(block);
//
//    }
//
//    public void addEntity(EntityC block) {
//
//        entityListC.add(block);
//
//    }
//
//    public void removeEntity(EntityC block) {
//
//        entityListC.remove(block);
//
//    }
//
//    public LinkedList<EntityA> getEntityA() {
//
//        return entityListA;
//
//    }
//
//    public LinkedList<EntityB> getEntityB() {
//
//        return entityListB;
//
//    }
//
//    public LinkedList<EntityC> getEntityC() {
//
//        return entityListC;
//
//    }

    public void addObject(GameObject gameObject) {

        objectList.add(gameObject);

    }

    public void removeObject(GameObject gameObject) {

        objectList.remove(gameObject);

    }

} // end class Handler
