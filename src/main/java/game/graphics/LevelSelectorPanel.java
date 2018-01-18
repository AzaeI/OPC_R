package game.graphics;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JLabel;

import game.Loader;
import game.level.Level;

public class LevelSelectorPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public LevelSelectorPanel() {
		super();
		setLayout(new GridLayout(0, 2));
		setBackground(Color.gray);

		ArrayList<Level> levels = Loader.getInstance().getLevels();

		for (Level level : levels) {
			JLabel title = new JLabel(level.getName());
			JButton b = new JButton("Play");
			b.addActionListener(new PlayButtonListener(level));
			add(title);
			add(b);
		}

	}
}
