package game.graphics.GameObjectsGraphics;

import game.objects.GameObject;
import game.tools.Constants;
import game.tools.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GCharacter implements GGameObject {
    private GameObject go;
    private Graphics2D g;

    BufferedImage sprite_1;
    BufferedImage sprite_2;
    BufferedImage sprite_3;

    public GCharacter(GameObject go, Graphics2D g) {
        this.go = go;
        this.g = g;

        try {
            sprite_1 = ImageIO.read(new File(go.getFullSprite()+"_1.png"));
            sprite_2 = ImageIO.read(new File(go.getFullSprite()+"_2.png"));
            sprite_3 = ImageIO.read(new File(go.getFullSprite()+"_3.png"));

            sprite_1 = Tools.resize(sprite_1, go.getWidth(), go.getLength());
            sprite_2 = Tools.resize(sprite_2, go.getWidth(), go.getLength());
            sprite_3 = Tools.resize(sprite_3, go.getWidth(), go.getLength());
        } catch (Exception e) { e.printStackTrace(); }

    }

    public GameObject getGo() {
        return go;
    }

    public Graphics2D getG() {
        return g;
    }

    public void draw(int OFFSET, int FOOTER) {
        g.setColor(go.getColor());
        /*g.fillOval(go.getPosX() + OFFSET,
                go.getPosY()+ FOOTER,
                go.getWidth(),
                go.getLength());*/
        g.drawImage(sprite_1, go.getPosX()+OFFSET, go.getPosY()+FOOTER,null);
    }
}
