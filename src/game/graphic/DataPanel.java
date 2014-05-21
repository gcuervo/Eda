package game.graphic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DataPanel extends JPanel {
	private int hPoints,pcPoints;
	
	public DataPanel(int hpoints,int pcpoints) {
		setBounds(0, 0, 900, 100);
		setBackground(new Color(250,212,64));
		this.hPoints = hpoints;
		this.pcPoints = pcpoints;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.black);
		Font fTitle = new Font("Serif", Font.BOLD, 30);
		g.setFont(fTitle);
		g.drawString("TILE'S GAME", 325, 40);
		
		g.setColor(Color.black);
		Font fPoints = new Font("Monospaced", Font.BOLD, 20);
		g.setFont(fPoints);
		g.drawString("Human Points: " + hPoints, 30, 70);
		g.drawString("PC Points: " + pcPoints, 700, 70);
	}
}
