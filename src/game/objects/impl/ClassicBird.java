package game.objects.impl;

import game.objects.Bird;
import game.objects.Character;

public class ClassicBird extends Bird {

	@Override
	public Character clone() {
		ClassicBird b = new ClassicBird();
		b.setColor(this.getColor());
		b.setWidth(this.getWidth());
		b.setLength(this.getLength());
		b.setMasse(this.getMasse());
		b.setName(this.getName());
		b.setSpeed(this.getSpeed());
		return b;
	}

}
