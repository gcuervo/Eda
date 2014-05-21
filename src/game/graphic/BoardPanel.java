package game.graphic;

import game.Game;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private Game game;
	
	public BoardPanel(Game game) {
		//setLayout(new GridLayout(board.length, board[0].length));
		setLayout(new GridLayout(board.getBlocks()[0].length, board.getBlocks().length, 1, 1));
		setBounds(0, 100, 900, 600);
		setBackground(Color.white);
		this.board = board;
		
		for ( int i = 0 ; i < board.getBlocks()[0].length ; i++ ) {
			for ( int j = 0 ; j < board.getBlocks().length ; j++ ) {
				add(new TilesPanel(new Point(j,i), board.getBlocks()[j][i].getColor(),board));
			}
		}
		
	}
}
