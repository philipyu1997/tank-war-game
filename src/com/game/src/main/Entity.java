package com.game.src.main;

import java.awt.*;

/**
 * @author Philip Yu
 */
public interface Entity {

    void tick();

    void render(Graphics g);

    double getX();

    double getY();

} // end interface Entity
