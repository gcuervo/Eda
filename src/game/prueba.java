package game;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class prueba {
	public static void main(String[] args) {
		int[][] board = { 
				{0,0,2,1,1,1},
				{0,0,3,2,1,1},
				{0,0,3,2,2,1},
				{0,0,3,1,3,2},
				{0,1,1,1,1,1} };
		
		for ( Point point: moves(board)) 
			System.out.println(point);
	}
	
	public static List<Point> moves(int[][] board) {
		List<Point> ans = new LinkedList<Point>();
		boolean included = false;

		for (int i = 0; i < board.length; i++) {
			for (int j = board[0].length - 1; j >= 0 && board[i][j] != 0; j--) {
				if (i - 1 >= 0 && board[i - 1][j] == board[i][j]) {
					included = true;
				}
				if (j - 1 >= 0 && board[i][j - 1] != board[i][j] ) {
					if (((i + 1 < board.length && board[i + 1][j] == board[i][j])
							|| (j + 1 < board[0].length && board[i][j + 1] == board[i][j])) && !included) {
						ans.add(new Point(i,j));
					}
					included = false;
				}
			}
			included = false;
		}
		return ans;
	}
}
