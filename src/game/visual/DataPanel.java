package game.visual;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DataPanel extends JPanel {
	JLabel humanPoints = new JLabel();
	JLabel pcPoints = new JLabel();
	private BackPanel backP;
	
	public DataPanel(BackPanel backP) {
		this.setLayout(null);
		this.setSize(150, 500);
		setLocation(0, 0);
		this.setBackground(Color.black);
		this.backP=backP;
	}
	
	public void setPoints(int pointsPC,int pointsHUMAN) {
		
	}
	
	@Override
	public void paint(Graphics g) {
		this.setSize(backP.getWidth()*1/3,backP.getHeight());
		super.paint(g);
		
	}
}
