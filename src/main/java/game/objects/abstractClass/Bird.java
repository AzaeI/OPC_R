package game.objects.abstractClass;

import game.objects.GameObject;
import game.physics.Vector;
import game.tools.Constants;

import java.awt.*;

public abstract class Bird extends Character {

    private double speed;
    private int order;
    private boolean isSelected;

    public void collisionWith(GameObject g) {
        if (g instanceof Decor && ((Decor) g).isMovable()) {

            if (this.getOldPos().x + this.getWidth() > g.getPosX() &&
                    this.getOldPos().x < g.getPosX() + g.getWidth()) {
                if (this.getVector().getCenter().y > g.getPosY() + g.getLength()) {
                    System.out.println("TOP");
                } else {
                    System.out.println("BOTTOM");
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
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
