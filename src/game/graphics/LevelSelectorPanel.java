package game.graphics;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.tools.Tool;

public class LevelSelectorPanel extends Panel {

	
	public LevelSelectorPanel() {
		setBackground(Color.gray);
		JPanel p = new JPanel(new GridLayout(0, 2));
		
		List<String> names = Tool.levelsNames();
		for (String n : names) {
			p.add(new JLabel(n));
			p.add(new JButton("Play"));
		}
		add(p);
	}
}
