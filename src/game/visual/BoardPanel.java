package game.visual;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private BackPanel backP;
	
	public BoardPanel(BackPanel backP) {
		this.setLayout(null);
		this.setBackground(Color.blue);
		this.setSize(350, 500);
		setLocation(150,0);
		this.setVisible(true);
		this.backP=backP;
	}
	
	@Override
	public void paint(Graphics g) {
		this.setSize(backP.getWidth()*2/3,backP.getHeight());
		this.setLocation(backP.getWidth()*1/3, 0);
		super.paint(g);
		
	}

}
