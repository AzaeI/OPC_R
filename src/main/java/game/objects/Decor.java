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
		//THIS IS NOT MOVABLE
		if (!this.isMovable) {
			if (g instanceof Decor) {
				g.stop();
			}
			else if (g instanceof Character) {
				if (g.getVector().getCenter().x + g.getWidth()/2 > this.getPosX())
					g.reboundY();
				else
					g.reboundX();
			}
		}
		// THIS IS MOVABLE
		else {

		}
 	}

}
