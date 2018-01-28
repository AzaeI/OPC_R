package game.graphics.GameObjectsGraphics;

import game.objects.abstractClass.Character;
import game.objects.GameObject;
import game.tools.Constants;
import game.tools.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class GCharacter implements GGameObject {
    private Character go;

    BufferedImage sprite_1;
    BufferedImage sprite_2;
    BufferedImage sprite_3;

    public GCharacter(Character go) {
        this.go = go;

        try {
            sprite_1 = ImageIO.read(new File(go.getFullSprite()+"_1.png"));
            sprite_2 = ImageIO.read(new File(go.getFullSprite()+"_2.png"));
            sprite_3 = ImageIO.read(new File(go.getFullSprite()+"_3.png"));

            sprite_1 = Tools.resize(sprite_1, go.getWidth(), go.getLength());
            sprite_2 = Tools.resize(sprite_2, go.getWidth(), go.getLength());
            sprite_3 = Tools.resize(sprite_3, go.getWidth(), go.getLength());
        } catch (Exception e) { e.printStackTrace(); }

    }

    public Character getGo() {
        return go;
    }

    public void draw(Graphics2D g) {
        g.setColor(go.getColor());
        Character c = go;
        switch (c.getState()) {
        case IDLE:
            g.drawImage(sprite_1, go.getPosX(), go.getPosY(),null);
            break;
        case FLYING:
            g.drawImage(sprite_2, go.getPosX(), go.getPosY(),null);
            break;
        case DEAD:
            g.drawImage(sprite_3, go.getPosX(), go.getPosY(),null);
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
