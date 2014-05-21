package game.graphic;

import file.FileState;
import game.Game;
import game.State;
import game.Turn;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class TilesWindow extends JFrame {
	BackgroundPanel back;
	Game game;
	
	public TilesWindow(Game game) {
		super("Tile's Game");
		setSize(900,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		this.game = game;
		back = new BackgroundPanel(game);
		setLayout(new BorderLayout());
		
		add(back);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		FileState fs = new FileState("archivo.txt",Turn.HUMAN);
		TilesWindow tw = new TilesWindow(fs.initialize());
		
	}
}
