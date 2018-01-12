package game.level;

import java.util.List;

import game.graphics.Panel;
import game.objects.GameObject;

public class Level {
	private double gravity;
	private List<GameObject> objects;
	
	public Level(double gravity, List<GameObject> objects) {
		super();
		this.gravity = gravity;
		this.objects = objects;
	}
	
	
}
