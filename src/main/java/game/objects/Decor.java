package game.objects;

public abstract class Decor extends GameObject {

	private boolean isMovable;

	public boolean isMovable() {
		return isMovable;
	}

	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

}
