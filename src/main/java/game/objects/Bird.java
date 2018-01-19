package game.objects;

public abstract class Bird extends Character {
		
	private double speed;
	private int order;
	private boolean isSelected;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean selected) {
		isSelected = selected;
	}

	public void collisionWith(GameObject g) {
	}
}
