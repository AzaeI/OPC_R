package game.level;

import java.util.ArrayList;
import java.util.List;

import game.objects.GameObject;
import game.objects.Gravity;

public class Level {
	private String name;
	private Gravity gravity;
	private List<GameObject> objects;
	
	public Level(String name, double gravity, List<GameObject> objects) {
		super();
		this.name = name;
		this.gravity = new Gravity(gravity);
		this.objects = objects;
	}

	public String getName() {
		return name;
	}

	public Gravity getGravity() {
		return gravity;
	}

	public List<GameObject> getObjects() {
		return objects;
	}
	
	public Level clone() {
		ArrayList<GameObject> objs = new ArrayList<>();
		for (GameObject gameObject : objects) {
			objs.add(gameObject.clone());
		}
		Level l = new Level(this.name, this.gravity.getEmplitude_gravity(), objs);
		return l;
	}
	
}
