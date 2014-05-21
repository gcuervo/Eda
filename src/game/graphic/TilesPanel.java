package game.graphic;

import game.Board;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class TilesPanel extends JPanel {
	Point point;
	Board board;
	
	public TilesPanel(Point p, char color,Board board) {
		this.point = p;
		this.board = board;
		
		switch (color) {
		case '0':
			setBackground(Color.white);
			break;
		case '1':
			setBackground(Color.blue);
			break;
		case '2':
			setBackground(Color.red);
			break;
		case '3':
			setBackground(Color.pink);
			break;
		case '4':
			setBackground(Color.yellow);
			break;
		case '5':
			setBackground(Color.orange);
			break;
		case '6':
			setBackground(Color.green);
			break;
		case '7':
			setBackground(Color.black);
			break;
		case '8':
			setBackground(Color.cyan);
			break;
		case '9':
			setBackground(Color.magenta);
			break;
		}
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(point);
			}
		});
	}
}
