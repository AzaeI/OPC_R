package game.objects.impl.Decor;

import game.objects.abstractClass.Decor;

public class Ground extends Decor {
    @Override
    public Decor clone() {
        Ground d = new Ground();
        d.setColor(this.getColor());
        d.setWidth(this.getWidth());
        d.setLength(this.getLength());
        d.setMasse(this.getMasse());
        d.setMovable(this.isMovable());
        d.setHp(this.getHp());
        d.setSprite(this.getSprite());
        d.setVector(this.getVector().clone());
        d.setPosX(this.getPosX());
        d.setPosY(this.getPosY());
        return d;
    }
}
