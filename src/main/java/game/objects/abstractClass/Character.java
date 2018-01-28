package game.objects.abstractClass;

import game.objects.GameObject;

public abstract class Character extends GameObject {

	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
