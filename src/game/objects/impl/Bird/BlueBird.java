package game.objects.impl.Bird;

import game.objects.Bird;
import game.objects.GameObject;

public class BlueBird extends Bird {
    @Override
    public GameObject clone() {
        BlueBird b = new BlueBird();
        b.setColor(this.getColor());
        b.setWidth(this.getWidth());
        b.setLength(this.getLength());
        b.setMasse(this.getMasse());
        b.setName(this.getName());
        b.setSpeed(this.getSpeed());
        b.setHp(this.getHp());
        b.setSprite(this.getSprite());
        return b;
    }
}
