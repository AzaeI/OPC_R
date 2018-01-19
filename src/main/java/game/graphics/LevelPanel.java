package game.graphics;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import game.Loader;
import game.engine.CollisionManager;
import game.graphics.GameObjectsGraphics.GCharacter;
import game.graphics.GameObjectsGraphics.GDecor;
import game.level.Level;
import game.objects.*;
import game.tools.Constants;
import game.tools.Tools;
import sun.misc.GC;

import javax.imageio.ImageIO;

public class LevelPanel extends Panel implements MouseMotionListener, MouseListener, Runnable {

    private final int FOOTER = 60;
    private final int OFFSET = 200;
    private final int PLATEFORM_WIDTH = 150;
    private final int PLATEFORM_HEIGHT = 180;

    private final int SLINGSHOT_WIDTH = 10;
    private final int SLINGSHOT_HEIGHT = 75;

    private final int SLINGSHOT_OFFSET = 50;

    private final Point SLINGSHOT_CENTER = new Point(
            PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2,
            PLATEFORM_HEIGHT + SLINGSHOT_HEIGHT + FOOTER + SLINGSHOT_OFFSET / 2);

    private ArrayList<Decor> decors;
    private ArrayList<Bird> birds;
    private ArrayList<Pig> pigs;

    private ArrayList<GDecor> gDecors;
    private ArrayList<GCharacter> gBirds;
    private ArrayList<GCharacter> gPigs;

    private BufferedImage backG;
    {
        try {
            backG = ImageIO.read(new File(Constants.BG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int timerRelaodBird = 1000; //1sec

    private CollisionManager collisionManager;

    private Bird currentBird;
    private Level level;

    private boolean initPanel = true;

    public LevelPanel(Level level) {
        super();
        this.level = level;

        decors = new ArrayList();
        birds = new ArrayList();
        pigs = new ArrayList();

        gDecors= new ArrayList();
        gBirds = new ArrayList();
        gPigs = new ArrayList();

        init();
        currentBird = getFirstBird();

        currentBird.setPosX(SLINGSHOT_CENTER.x - ((currentBird.getWidth()) / 2));
        currentBird.setPosY(SLINGSHOT_CENTER.y - ((currentBird.getLength()) / 2));

        addMouseMotionListener(this);
        addMouseListener(this);
        new Thread(this).start();
    }

    private Bird getFirstBird() {
        for (Bird b : birds) {
            if (b.getOrder() == 1) {
                return b;
            }
        }
        return null;
    }
    private void newCurrentBird() {
        for (Bird b: birds) {
            b.setOrder(b.getOrder()-1);
        }
    }

    private void init() {
        for (GameObject o : level.getObjects()) {
            if (o instanceof Bird) {
                Bird b = (Bird) o;
                if (b.getOrder() > 1) {
                    b.setPosX(PLATEFORM_WIDTH - b.getWidth() * b.getOrder());
                    b.setPosY(PLATEFORM_HEIGHT + FOOTER);
                }
                birds.add(b);
                gBirds.add(new GCharacter(b));
            } else if (o instanceof Pig) {
                Pig p = (Pig) o;
                p.setPosX(p.getPosX()+OFFSET);
                p.setPosY(p.getPosY()+FOOTER);
                pigs.add(p);
                gPigs.add(new GCharacter(p));
            } else if (o instanceof Decor) {
                Decor d = (Decor) o;
                d.setPosX(d.getPosX()+OFFSET);
                d.setPosY(d.getPosY()+FOOTER);
                decors.add(d);
                gDecors.add(new GDecor(d));
            }
        }
        //STRUCTURE
        Decor d = (Decor) Loader.getInstance().getDecors().get("Structure").clone();
        d.setPosY(FOOTER);
        d.setPosX(0);
        d.setWidth(PLATEFORM_WIDTH);
        d.setLength(PLATEFORM_HEIGHT);
        gDecors.add(new GDecor(d));

        //GROUND
        Decor g = (Decor) Loader.getInstance().getDecors().get("Ground").clone();
        g.setPosY(0);
        g.setPosX(0);
        g.setWidth(Frame.getInstance().getWidth());
        g.setLength(FOOTER-30);
        gDecors.add(new GDecor(g));

        //GRASS
        Decor gr = (Decor) Loader.getInstance().getDecors().get("Grass").clone();
        gr.setPosY(FOOTER-30);
        gr.setPosX(0);
        gr.setWidth(Frame.getInstance().getWidth());
        gr.setLength(30);
        gDecors.add(new GDecor(gr));

        level.getObjects().add(gr);
        level.getObjects().add(d);

        collisionManager = new CollisionManager(level.getObjects());

    }

    public void paintComponent(Graphics g2) {
        super.paintComponent(g2);
        Graphics2D g = (Graphics2D) g2;

        g.drawImage(backG, 0, -200, null);

        g.translate(0, getHeight() - 1);
        g.scale(1, -1);

        // SLINGSHOT
        g.setColor(new Color(102, 51, 0));
        g.fillRect(PLATEFORM_WIDTH - SLINGSHOT_WIDTH,
                FOOTER + PLATEFORM_HEIGHT,
                SLINGSHOT_WIDTH,
                SLINGSHOT_HEIGHT);
        Stroke s = g.getStroke();
        g.setStroke(new BasicStroke(10));

        g.drawLine(PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2,
                FOOTER + PLATEFORM_HEIGHT + SLINGSHOT_HEIGHT,
                PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2 + SLINGSHOT_OFFSET,
                FOOTER + PLATEFORM_HEIGHT + SLINGSHOT_HEIGHT + SLINGSHOT_OFFSET);

        g.drawLine(PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2,
                FOOTER + PLATEFORM_HEIGHT + SLINGSHOT_HEIGHT,
                PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2 - SLINGSHOT_OFFSET,
                FOOTER + PLATEFORM_HEIGHT + SLINGSHOT_HEIGHT + SLINGSHOT_OFFSET);
        g.setStroke(s);


        for (GDecor d : gDecors) {
            d.draw(g);
        }
        for (GCharacter b: gBirds) {
            b.draw(g);
        }
        for (GCharacter p : gPigs) {
            p.draw(g);
        }
    }

    public void mouseDragged(MouseEvent mouseEvent) {
        /*if (Tools.distance(mouseEvent.getX(), getHeight() - mouseEvent.getY(),
                SLINGSHOT_CENTER.x, SLINGSHOT_CENTER.y) < SLINGSHOT_CENTER.x) {
            if (Tools.distance(currentBird.getPosX() + currentBird.getWidth() / 2,
                    getHeight() - (currentBird.getPosY() + currentBird.getLength() / 2 ),
                    mouseEvent.getX(),
                    mouseEvent.getY()) < currentBird.getWidth() * 2) {*/
                getFirstBird().setPosX(mouseEvent.getX() - currentBird.getWidth() / 2);
                getFirstBird().setPosY(getHeight() - mouseEvent.getY() - currentBird.getLength() / 2 );
       /*     }
        }
*/

    }
    public void mouseReleased(MouseEvent mouseEvent) {
        currentBird.setVelocityX( currentBird.getSpeed() * (SLINGSHOT_CENTER.x - currentBird.getPosX() ) /20);
        currentBird.setVelocityY( currentBird.getSpeed() * (SLINGSHOT_CENTER.y - currentBird.getPosY() ) /20);
        currentBird.setSelected(true);
        currentBird  = null;
    }



    public void run() {
        while (true) {
            try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }
            collisionManager.checkCollision();
            if (currentBird == null) {
                timerRelaodBird-=10;
            }
            if (currentBird == null && timerRelaodBird == 0) {
                timerRelaodBird = 1000;
                newCurrentBird();
                currentBird = getFirstBird();
                if (currentBird != null) {
                    currentBird.setPosX(SLINGSHOT_CENTER.x - ((currentBird.getWidth()) / 2));
                    currentBird.setPosY(SLINGSHOT_CENTER.y - ((currentBird.getLength()) / 2));

                    for (Bird b : birds) {
                        if (b.getOrder() > 1) {
                            b.setPosX(PLATEFORM_WIDTH - b.getWidth() * b.getOrder());
                            b.setPosY(PLATEFORM_HEIGHT);
                        }
                    }
                }
            }

            for (Bird b : birds) {
                if (b.isSelected()){
                    b.setPosX((int) (b.getPosX()+b.getVelocityX()));
                    b.setPosY((int) (b.getPosY()+b.getVelocityY()));
                    level.getGravity().agis_sur_GameObject(b);
                    b.setState(GameObjectState.FLYING);
                } else {
                    b.setState(GameObjectState.IDLE);
                }

            }
            for (Pig p : pigs) {
                level.getGravity().agis_sur_GameObject(p);
                p.setState(GameObjectState.IDLE);
            }
            for (Decor d : decors) {
                level.getGravity().agis_sur_GameObject(d);
            }
            repaint();
        }
    }

    public boolean winCondition(){
        return false;
    }

    public void mouseEntered(MouseEvent mouseEvent) {}
    public void mouseExited(MouseEvent mouseEvent) {}
    public void mousePressed(MouseEvent mouseEvent) {}
    public void mouseMoved(MouseEvent mouseEvent) {}
    public void mouseClicked(MouseEvent mouseEvent) {}
}
