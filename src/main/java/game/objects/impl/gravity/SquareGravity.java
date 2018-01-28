package game.objects.impl.gravity;

import game.objects.GameObject;
import game.objects.abstractClass.Gravity;
import game.tools.Constants;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class SquareGravity extends Gravity {

    @Override
    public void collisionWith(GameObject g) {
        Rectangle2D r1 = new Rectangle2D.Double(g.getPosX() ,
                this.getPosY(),
                this.getWidth(),
                this.getLength());

        RoundRectangle2D r2 = new RoundRectangle2D.Double(g.getPosX(), g.getPosY(),
                g.getLength(), g.getWidth(),
                g.getLength(), g.getWidth());
        if (!r2.intersects(r1)){
            //Gravity have horizontal shape
            if (this.getWidth() > g.getLength()) {
                //g below gravity gameobject
                if (this.getVector().getCenter().y > g.getVector().getCenter().y) {
                    g.getVector().getDirection().y += this.getGravity();
                }
                 else {
                    g.getVector().getDirection().y -= this.getGravity();
                }
            } else {
                //g on right of gameobject
                if (this.getVector().getCenter().x > g.getVector().getCenter().x) {
                    g.getVector().getDirection().x += this.getGravity();
                }
                else {
                    g.getVector().getDirection().x -= this.getGravity();
                }
            }
        } else {
            if (g.getVector().getForce() < Constants.FORCE_MIN) {
                g.getVector().getDirection().y = g.getVector().getCenter().y;
            }
        }
    }

    @Override
    public Gravity clone() {
        Gravity d = new SquareGravity();
        d.setWidth(this.getWidth());
        d.setLength(this.getLength());
        d.setVisible(this.isVisible());
        d.setSprite(this.getSprite());
        d.setVector(this.getVector().clone());
        d.setRadius(this.getRadius());
        d.setGravity(this.getGravity());
        d.setPosX(this.getPosX());
        d.setPosY(this.getPosY());
        return d;
    }
}
