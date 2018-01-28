package game.objects.abstractClass;

import game.objects.GameObject;

public abstract class Gravity extends GameObject {

    private int radius;
    private double gravity;
    private boolean isVisible;



    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
