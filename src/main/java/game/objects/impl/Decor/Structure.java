package game.objects.impl.Decor;

import game.objects.Decor;

public class Structure extends Decor {

    @Override
    public Decor clone() {
        Structure d = new Structure();
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
