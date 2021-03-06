package game.graphics;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import game.Loader;
import game.engine.CollisionManager;
import game.graphics.GameObjectsGraphics.GCharacter;
import game.graphics.GameObjectsGraphics.GDecor;
import game.graphics.GameObjectsGraphics.GGravity;
import game.level.Level;
import game.objects.*;
import game.objects.abstractClass.Bird;
import game.objects.abstractClass.Decor;
import game.objects.abstractClass.Gravity;
import game.objects.abstractClass.Pig;
import game.tools.Constants;
import game.tools.Tools;

import javax.imageio.ImageIO;

public class LevelPanel extends Panel implements MouseMotionListener, MouseListener, Runnable {

    private final int PLATEFORM_WIDTH = 150;
    private final int PLATEFORM_HEIGHT = 30;
    private final int PLATEFORM_POSY = 300;

    private final int SLINGSHOT_WIDTH = 10;
    private final int SLINGSHOT_HEIGHT = 120;
    private final int SLINGSHOT_OFFSET = 50;

    private final Point SLINGSHOT_CENTER = new Point(
            PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2,
            PLATEFORM_HEIGHT + PLATEFORM_POSY + SLINGSHOT_HEIGHT + SLINGSHOT_OFFSET / 2);

    private ArrayList<Decor> decors;
    private ArrayList<Bird> birds;
    private ArrayList<Pig> pigs;
    private ArrayList<Gravity> gravities;

    private ArrayList<GDecor> gDecors;
    private ArrayList<GCharacter> gBirds;
    private ArrayList<GCharacter> gPigs;
    private ArrayList<GGravity> gGravities;

    private ArrayList<Pig> pigToRemove;
    private ArrayList<Bird> birdToRemove;

    private ArrayList<GCharacter> gPigToRemove;
    private ArrayList<GCharacter> gBirdToRemove;

    private BufferedImage backG;
    {
        try {
            backG = ImageIO.read(new File(Constants.BG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage homeBtn;
    {
        try {
            homeBtn = ImageIO.read(new File(Constants.HOME));
            homeBtn = Tools.resize(homeBtn, 100,100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int timerRelaodBird = 1000;     //1sec
    private final int timerDie = 1000;     //1sec

    private volatile boolean shutdown;
    public boolean isGameFinish;
    public boolean isWon;

    private CollisionManager collisionManager;

    private Bird currentBird;
    private Level level;

    public LevelPanel(Level level) {
        super();
        this.level = level;
        this.isGameFinish = false;
        this.isWon = false;
        this.setLayout(null);

        shutdown = false;

        decors = new ArrayList();
        birds = new ArrayList();
        pigs = new ArrayList();
        gravities = new ArrayList();

        gDecors = new ArrayList();
        gBirds = new ArrayList();
        gPigs = new ArrayList();
        gGravities = new ArrayList();

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
        for (Bird b : birds) {
            b.setOrder(b.getOrder() - 1);
        }
    }

    private void init() {
        gPigToRemove = new ArrayList<>();
        gBirdToRemove = new ArrayList<>();
        for (GameObject o : level.getObjects()) {
            if (o instanceof Bird) {
                Bird b = (Bird) o;
                if (b.getOrder() > 1) {
                    b.setPosX(PLATEFORM_WIDTH - b.getWidth() * b.getOrder());
                    b.setPosY(PLATEFORM_HEIGHT + PLATEFORM_POSY);
                }
                b.getVector().init();
                b.setState(GameObjectState.IDLE);
                b.setCurrentHp(b.getHp());
                b.setTimeToLive(Constants.TIME_TO_LIVE_BIRD);
                birds.add(b);
                gBirds.add(new GCharacter(b, timerDie));
            } else if (o instanceof Pig) {
                Pig p = (Pig) o;
                p.getVector().init();
                p.setState(GameObjectState.IDLE);
                p.setCurrentHp(p.getHp());
                pigs.add(p);
                gPigs.add(new GCharacter(p, timerDie));
            } else if (o instanceof Decor) {
                Decor d = (Decor) o;
                d.getVector().init();
                decors.add(d);
                gDecors.add(new GDecor(d));
            } else if (o instanceof Gravity) {
                Gravity g = (Gravity) o;
                gravities.add(g);
                gGravities.add(new GGravity(g));
            }
        }
        //STRUCTURE
        Decor d = (Decor) Loader.getInstance().getDecors().get("Structure").clone();
        d.setPosY(PLATEFORM_POSY);
        d.setPosX(0);
        d.setWidth(PLATEFORM_WIDTH);
        d.setLength(PLATEFORM_HEIGHT);
        gDecors.add(new GDecor(d));

        level.getObjects().add(d);

        collisionManager = new CollisionManager(level.getObjects());
    }

    public void run() {
        while (!shutdown) {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
            }
            collisionManager.checkCollision();

            if (checkLoseCondition()) {
                isGameFinish = true;
                closeGame(false);
            } else if (checkWinCondition()) {
                isWon = true;
                isGameFinish = true;
                closeGame(true);
            }

            pigToRemove = new ArrayList();
            birdToRemove = new ArrayList();

            //Timer reload bird
            if (currentBird == null) {
                timerRelaodBird -= 10;
            }

            //Reload new bird
            if (currentBird == null && timerRelaodBird == 0) {
                newCurrentBird();
                currentBird = getFirstBird();
                if (currentBird != null) {
                    timerRelaodBird = 1000;
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
            //move birds
            if (birds.size() != 0) {
                for (Bird b : birds) {

                    if (b.isSelected()) {
                        b.reduceTTL();
                        if (!b.isAlive() || b.getState().equals(GameObjectState.DEAD)) {
                            b.setCurrentHp(0);
                            b.setState(GameObjectState.DEAD);
                            GCharacter c = gBirds.stream().filter(gp -> gp.getGo().equals(b)).findFirst().get();
                            c.setTimeToDisappear(timerDie);
                            gBirdToRemove.add(c);
                        }
                        b.move();
                        b.setState(GameObjectState.FLYING);
                    }
                }
            }

            //move pigs
            if (pigs.size() != 0) {
                for (Pig p : pigs) {
                    if (p.getState().equals(GameObjectState.DEAD)) {
                        pigToRemove.add(p);
                        GCharacter c = gPigs.stream().filter(gp -> gp.getGo().equals(p)).findFirst().get();
                        c.setTimeToDisappear(timerDie);
                        gPigToRemove.add(c);
                    } else {
                        p.move();
                    }
                }
            }

            //move decors
            for (Decor d : decors) {
                if (d.isMovable()) {
                    d.move();
                }
            }
            //remove Dead birds to list
            if (birdToRemove.size() > 0) {
                for (Bird b : birdToRemove) {
                    birds.remove(b);
                }
            }

            //remove Dead pigs to list
            if (pigToRemove.size() > 0) {
                for (Pig p : pigToRemove) {
                    pigs.remove(p);
                }
            }

            // reduce timer on birds
            if (gBirdToRemove.size() > 0) {
                for (GCharacter gc : gBirdToRemove) {
                    gc.decreaseTimerDisappear();
                }
            }
            // reduce timer on pigs
            if (gPigToRemove.size() > 0) {
                for (GCharacter gc : gPigToRemove) {
                    gc.decreaseTimerDisappear();
                }
            }

            repaint();
        }
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
                PLATEFORM_HEIGHT + PLATEFORM_POSY,
                SLINGSHOT_WIDTH,
                SLINGSHOT_HEIGHT);
        Stroke s = g.getStroke();
        g.setStroke(new BasicStroke(10));

        g.drawLine(PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2,
                PLATEFORM_HEIGHT + PLATEFORM_POSY + SLINGSHOT_HEIGHT,
                PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2 + SLINGSHOT_OFFSET,
                PLATEFORM_HEIGHT + SLINGSHOT_HEIGHT + PLATEFORM_POSY + SLINGSHOT_OFFSET);

        g.drawLine(PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2,
                PLATEFORM_HEIGHT + PLATEFORM_POSY + SLINGSHOT_HEIGHT,
                PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2 - SLINGSHOT_OFFSET,
                PLATEFORM_HEIGHT + SLINGSHOT_HEIGHT + PLATEFORM_POSY + SLINGSHOT_OFFSET);
        g.setStroke(s);

        for (GDecor d : gDecors) {
            if (d.getGo().getWidth() == 0) d.getGo().setWidth(getWidth());
            if (d.getGo().getLength() == 0) d.getGo().setLength(getHeight());
            d.draw(g);
        }
        for (GCharacter b : gBirds) {
            if (b.getTimeToDisappear() <= 0) {
                b.getGo().setState(GameObjectState.TOMB_BIRD);
            }
            b.draw(g);
        }
        for (GCharacter p : gPigs) {
            if (p.getTimeToDisappear() <= 0) {
                p.getGo().setState(GameObjectState.TOMB);
            }
            p.draw(g);
        }
        for (GGravity gr : gGravities) {
            if (gr.getGo().getWidth() == 0) gr.getGo().setWidth(getWidth());
            if (gr.getGo().getLength() == 0) gr.getGo().setLength(getHeight());
            if (gr.getGo().getRadius() == 0) gr.getGo().setRadius(getWidth());
            gr.draw(g);
        }

        g.translate(0, getHeight() - 1);
        g.scale(1, -1);
        if (isGameFinish) {
            g.setColor(Color.lightGray);
            g.fillRect(getWidth()/4, getHeight()/3, getWidth()/2, getHeight()/4);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Serif Bold", Font.BOLD, 30));
            if (isWon) {
                g.drawString("Vous avez gagné !",getWidth()/2 - getWidth()/5, getHeight()/2 - getHeight()/32);
            } else {
                g.drawString("Vous avez perdu !",getWidth()/2 - getWidth()/5, getHeight()/2 - getHeight()/32);
            }
            g.drawImage(homeBtn, 0, 0, null);
        }

    }

    public void mouseDragged(MouseEvent mouseEvent) {
        if (currentBird != null) {
            Point tmp_MouseP = new Point(mouseEvent.getX(), getHeight() - mouseEvent.getY());
            int max = SLINGSHOT_HEIGHT;

            if (tmp_MouseP.distance(SLINGSHOT_CENTER) < max) {
                getFirstBird().setPosX(mouseEvent.getX() - currentBird.getWidth() / 2);
                getFirstBird().setPosY(getHeight() - mouseEvent.getY() - currentBird.getLength() / 2);
            } else {
                Point tmp = Tools.interpolationByDistance(SLINGSHOT_CENTER, tmp_MouseP, max);

                getFirstBird().setPosX(tmp.x - currentBird.getWidth() / 2);
                getFirstBird().setPosY(tmp.y - currentBird.getLength() / 2);
            }
        }
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        if (currentBird != null) {
            double distance = SLINGSHOT_CENTER.distance(currentBird.getVector().getCenter());
            if (!(distance < Constants.FORCE_MIN)) {
                currentBird.getVector().setDirection((Point) SLINGSHOT_CENTER.clone());
            } else {
                Point tmp = Tools.interpolationByDistance(currentBird.getVector().getCenter(), SLINGSHOT_CENTER, Constants.FORCE_MIN);
                currentBird.getVector().setDirection(tmp);
            }
            currentBird.setSelected(true);
            currentBird = null;
        }
    }

    private boolean checkWinCondition() {
        for (GCharacter gc : gPigs) {
            if (gc.getGo().getState() != GameObjectState.DEAD) return false;
        }
        return true;
    }

    private boolean checkLoseCondition() {
        for (Bird b : birds) {
            if (b.isAlive()) return false;
        }
        return true;
    }

    private void closeGame(boolean win) {
        //this.shutdown = true;
        if (win) {

        }
        //Frame.getInstance().setSelectorLvPannel();
    }

    private void exitGame() {
        this.shutdown = true;
        Frame.getInstance().setSelectorLvPannel();
    }

    public void mouseEntered(MouseEvent mouseEvent) {
    }

    public void mouseExited(MouseEvent mouseEvent) {
    }

    public void mousePressed(MouseEvent mouseEvent) {
    }

    public void mouseMoved(MouseEvent mouseEvent) {
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getX() < 100 && mouseEvent.getY() < 100) {
            exitGame();
        }
    }
}
