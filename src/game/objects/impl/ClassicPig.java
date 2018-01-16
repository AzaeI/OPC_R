package game.objects.impl;

import game.objects.Character;
import game.objects.Pig;

public class ClassicPig extends Pig {

	@Override
	public Character clone() {
		ClassicPig p = new ClassicPig();
		p.setColor(this.getColor());
		p.setWidth(this.getWidth());
		p.setLength(this.getLength());
		p.setMasse(this.getMasse());
		p.setName(this.getName());
		return p;
	}

}
