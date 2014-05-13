package game;

import java.awt.Point;
import java.util.List;


public class Board {
	private Block[][] board;
	
	public Board(Block[][] board) {
		this.board = board;
	}
	
	public int remove(int posx,int posy){
		
	}
	
	private void gravity() {
		int x = 0,y = 0;
		boolean zero = false;
		
		for ( int i = 0 ; i < board.length ; i++ ) {
			for ( int j = board[0].length-1 ; j >= 0 ; j-- ) {
				if ( !zero && board[i][j].isEmpty() ) {
					x = i;
					y = j;
					zero = true;
				}
				if ( zero && !board[i][j].isEmpty() ) {
					System.out.println("i="+i+" j="+j+" x="+x+" y="+y);
					board[x][y] = board[i][j];
					board[i][j] = new EmptyBlock();
					y--;
				}
			}
			zero=false;
		}
	}
	
	
	private void lateralGravity() {
		int x = -1;
		for ( int i = 0 ; i < board.length ; i++ ) {
			if ( x<0 && board[i][board[0].length-1].isEmpty() )
				x = i;
			if ( x >= 0 && !board[i][board[0].length-1].isEmpty() ) {
				swapCol(i,x);
				x++;
			}
		}
	}
	
	private void swapCol(int n, int k) {
		Block[] aux = board[n];
		board[n] = board[k];
		board[k] = aux;
	}
	

	public boolean isEmpty() {
		return board[0][board[0].length-1].isEmpty();
	}
	
	public boolean isOver() {
		if ( isEmpty() ) 
			return true;
		
		for ( int i = 0 ; i < board.length && !board[i][board[0].length-1].isEmpty() ; i++ ) {
			for ( int j = board[0].length-1 ; j >= 0 && !board[i][j].isEmpty() ; j-- ) {
				if ( i+1 < board.length ) {
					System.out.println("**"+board[i][j]+" "+board[i+1][j]);
					if ( board[i][j].equals(board[i+1][j]) ) 
						return false;
				}
				if ( j-1 >= 0 ) {
					System.out.println("***"+board[i][j]+" "+board[i][j-1]);
					if ( board[i][j].equals(board[i][j-1]) ) 
						return false;
				}
			}
		}
		return true;
	}
	
	public List<Point> moves() {
		return null;
	}
}
