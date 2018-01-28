package game.objects.impl.Pig;

import game.objects.abstractClass.Character;
import game.objects.abstractClass.Pig;

public class ClassicPig extends Pig {

	@Override
	public Character clone() {
		ClassicPig p = new ClassicPig();
		p.setColor(this.getColor());
		p.setWidth(this.getWidth());
		p.setLength(this.getLength());
		p.setMasse(this.getMasse());
		p.setName(this.getName());
		p.setHp(this.getHp());
		p.setSprite(this.getSprite());
		p.setVector(this.getVector().clone());
		p.setPosX(this.getPosX());
		p.setPosY(this.getPosY());
		return p;
	}

}
