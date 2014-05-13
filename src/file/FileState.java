package file;

import game.ActiveBlock;
import game.Block;
import game.Board;
import game.EmptyBlock;
import game.HumanState;
import game.PCState;
import game.State;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileState {
	private String name;
	private boolean turn;
	
	public FileState(String name,boolean turn) {
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
				file.read();
			}
			
			if ( turn ) 
				return new PCState(new Board(board),Integer.parseInt(points1),Integer.parseInt(points2));
			else
				return new HumanState(new Board(board),Integer.parseInt(points1),Integer.parseInt(points2));
			
		} catch (FileNotFoundException e) {
			System.err.println("No existe archivo");
		} catch (IOException e) {
			System.err.println("IOE Exception");
		}
		return null;
	}
	
}
