package game.objects.impl.Pig;

import game.objects.GameObject;
import game.objects.Pig;

public class HelmetPig extends Pig {
    @Override
    public GameObject clone() {
        HelmetPig p = new HelmetPig();
        p.setColor(this.getColor());
        p.setWidth(this.getWidth());
        p.setLength(this.getLength());
        p.setMasse(this.getMasse());
        p.setName(this.getName());
        p.setHp(this.getHp());
        p.setSprite(this.getSprite());
        p.setVector(this.getVector().clone());
        return p;
    }
}
