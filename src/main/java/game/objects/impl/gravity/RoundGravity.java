package game.objects.impl.gravity;

import game.objects.GameObject;
import game.objects.abstractClass.Gravity;

public class RoundGravity extends Gravity {
    @Override
    public void collisionWith(GameObject g) {
        if (this.getVector().getCenter().x > g.getVector().getCenter().x) {
            g.getVector().getDirection().x += this.getGravity();
        } else {
            g.getVector().getDirection().x -= this.getGravity();
        }

        if (this.getVector().getCenter().y > g.getVector().getCenter().y) {
            g.getVector().getDirection().y += this.getGravity();
        } else {
            g.getVector().getDirection().y -= this.getGravity();
        }
    }

    @Override
    public GameObject clone() {
        Gravity d = new RoundGravity();
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
