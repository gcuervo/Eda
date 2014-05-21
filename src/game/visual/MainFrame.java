package game.visual;

import file.FileState;
import game.State;
import game.Turn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.crypto.spec.OAEPParameterSpec;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private State state;
	private DataPanel dataP;
	private BoardPanel boardP ;
	private BackPanel backP = new BackPanel();
	
	public MainFrame(State state) {
		super("Tile's Game");
		this.setLayout(new BorderLayout());
		boardP = new BoardPanel(backP);
		dataP=new DataPanel(backP);
		this.state = state;
		this.add(backP);
		backP.add(dataP);
		backP.add(boardP);
		this.setSize(500,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		System.out.println(backP.getHeight());
	}
	
	public static void main(String[] args) {
		FileState fs = new FileState("archivo.txt",Turn.HUMAN);
		MainFrame frame = new MainFrame(fs.initialize());
		
	}
	
	public void drawGame() {
		//this.
	}
}
