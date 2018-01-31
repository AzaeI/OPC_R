package game.objects.abstractClass;

import game.objects.GameObject;
import game.objects.GameObjectState;
import game.objects.impl.Decor.Grass;
import game.objects.impl.Decor.Ground;
import game.objects.impl.Decor.Structure;
import game.objects.impl.Decor.Wall;
import game.physics.Vector;
import game.tools.Constants;

import java.awt.*;

public abstract class Bird extends Character {

    private double speed;
    private int order;
    private boolean isSelected;
    private int timeToLive;

    public void collisionWith(GameObject g) {

        if (g instanceof Pig) {

            int hp = g.getCurrentHp();

            //REBOND ON PIG
            if (this.getVector().getForce() < 100) {
                g.setCurrentHp(hp - 2);
            } else if (this.getVector().getForce() < 120) {
                g.setCurrentHp(hp - 3);
            }

            //TO FAST TO REBOUN, PASS THROUGH PIG
            else if (this.getVector().getForce() <= 140) {
                g.setCurrentHp(hp - 4);
            } else if (this.getVector().getForce() >= 141) {
                g.setCurrentHp(hp - 5);
            }

            if (g.getCurrentHp() <= 0) {
                g.setState(GameObjectState.DEAD);
            } else if (g.getHp() == g.getHp()) {
                g.setState(GameObjectState.IDLE);
            } else {
                g.setState(GameObjectState.DAMAGED_1);
            }
        } else if (g instanceof Character || (g instanceof Decor && ((Decor) g).isMovable())) {
            Vector v = new Vector();
            v.setCenter(new Point());
            int x = this.getVector().getDirection().x - this.getVector().getCenter().x;
            int y = 0;
            v.setDirection(new Point(x, y));
            g.getVector().addVector(v);
            g.getVector().applyFriction();
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

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void reduceTTL() {
        this.timeToLive -= Constants.DEACREASE_TIMER_DESAPPEAR_BIRD;
        if (timeToLive <= 0) this.setState(GameObjectState.DEAD);
    }

    public boolean isAlive() {
        if (timeToLive >= 0 || getCurrentHp() >= 0) return true;
        return false;
    }
}
