package game.graphics.GameObjectsGraphics;

import game.objects.GameObject;
import game.tools.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GDecor implements GGameObject {
    private GameObject go;

    BufferedImage sprite;

    public GDecor(GameObject go) {
        this.go = go;
        try {
            sprite = ImageIO.read(new File(go.getFullSprite()));
            sprite = Tools.resize(sprite, 30, 30);
        }catch (Exception e) { e.printStackTrace(); }
    }

    public GameObject getGo() {
        return go;
    }

    public void draw(int OFFSET, int FOOTER, Graphics2D g) {
        int iw = sprite.getWidth();
        int ih = sprite.getHeight();
        if (iw > 0 && ih > 0) {
            for (int x = go.getPosX() + OFFSET; x < go.getWidth() + go.getPosX() + OFFSET; x += iw) {
                for (int y = go.getPosY() + FOOTER; y < go.getLength()+go.getPosY() + FOOTER; y += ih) {
                    g.drawImage(sprite, x, y, iw, ih, null);
                }
            }
        }
    }
}
