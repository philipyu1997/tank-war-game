package framework;

import java.awt.*;
import java.util.List;

/**
 * @author Philip Yu
 */
public interface EntityC {

    void tick(List<GameObject> object);

    void render(Graphics g);

    Rectangle getBounds();

    int getX();

    int getY();

} // end interface EntityC
