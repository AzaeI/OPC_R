package game.graphics.GameObjectsGraphics;

import game.objects.GameObject;

import java.awt.*;

public class GDecor implements GGameObject {
    private GameObject go;
    private Graphics2D g;

    public GDecor(GameObject go, Graphics2D g) {
        this.go = go;
        this.g = g;
    }

    public GameObject getGo() {
        return go;
    }

    public Graphics2D getG() {
        return g;
    }

    public void draw(int OFFSET, int FOOTER) {
        g.setColor(go.getColor());
        g.fillRect(go.getPosX()+ OFFSET,
                go.getPosY()+ FOOTER,
                go.getWidth(),
                go.getLength());
    }
}
