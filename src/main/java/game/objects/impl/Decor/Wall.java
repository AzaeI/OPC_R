package game.objects.impl.Decor;

import game.objects.Decor;
import game.objects.GameObject;

public class Wall extends Decor {

    @Override
    public Decor clone() {
        Wall d = new Wall();
        d.setColor(this.getColor());
        d.setWidth(this.getWidth());
        d.setLength(this.getLength());
        d.setMasse(this.getMasse());
        d.setMovable(this.isMovable());
        d.setHp(this.getHp());
        d.setSprite(this.getSprite());
        d.setVector(this.getVector().clone());
        return d;
    }
}
