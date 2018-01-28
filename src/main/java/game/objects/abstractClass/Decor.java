package game.objects.abstractClass;

import game.objects.GameObject;
import game.physics.Vector;
import game.tools.Constants;

import java.awt.*;


public abstract class Decor extends GameObject {

    private boolean isMovable;

    public boolean isMovable() {
        return isMovable;
    }

    public void setMovable(boolean isMovable) {
        this.isMovable = isMovable;
    }

    public void collisionWith(GameObject g) {
        if (g instanceof Decor) {
            if (!this.isMovable)
                g.stop();
            else {
                if (g.getOldPos().x + g.getWidth() > this.getPosX() &&
                        g.getOldPos().x < this.getPosX() + this.getWidth()) {
                    if (this.getVector().getCenter().y > g.getPosY() + g.getLength()) {

                    } else {

                    }
                } else {
                    if (this.getVector().getCenter().x > g.getPosX() + g.getWidth()) {
                        Vector v = new Vector();
                        v.setCenter((Point) g.getVector().getCenter().clone());
                        v.setDirection(new Point((int) (g.getVector().getCenter().x - this.getVector().getForce()/ Constants.COLLISION_REDUCTOR), g.getVector().getCenter().y));
                        g.getVector().addVector(v);
                    } else {
                        Vector v = new Vector();
                        v.setCenter((Point) g.getVector().getCenter().clone());
                        v.setDirection(new Point((int) (g.getVector().getCenter().x + this.getVector().getForce()/ Constants.COLLISION_REDUCTOR), g.getVector().getCenter().y));
                        g.getVector().addVector(v);
                    }
                }
            }
        } else if (g instanceof Character) {
            if (g.getOldPos().x + g.getWidth() > this.getPosX() &&
                    g.getOldPos().x < this.getPosX() + this.getWidth()) {
                g.reboundY();
            } else {
                g.reboundX();
            }
        }
    }
}

