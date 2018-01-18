package game.objects;

import game.tools.Constants;

import java.awt.Color;

public abstract class GameObject {

	private Color color;
	
	private int posX;
	private int posY;
	
	private int width;
	private int length;
	
	private double masse;

	private double velocityX;
	private double velocityY;

	private int hp;

	private String sprite;


	public abstract GameObject clone();

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
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
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

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

    public String getSprite() {
        return sprite;
    }

    public String getFullSprite() {
	    if (this instanceof Bird) return Constants.BIRD_IMG + "/" + sprite;
	    else if (this instanceof Pig) return Constants.PIGS_IMG + "/" + sprite;
	    else if (this instanceof Decor) return Constants.DECOR_IMG +  "/" + sprite;
	    else return "";
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
