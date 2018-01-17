package game.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import game.level.Level;
import game.objects.Bird;
import game.objects.Decor;
import game.objects.GameObject;
import game.objects.Pig;
import game.tools.Constants;

public class LevelPanel extends Panel implements MouseMotionListener {

	private final int FOOTER = 50;
	private final int OFFSET = 200;
	private final int PLATEFORM_WIDTH = 150;
	private final int PLATEFORM_HEIGHT = 200;

	private final int SLINGSHOT_WIDTH = 10;
	private final int SLINGSHOT_HEIGHT = 75;

	private final int SLINGSHOT_OFFSET = 50;

	private final Point SLINGSHOT_CENTER = new Point(PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2,
				PLATEFORM_HEIGHT + SLINGSHOT_HEIGHT + SLINGSHOT_OFFSET / 2);

	private ArrayList<Shape> decors;
	private ArrayList<Shape> birds;
	private ArrayList<Shape> pigs;
	
	private int currentBird = 1;

	private Level level;

	public LevelPanel(Level level) {
		super();
		this.level = level;
		decors = new ArrayList<>();
		birds = new ArrayList<>();
		pigs = new ArrayList<>();
		addMouseMotionListener(this);
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	private void init() {
	}

	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2;

		g.translate(0,getHeight() -1 );
		g.scale(1,-1);


		// FOND
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), 0);

		// GROUND
		g.setColor(Color.BLACK);
		g.drawLine(0,  FOOTER, getWidth(), FOOTER);

		// PLATEFORME
		g.setColor(Color.BLACK);
		g.fillRect(0,  FOOTER, PLATEFORM_WIDTH, PLATEFORM_HEIGHT);

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

		for (GameObject o : level.getObjects()) {

			if (o instanceof Bird) {
				Bird b = (Bird) o;
				if (b.getOrder() == currentBird) {
				    b.setPosX(SLINGSHOT_CENTER.x - ((b.getWidth())/2 ));
				    b.setPosY(SLINGSHOT_CENTER.y - ((b.getLength())/2));

				    System.out.println(b.getPosX());
                    System.out.println(b.getPosY());

				} else {
				    b.setPosX(PLATEFORM_WIDTH - b.getWidth() *b.getOrder());
				    b.setPosY(PLATEFORM_HEIGHT);
				}
                Sphere sp = new Sphere(b,g);
				sp.draw(0, FOOTER);
				birds.add(sp);
			} else if (o instanceof Pig) {
				Pig p = (Pig) o;
				Sphere sp = new Sphere(p,g);
				sp.draw(OFFSET,FOOTER);
				pigs.add(sp);
			} else if (o instanceof Decor) {
				Decor d = (Decor) o;
				Cube c = new Cube(d,g);
				c.draw(OFFSET, FOOTER);
				decors.add(c);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseMoved(MouseEvent mouseEvent) {

	}
}
