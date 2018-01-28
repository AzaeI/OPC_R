package game.level;

import java.util.ArrayList;
import java.util.List;

import game.objects.GameObject;
import game.objects.impl.gravity.SquareGravity;

public class Level {
	private String name;
	private List<GameObject> objects;
	
	public Level(String name, List<GameObject> objects) {
		super();
		this.name = name;
		this.objects = objects;
	}

	public String getName() {
		return name;
	}

	public List<GameObject> getObjects() {
		return objects;
	}
	
	public Level clone() {
		ArrayList<GameObject> objs = new ArrayList();
		for (GameObject gameObject : objects) {
			objs.add(gameObject.clone());
		}
		Level l = new Level(this.name, objs);
		return l;
	}
	
}
