package game.graphics;

import game.objects.GameObject;
import game.tools.Constants;

import java.awt.*;

public class Sphere implements Shape {

    private GameObject go;
    private Graphics2D g;

    public Sphere(GameObject go, Graphics2D g) {
        this.go = go;
        this.g = g;
    }

    public GameObject getGo() {
        return go;
    }

    public Graphics2D getG() {
        return g;
    }


    @Override
    public void draw(int OFFSET, int FOOTER) {
        g.setColor(go.getColor());
        g.fillOval(go.getPosX() + OFFSET,
                go.getPosY()+ FOOTER,
                go.getWidth(),
                go.getLength());
    }
}
