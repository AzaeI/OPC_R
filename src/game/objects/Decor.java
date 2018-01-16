package game.objects;

public class Decor extends GameObject {

	private boolean isMovable;

	public boolean isMovable() {
		return isMovable;
	}

	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

	@Override
	public Decor clone() {
		Decor d = new Decor();
		d.setColor(this.getColor());
		d.setWidth(this.getWidth());
		d.setLength(this.getLength());
		d.setMasse(this.getMasse());
		d.setMovable(this.isMovable());
		return d;
	}
	
	
}
