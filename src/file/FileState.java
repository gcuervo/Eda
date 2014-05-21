package file;

import game.Board;
import game.State;
import game.Turn;
import game.blocks.ActiveBlock;
import game.blocks.Block;
import game.blocks.EmptyBlock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileState {
	private String name;
	private Turn turn;
	
	public FileState(String name,Turn turn) {
		this.name = name;
		this.turn = turn;
	}
	
	public State initialize() {
		FileInputStream file;
		int value;
		String row = new String();
		String col = new String();
		String points1 = new String();
		String points2 = new String();
		
		try {
			file = new FileInputStream(name);
			while ( (value = file.read()) != '\n' )
				row+=(char)value;
			while ( (value = file.read() ) != '\n')
				col+=(char)value;
			
			Block[][] board = new Block[Integer.parseInt(col)][Integer.parseInt(row)];
			
			while ( (value = file.read()) != '\n' ) 
				points1+=(char)value;
			while ( (value = file.read()) != '\n' ) 
				points2+=(char)value;
			for ( int i = 0 ; i < board[0].length ; i++ ) {
				for ( int j = 0 ; j < board.length ; j++ ) {
					if ( ( value = file.read() ) != ' ' ) 
						board[j][i] = new ActiveBlock((char)value);
					else
						board[j][i] = new EmptyBlock();
				}
				file.read();	// Lee el \n
			}
			
			if ( turn == Turn.HUMAN ) 
				return new State(new Board(board),Integer.parseInt(points2),Integer.parseInt(points1));
			else
				return new State(new Board(board),Integer.parseInt(points1),Integer.parseInt(points2));
			
		} catch (FileNotFoundException e) {
			System.err.println("No existe archivo");
		} catch (IOException e) {
			System.err.println("IOE Exception");
		}
		return null;
	}
}
