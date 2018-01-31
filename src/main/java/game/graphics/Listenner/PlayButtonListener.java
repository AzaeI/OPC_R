package game.graphics.Listenner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.graphics.Frame;
import game.graphics.LevelPanel;
import game.level.Level;

public class PlayButtonListener implements ActionListener {

	private Level level;
	
	public PlayButtonListener(Level level) {
		super();
		this.level = level;
	}

	public void actionPerformed(ActionEvent e) {
		Frame.getInstance().setLevelPannel(level);
	}

}
