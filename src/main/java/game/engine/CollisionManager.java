package game.engine;

import game.objects.Character;
import game.objects.Decor;
import game.objects.GameObject;
import game.tools.Tools;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class CollisionManager {

    List<GameObject> objects;

    public CollisionManager(List<GameObject> objects) {
        this.objects = objects;
    }

    public void checkCollision() {
        for (GameObject g : objects) {
            checkCollision(g);
        }
    }

    public void checkCollision(GameObject g) {
        for (GameObject go : objects) {
            if (g != go) {
                //CHECK CHAR TO CHAR
                if (g instanceof Character && go instanceof Character) {
                    double distance = Tools.distance(g.getPosX() + g.getWidth() / 2,
                            g.getPosY() + g.getWidth()/2,
                            go.getPosX() + go.getWidth()/2,
                            go.getPosY() + go.getWidth()/2);

                    if (g != go && distance < g.getWidth() / 2 + go.getWidth() / 2) {
                        g.collisionWith(go);
                    }
                }
                //DECOR TO CHAR
                else if (g instanceof Decor && go instanceof Character) {
                    Rectangle2D r1 = new Rectangle2D.Double(g.getPosX(), g.getPosY(), g.getWidth(), g.getLength());

                    RoundRectangle2D r2 = new RoundRectangle2D.Double(go.getPosX(), go.getPosY(),
                            go.getLength(), go.getWidth(),
                            go.getLength(), go.getWidth());
                    if (r2.intersects(r1)){
                        g.collisionWith(go);
                    }
                }
                // CHAR TO DECOR
                else if (g instanceof Character && go instanceof Decor) {
                    Rectangle2D r1 = new Rectangle2D.Double(go.getPosX(), go.getPosY(), go.getWidth(), go.getLength());

                    RoundRectangle2D r2 = new RoundRectangle2D.Double(g.getPosX(), g.getPosY(),
                            g.getLength(), g.getWidth(),
                            g.getLength(), g.getWidth());
                    if (r2.intersects(r1)){
                        g.collisionWith(go);
                    }
                }
                //DECOR TO DECOR
                else if (g instanceof Decor && go instanceof Decor) {

                    Rectangle2D r1 = new Rectangle2D.Double(g.getPosX(), g.getPosY(), g.getWidth(), g.getLength());
                    Rectangle2D r2 = new Rectangle2D.Double(go.getPosX(), go.getPosY(), go.getWidth(), go.getLength());
                    if (r1.intersects(r2)) {
                        g.collisionWith(go);
                    }
                }
            }
        }
    }
}
