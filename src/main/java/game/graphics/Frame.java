package game.graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

import game.level.Level;
import game.tools.Constants;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;

	private static Frame mainFrame;
	
	private Panel lvSelector;
	
	public static Frame getInstance() {
	      if(mainFrame == null) {
	    	  mainFrame = new Frame();
	      }
	      return mainFrame;
	   }
	
	protected Frame() {
		super(Constants.GAME_NAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(getPreferredSize());
		setLocationRelativeTo(null);
		lvSelector = new LevelSelectorPanel();
		setContentPane(lvSelector);
		setVisible(true);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1000,800);
	}
	private void setPanel(Panel panel) {
		setContentPane(panel);
		panel.revalidate();
	}

	public void setSelectorLvPannel () {
		mainFrame.setPanel(new LevelSelectorPanel());
	}

	public void setLevelPannel (Level l) {
		mainFrame.setPanel(new LevelPanel(l));
	}

}
