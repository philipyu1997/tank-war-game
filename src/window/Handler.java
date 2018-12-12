package window;

import framework.GameObject;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Handler {

    public List<GameObject> objectList = new LinkedList<>();
    private GameObject gameObject;
    private Game game;

    public Handler(Game game) {

        this.game = game;

    }

    public void tick() {

        for (int i = 0; i < objectList.size(); ++i) {
            gameObject = objectList.get(i);
            gameObject.tick(objectList);
        }

    }

    public void render(Graphics g) {

        for (int i = 0; i < objectList.size(); ++i) {
            gameObject = objectList.get(i);
            gameObject.render(g);
        }

    }

    public void addObject(GameObject gameObject) {

        objectList.add(gameObject);

    }

    public void removeObject(GameObject gameObject) {

        objectList.remove(gameObject);

    }

} // end class Handler
