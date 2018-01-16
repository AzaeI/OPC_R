package game.level;

import java.util.ArrayList;
import java.util.List;

import game.graphics.Panel;
import game.objects.GameObject;

public class Level {
	private String name;
	private double gravity;
	private List<GameObject> objects;
	
	public Level(String name, double gravity, List<GameObject> objects) {
		super();
		this.name = name;
		this.gravity = gravity;
		this.objects = objects;
	}

	public String getName() {
		return name;
	}

	public double getGravity() {
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
		Level l = new Level(this.name, this.gravity, objs);
		return l;
	}
	
}
