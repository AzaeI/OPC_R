package game.graphics;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import game.tools.Constants;

public class Frame extends JFrame{
	
	private Panel lvSelector; 
	
	public Frame() {
		super(Constants.GAME_NAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(getPreferredSize());
		setLocationRelativeTo(null);
		lvSelector = new LevelSelectorPanel();
		add(lvSelector);
		setVisible(true);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1000,800);
	}
}
