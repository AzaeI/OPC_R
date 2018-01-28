package game.objects.impl.Bird;

import game.objects.abstractClass.Bird;
import game.objects.GameObject;

public class ClassicBird extends Bird {
	@Override
	public GameObject clone() {
		ClassicBird b = new ClassicBird();
		b.setColor(this.getColor());
		b.setWidth(this.getWidth());
		b.setLength(this.getLength());
		b.setMasse(this.getMasse());
		b.setName(this.getName());
		b.setSpeed(this.getSpeed());
		b.setHp(this.getHp());
		b.setOrder(this.getOrder());
		b.setSprite(this.getSprite());
		b.setVector(this.getVector().clone());
		return b;
	}

}
