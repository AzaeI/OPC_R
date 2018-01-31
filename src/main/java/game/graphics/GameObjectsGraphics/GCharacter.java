package game.graphics.GameObjectsGraphics;

import game.objects.abstractClass.Character;
import game.objects.GameObject;
import game.objects.abstractClass.Pig;
import game.tools.Constants;
import game.tools.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class GCharacter implements GGameObject {
    private Character go;
    private int timeToDisappear;

    BufferedImage sprite_1;
    BufferedImage sprite_2;
    BufferedImage sprite_3;
    BufferedImage tomb;
    BufferedImage tombbird;


    public GCharacter(Character go, int timeToDisappear) {
        this.go = go;
        this.timeToDisappear = timeToDisappear;

        try {
            sprite_1 = ImageIO.read(new File(go.getFullSprite()+"_1.png"));
            sprite_2 = ImageIO.read(new File(go.getFullSprite()+"_2.png"));
            sprite_3 = ImageIO.read(new File(go.getFullSprite()+"_3.png"));
            tomb     = ImageIO.read(new File(Constants.TOMB_FILE));
            tombbird = ImageIO.read(new File(Constants.TOMB_BIRD_FILE));

            sprite_1 = Tools.resize(sprite_1, go.getWidth(), go.getLength());
            sprite_2 = Tools.resize(sprite_2, go.getWidth(), go.getLength());
            sprite_3 = Tools.resize(sprite_3, go.getWidth(), go.getLength());
            tomb     = Tools.resize(tomb, go.getWidth(), go.getLength());
            tombbird = Tools.resize(tombbird, go.getWidth(), go.getLength());
        } catch (Exception e) { e.printStackTrace(); }

    }

    public Character getGo() {
        return go;
    }

    public int getTimeToDisappear() {
        return timeToDisappear;
    }

    public void setTimeToDisappear(int timeToDisappear) {
        this.timeToDisappear = timeToDisappear;
    }

    public void decreaseTimerDisappear() {
        this.timeToDisappear -= Constants.DEACREASE_TIMER_DESAPPEAR_GCHARACTER;
    }

    public void draw(Graphics2D g) {
        g.setColor(go.getColor());
        Character c = go;
        switch (c.getState()) {
            case IDLE:
                g.drawImage(sprite_1, go.getPosX(), go.getPosY(),null);
                break;
            case FLYING :
                g.drawImage(sprite_2, go.getPosX(), go.getPosY(),null);
                break;
            case DEAD:
                g.drawImage(sprite_3, go.getPosX(), go.getPosY(),null);
                break;
            case DAMAGED_1:
                g.drawImage(sprite_2, go.getPosX(), go.getPosY(),null);
                break;
            case TOMB:
                g.drawImage(tomb, go.getPosX(), go.getPosY(),null);
                break;
            case TOMB_BIRD:
                g.drawImage(tombbird, go.getPosX(), go.getPosY(),null);
                break;
        }
        if (Constants.DEBUG) {
            RoundRectangle2D r2 = new RoundRectangle2D.Double(go.getPosX(), go.getPosY(),
                    go.getLength(), go.getWidth(),
                    go.getLength(), go.getWidth());
            g.draw(r2);

            g.setColor(Color.GREEN);
            g.drawLine(go.getVector().getCenter().x,go.getVector().getCenter().y, go.getVector().getDirection().x,  go.getVector().getDirection().y);
        }
    }
}
