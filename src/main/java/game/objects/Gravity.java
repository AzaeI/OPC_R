package game.objects;

import game.tools.Tools;

import java.awt.*;

public class Gravity  extends GameObject {

    private double emplitude_gravity;

    public Gravity(double emplitude_gravity) {
        this.emplitude_gravity = emplitude_gravity;
    }

    public double getEmplitude_gravity() {
        return emplitude_gravity;
    }

    public void agis_sur_GameObject(GameObject g) {
        double force = g.getVector().getForce();
        Point tmp = new Point(g.getVector().getDirection().x, (int) (g.getVector().getDirection().y - emplitude_gravity));

        g.getVector().getCenter().y -= emplitude_gravity;
        g.getVector().getDirection().y -= emplitude_gravity;
    }

    @Override
    public void collisionWith(GameObject g) {

    }

    @Override
    public GameObject clone() {
        return null;
    }
}
