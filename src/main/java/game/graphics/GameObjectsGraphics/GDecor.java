package game.graphics.GameObjectsGraphics;

import game.objects.GameObject;
import game.objects.abstractClass.Decor;
import game.tools.Constants;
import game.tools.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class GDecor implements GGameObject {
    private Decor go;

    BufferedImage sprite;

    public GDecor(Decor go) {
        this.go = go;
        try {
            sprite = ImageIO.read(new File(go.getFullSprite()));
            sprite = Tools.resize(sprite, 30, 30);
        }catch (Exception e) { e.printStackTrace(); }
    }

    public Decor getGo() {
        return go;
    }

    public void draw(Graphics2D g) {

        int iw = sprite.getWidth();
        int ih = sprite.getHeight();
        if (iw > 0 && ih > 0) {
            for (int x = go.getPosX(); x < go.getWidth() + go.getPosX(); x += iw) {
                for (int y = go.getPosY(); y < go.getLength()+go.getPosY(); y += ih) {
                    g.drawImage(sprite, x, y, iw, ih, null);
                }
            }
        }

        if (Constants.DEBUG) {
            g.setColor(Color.RED);
            Rectangle2D r2 = new Rectangle2D.Double(go.getPosX(), go.getPosY(), go.getWidth(), go.getLength());
            g.draw(r2);
        }
    }
}
