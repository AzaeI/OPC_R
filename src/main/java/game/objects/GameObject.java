package game.objects;

import game.objects.abstractClass.Bird;
import game.objects.abstractClass.Decor;
import game.objects.abstractClass.Gravity;
import game.objects.abstractClass.Pig;
import game.physics.Vector;
import game.tools.Constants;

import java.awt.*;

public abstract class GameObject {

	private Color color;
	
	private int posX;
	private int posY;
	
	private int width;
	private int length;
	
	private double masse;

	private Vector vector;

	private int hp;
	private int currentHp;

	private String sprite;

	private GameObjectState state;

	private Point oldPos;

	//FUNCTION

	public abstract void collisionWith(GameObject g);

	public abstract GameObject clone();

	public void move() {
		double x = vector.getDirection().x - vector.getCenter().x;
		double y = vector.getDirection().y - vector.getCenter().y;
		x = (x / (Constants.FORCE_MAX / vector.getForce()));
		y = (y / (Constants.FORCE_MAX / vector.getForce()));

		if (oldPos == null) oldPos = new Point();
		oldPos.x = posX;
		oldPos.y = posY;

		setPosX((int) (posX + x));
		setPosY((int) (posY + y));
		vector.getDirection().x = (int) (vector.getDirection().x + x);
		vector.getDirection().y = (int) (vector.getDirection().y + y);
	}

	public void stop() {
		this.vector.stop();
	}

	public void reboundX() {
		this.vector.reboundX();
	}
	public void reboundY() {
		this.vector.reboundY();
	}

	//GETTER & SETTER

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
		this.vector.getCenter().x = posX + width/2;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
		this.vector.getCenter().y = posY + length/2;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getMasse() {
		return masse;
	}

	public void setMasse(double masse) {
		this.masse = masse;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public String getSprite() {
        return sprite;
    }

    public String getFullSprite() {
	    if (this instanceof Bird) return Constants.BIRD_IMG + "/" + sprite;
	    else if (this instanceof Pig) return Constants.PIGS_IMG + "/" + sprite;
	    else if (this instanceof Decor) return Constants.DECOR_IMG +  "/" + sprite;
	    else if (this instanceof Gravity) return Constants.GRAVITY_IMG + "/" + sprite;
	    else return "";
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

	public GameObjectState getState() {
		return state;
	}

	public void setState(GameObjectState state) {
		this.state = state;
	}

	public Vector getVector() {
		return vector;
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}

	public Point getOldPos() {
		return oldPos;
	}

	public void setOldPos(Point oldPos) {
		this.oldPos = oldPos;
	}
}
