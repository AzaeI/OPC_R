package game.physics;

import game.tools.Constants;
import game.tools.Tools;

import java.awt.*;

public class Vector {

    private Point center;
    private Point direction;

    public Vector() {
        this.center = new Point(0, 0);
        this.direction = new Point(0, 0);
    }

    public Vector(Point center, Point direction) {
        this.center = center;
        this.direction = direction;
    }

    public Vector(int x1, int y1, int x2, int y2) {
        this.center = new Point(x1, y1);
        this.direction = new Point(x2, y2);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
    }

    public void init() {
        this.direction.x = center.x;
        this.direction.y = center.y;
    }

    public double getForce() {
        return center.distance(direction);
    }

    public Vector clone() {
        Vector v = new Vector(center.x, center.y, direction.x, direction.y);
        return v;
    }

    public void addVector(Vector v) {
        this.direction.x += v.getDirection().x - v.getCenter().x;
        this.direction.y += v.getDirection().y - v.getCenter().y;
    }

    public void stop() {
        this.direction.x = center.x;
        this.direction.y = center.y;
    }

    public void reboundX() {
        int tmp = this.direction.x - this.center.x;
        this.direction.x = this.center.x - tmp;
    }

    public void reboundY() {
        int tmp = this.direction.y - this.center.y;
        this.direction.y = this.center.y - tmp;
    }
}
