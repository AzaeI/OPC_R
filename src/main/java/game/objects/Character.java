package game.objects;

public abstract class Character extends GameObject  {

	private String name;

	private GameObjectState state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GameObjectState getState() {
		return state;
	}

	public void setState(GameObjectState state) {
		this.state = state;
	}

}
