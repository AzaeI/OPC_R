package game.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.level.Level;

public class PlayButtonListener implements ActionListener {

	private Level level;
	
	public PlayButtonListener(Level level) {
		super();
		this.level = level;
	}

	public void actionPerformed(ActionEvent e) {
		Frame.getInstance().setPanel(new LevelPanel(level));
	}

}
