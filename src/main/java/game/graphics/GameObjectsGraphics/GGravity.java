package game.graphics.GameObjectsGraphics;

import game.objects.GameObject;
import game.objects.abstractClass.Gravity;
import game.objects.impl.gravity.RoundGravity;
import game.objects.impl.gravity.SquareGravity;
import game.tools.Constants;
import game.tools.Tools;
import org.omg.PortableServer.POA;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

public class GGravity implements GGameObject {

    private Gravity go;

    BufferedImage sprite;

    private int turn = 0;

    public GGravity(Gravity go) {
        this.go = go;
        if (go.isVisible()) {
            try {
                sprite = ImageIO.read(new File(go.getFullSprite()));
                sprite = Tools.resize(sprite, 30, 30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Gravity getGo() {
        return go;
    }

    @Override
    public void draw(Graphics2D g) {

        double rotationRequired = Math.toRadians(1) * turn;
        double locationX = this.go.getWidth() / 2;
        double locationY = this.go.getLength() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        turn++;
        if (turn == 100) turn = 0;

        if (go.isVisible()) {


            //g.drawImage(sprite, go.getPosX(), go.getPosY(),null);
            g.drawImage(op.filter(sprite, null), go.getPosX(), go.getPosY(), null);


        }

        if (Constants.DEBUG) {
            if (go instanceof RoundGravity) {
                g.setColor(Color.RED);
                RoundRectangle2D r2 = new RoundRectangle2D.Double(go.getPosX(), go.getPosY(),
                        go.getLength(), go.getWidth(),
                        go.getLength(), go.getWidth());
                g.draw(r2);
                g.setColor(Color.BLUE);
                RoundRectangle2D r3 = new RoundRectangle2D.Double(go.getPosX() - go.getRadius() / 2, go.getPosY() - go.getRadius() / 2,
                        go.getLength() + go.getRadius(), go.getWidth() + go.getRadius(),
                        go.getLength() + go.getRadius(), go.getWidth() + go.getRadius());
                g.draw(r3);
            } else if (go instanceof SquareGravity) {
                g.setColor(Color.RED);
                Rectangle2D r1 = new Rectangle2D.Double(go.getPosX() , go.getPosY(), go.getWidth(), go.getLength());
                g.setColor(Color.BLUE);
                Rectangle2D r2 = new Rectangle2D.Double(go.getPosX() - go.getRadius(), go.getPosY() - go.getRadius(),
                        go.getWidth() + go.getRadius()*2, go.getLength() + go.getRadius() *2);
                g.draw(r1);
                g.draw(r2);

            }
        }
    }
}
