package game.graphics;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import game.graphics.GameObjectsGraphics.GCharacter;
import game.graphics.GameObjectsGraphics.GDecor;
import game.level.Level;
import game.objects.Bird;
import game.objects.Decor;
import game.objects.GameObject;
import game.objects.Pig;
import game.tools.Constants;
import game.tools.Tools;

import javax.imageio.ImageIO;

public class LevelPanel extends Panel implements MouseMotionListener, MouseListener, Runnable {

    private final int FOOTER = 50;
    private final int OFFSET = 200;
    private final int PLATEFORM_WIDTH = 150;
    private final int PLATEFORM_HEIGHT = 200;

    private final int SLINGSHOT_WIDTH = 10;
    private final int SLINGSHOT_HEIGHT = 75;

    private final int SLINGSHOT_OFFSET = 50;

    private final Point SLINGSHOT_CENTER = new Point(
            PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2,
            PLATEFORM_HEIGHT + SLINGSHOT_HEIGHT + SLINGSHOT_OFFSET / 2);

    private ArrayList<Decor> decors;
    private ArrayList<Bird> birds;
    private ArrayList<Pig> pigs;

    BufferedImage backG;
    {
        try {
            backG = ImageIO.read(new File(Constants.BG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Bird currentBird;
    private Level level;

    private boolean initPanel = true;

    public LevelPanel(Level level) {
        super();
        this.level = level;

        decors = new ArrayList();
        birds = new ArrayList();
        pigs = new ArrayList();

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
                    b.setPosY(PLATEFORM_HEIGHT);
                }
                birds.add(b);
            } else if (o instanceof Pig) {
                Pig p = (Pig) o;
                pigs.add(p);
            } else if (o instanceof Decor) {
                Decor d = (Decor) o;
                decors.add(d);
            }
        }
    }

    public void paintComponent(Graphics g2) {
        super.paintComponent(g2);
        Graphics2D g = (Graphics2D) g2;

        g.drawImage(backG, 0, -200, null);

        g.translate(0, getHeight() - 1);
        g.scale(1, -1);

        // GROUND
        g.setColor(Color.BLACK);
        g.drawLine(0, FOOTER, getWidth(), FOOTER);

        // PLATEFORME
        g.setColor(Color.BLACK);
        g.fillRect(0, FOOTER, PLATEFORM_WIDTH, PLATEFORM_HEIGHT);

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


        for (Bird b: birds) {
            GCharacter bird = new GCharacter(b, g);
            bird.draw(0, FOOTER);
        }
        for (Pig p : pigs) {
            GCharacter pig = new GCharacter(p, g);
            pig.draw(OFFSET, FOOTER);
        }
        for (Decor d :decors) {
            GDecor decor = new GDecor(d, g);
            decor.draw(OFFSET, FOOTER);
        }
    }

    public void mouseDragged(MouseEvent mouseEvent) {
        if (Tools.distance(mouseEvent.getX(), getHeight() - mouseEvent.getY() - FOOTER,
                SLINGSHOT_CENTER.x, SLINGSHOT_CENTER.y) < SLINGSHOT_CENTER.x) {
            if (Tools.distance(currentBird.getPosX() + currentBird.getWidth() / 2,
                    getHeight() - (currentBird.getPosY() + currentBird.getLength() / 2 + FOOTER),
                    mouseEvent.getX(),
                    mouseEvent.getY()) < currentBird.getWidth() * 2) {
                getFirstBird().setPosX(mouseEvent.getX() - currentBird.getWidth() / 2);
                getFirstBird().setPosY(getHeight() - mouseEvent.getY() - currentBird.getLength() / 2 - FOOTER);
            }
        }


    }
    public void mouseReleased(MouseEvent mouseEvent) {
        currentBird.setVelocityX( (SLINGSHOT_CENTER.x - currentBird.getPosX() ) /20);
        currentBird.setVelocityY( (SLINGSHOT_CENTER.y - currentBird.getPosY() ) /20);
        currentBird.setSelected(true);
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

    public void run() {
        while (true) {
            try { Thread.currentThread().sleep(10); } catch(InterruptedException e) { }
            for (Bird b : birds) {
                if (b.isSelected()){
                    b.setPosX((int) (b.getPosX()+b.getVelocityX()));
                    b.setPosY((int) (b.getPosY()+b.getVelocityY()));
                    level.getGravity().agis_sur_GameObject(b);
                }
            }
            for (Pig p : pigs) {
                level.getGravity().agis_sur_GameObject(p);
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
