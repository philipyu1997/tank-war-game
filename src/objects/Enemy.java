//package objects;
//
//import window.Handler;
//import framework.Texture;
//import framework.EntityB;
//import framework.GameObject;
//import window.Game;
//
//import java.awt.*;
//import java.util.List;
//
///**
// * @author Philip Yu
// */
//public class Enemy extends GameObject implements EntityB {
//
//    private Texture tex;
//    private Game game;
//    private Handler c;
//
//    private int health;
//
//    public Enemy(int x, int y, Texture tex, Game game, Handler c, int health) {
//
//        super(x, y);
//        this.tex = tex;
//        this.game = game;
//        this.c = c;
//        this.health = health;
//
//    }
//
//    @Override
//    public void tick(List<GameObject> object) {
//
//        x += velX;
//        y += velY;
//
//        checkBorders();
//        checkCollision();
//
//    }
//
//    private void checkCollision() {
//
////        for (int i = 0; i < game.entityListC.size(); ++i) {
////            EntityC entityC = game.entityListC.get(i);
////
////            if (Physics.checkCollision(entityC, this)) {
////                c.removeEntity(entityC);
////                health -= Bullet.getShellDamage();
////
////                if (health == 0)
////                    c.removeEntity(this);
////
////            }
////        }
//
//    }
//
//    private void checkBorders() {
//
//        // LEFT
//        if (x <= 0)
//            x = 0;
//
//        // RIGHT
//        if (x >= 640 - tex.enemy.getWidth())
//            x = 640 - tex.enemy.getWidth();
//
//        // TOP
//        if (y < 0)
//            y = 0;
//
//        // BOTTOM
//        if (y >= 480 - tex.enemy.getHeight() - 16)
//            y = 480 - tex.enemy.getHeight() - 16;
//
//    }
//
//    @Override
//    public void render(Graphics g) {
//
//        g.drawImage(tex.enemy, x, y, null);
//
//    }
//
//    @Override
//    public Rectangle getBounds() {
//
//        return (new Rectangle(x, y, 64, 64));
//
//    }
//
//    public int getHealth() {
//
//        return health;
//
//    }
//
//    public void setHealth(int health) {
//
//        this.health = health;
//
//    }
//
//} // end class Player
