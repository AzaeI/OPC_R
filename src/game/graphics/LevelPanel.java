package game.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import game.level.Level;
import game.objects.Bird;
import game.objects.Decor;
import game.objects.GameObject;
import game.objects.Pig;

public class LevelPanel extends Panel {

	private final int MULT = 30;
	private final int FOOTER = 50;
	private final int OFFSET = 200;
	private final int PLATEFORM_WIDTH = 5 * MULT;
	private final int PLATEFORM_HEIGHT = 7 * MULT;

	private final int SLINGSHOT_WIDTH = 10;
	private final int SLINGSHOT_HEIGHT = 75;

	private final int SLINGSHOT_OFFSET = 50;
	private final int BIRD_OFFSET = MULT;
	
	private int currentBird = 1;

	private Level level;

	public LevelPanel(Level level) {
		super();
		this.level = level;
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
		
		Point SLINGSHOT_CENTER = new Point(PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2,
				getHeight() - FOOTER - PLATEFORM_HEIGHT - SLINGSHOT_HEIGHT - SLINGSHOT_OFFSET / 2);

		// FOND
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		// GROUND
		g.setColor(Color.BLACK);
		g.drawLine(0, getHeight() - FOOTER, getWidth(), getHeight() - FOOTER);

		// PLATEFORME
		g.setColor(Color.BLACK);
		g.fillRect(0, getHeight() - FOOTER - PLATEFORM_HEIGHT, PLATEFORM_WIDTH, PLATEFORM_HEIGHT);

		// SLINGSHOT
		g.setColor(new Color(102, 51, 0));
		g.fillRect(PLATEFORM_WIDTH - SLINGSHOT_WIDTH, getHeight() - FOOTER - PLATEFORM_HEIGHT - SLINGSHOT_HEIGHT,
				SLINGSHOT_WIDTH, SLINGSHOT_HEIGHT);
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke(10));

		g.drawLine(PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2, getHeight() - FOOTER - PLATEFORM_HEIGHT - SLINGSHOT_HEIGHT,
				PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2 - SLINGSHOT_OFFSET,
				getHeight() - FOOTER - PLATEFORM_HEIGHT - SLINGSHOT_HEIGHT - SLINGSHOT_OFFSET);
		g.drawLine(PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2, getHeight() - FOOTER - PLATEFORM_HEIGHT - SLINGSHOT_HEIGHT,
				PLATEFORM_WIDTH - SLINGSHOT_WIDTH / 2 + SLINGSHOT_OFFSET,
				getHeight() - FOOTER - PLATEFORM_HEIGHT - SLINGSHOT_HEIGHT - SLINGSHOT_OFFSET);
		g.setStroke(s);

		for (GameObject o : level.getObjects()) {

			if (o instanceof Bird) {
				Bird b = (Bird) o;
				g.setColor(b.getColor());
				if (b.getOrder() == currentBird) {
					g.fillOval(SLINGSHOT_CENTER.x - ((b.getWidth()*MULT)/2), 
							SLINGSHOT_CENTER.y - ((b.getLength()*MULT)/2),
							b.getWidth()*MULT, 
							b.getLength()*MULT);
				} else {
					g.fillOval(PLATEFORM_WIDTH - BIRD_OFFSET*b.getOrder(), 
						getHeight()-FOOTER-PLATEFORM_HEIGHT-b.getLength()*MULT,
						b.getWidth()*MULT, 
						b.getLength()*MULT);
				}
			} else if (o instanceof Pig) {
				Pig p = (Pig) o;
				g.setColor(p.getColor());
				g.fillOval(p.getPosX() * MULT + OFFSET,
						p.getPosY() * MULT + getHeight() - p.getLength() * MULT - FOOTER, p.getWidth() * MULT,
						p.getLength() * MULT);
			} else if (o instanceof Decor) {
				Decor d = (Decor) o;
				g.setColor(d.getColor());
				g.fillRect(d.getPosX() * MULT + OFFSET,
						d.getPosY() * MULT + getHeight() - d.getLength() * MULT - FOOTER, d.getWidth() * MULT,
						d.getLength() * MULT);
			}
		}
	}

}
