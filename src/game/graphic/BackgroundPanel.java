package game.graphic;

import game.Game;

import java.awt.Color;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	DataPanel dp;
	BoardPanel bp;
	
	public BackgroundPanel(Game game) {
		setLayout(null);
		setSize(getHeight(),getWidth());
		setBackground(Color.green);
		dp = new DataPanel(game.getPointsHuman(),game.getPointsPC());
		bp = new BoardPanel(game);
		add(dp);
		add(bp);
		
	}
}
