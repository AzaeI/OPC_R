package game.objects;

import game.objects.impl.Decor.Grass;

public abstract class Decor extends GameObject {

	private boolean isMovable;

	public boolean isMovable() {
		return isMovable;
	}

	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

	public void collisionWith(GameObject g) {
		/*if (g instanceof Grass) {
			g.setVelocityY(0);
		}
		else if (!this.isMovable) {
			g.setVelocityX(0);
			g.setVelocityY(0);
		}

		if (g instanceof Decor && this.isMovable) {
			g.setVelocityX(this.getVelocityX());
			g.setVelocityY(this.getVelocityY());
		} else if (g instanceof Bird) {

		}*/

	}

}
