package com.game.src.main;

import window.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Philip Yu
 */
public class KeyInput extends KeyAdapter {

    private Game game;

    public KeyInput(Game game) {

        this.game = game;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        game.keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {

        game.keyReleased(e);

    }

} // end class KeyInput
