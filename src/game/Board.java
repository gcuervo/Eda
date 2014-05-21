package game;

import game.blocks.Block;
import game.blocks.EmptyBlock;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class Board {
	private final static int movements[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 },
			{ 0, -1 } };
	private Block[][] board;

	public Board(Block[][] board) {
		this.board = board;
	}

	public int remove(int posx, int posy) throws InvalidMoveException {
		if (board[posx][posy].isEmpty())
			throw new InvalidMoveException("Invalid movement");
		else if (!aroundValidate(board, posx, posy))
			throw new InvalidMoveException("Invalid Movement" + posx + " " + posy);
		else {
			int aux = board[posx][posy].remove(board, posx, posy);
			gravity();
			lateralGravity();
			return aux;
		}
	}

	private void gravity() {
		int x = 0, y = 0;
		boolean zero = false;

		for (int i = 0; i < board.length; i++) {
			for (int j = board[0].length - 1; j >= 0; j--) {
				if (!zero && board[i][j].isEmpty()) {
					x = i;
					y = j;
					zero = true;
				} else if (zero && !board[i][j].isEmpty()) {
					board[x][y] = board[i][j];
					board[i][j] = new EmptyBlock();
					y--;
				}
			}
			zero = false;
		}
	}

	private void lateralGravity() {
		int x = -1;
		for (int i = 0; i < board.length; i++) {
			if (x < 0 && board[i][board[0].length - 1].isEmpty())
				x = i;
			if (x >= 0 && !board[i][board[0].length - 1].isEmpty()) {
				swapCol(i, x);
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
		return board[0][board[0].length - 1].isEmpty();
	}

	public boolean isOver() {
		if (isEmpty())
			return true;

		for (int i = 0; i < board.length
				&& !board[i][board[0].length - 1].isEmpty(); i++) {
			for (int j = board[0].length - 1; j >= 0 && !board[i][j].isEmpty(); j--) {
				if (i + 1 < board.length) {
					if (board[i][j].equals(board[i + 1][j]))
						return false;
				}
				if (j - 1 >= 0) {
					if (board[i][j].equals(board[i][j - 1]))
						return false;
				}
			}
		}
		return true;
	}

	public List<Point> moves() {
		List<Point> ans = new LinkedList<Point>();
		boolean included = false;

		for (int i = 0; i < board.length; i++) {
			for (int j = board[0].length - 1; j >= 0 && !board[i][j].isEmpty(); j--) {
				if (i - 1 >= 0 && board[i - 1][j].equals(board[i][j])) {
					included = true;
				}
				if (j-1 < 0 || (j - 1 >= 0 && !board[i][j - 1].equals(board[i][j])) ) {
					if (!included && ((i + 1 < board.length && board[i + 1][j].equals(board[i][j])) || (j + 1 < board[0].length && board[i][j + 1].equals(board[i][j])) )) {
						ans.add(new Point(i, j));
					}
					included = false;
				}
			}
			included = false;
		}
		return ans;
	}

	private boolean aroundValidate(Block[][] board, int posX, int posY) {

		for (int i = 0; i < movements.length; i++) {
			int auxX = posX + movements[i][0];
			int auxY = posY + movements[i][1];
			if (auxX >= 0 && auxX < board.length && auxY >= 0
					&& auxY < board[0].length) {
				if (board[posX][posY].equals(board[auxX][auxY])) {
					return true;
				}
			}
		}
		return false;
	}

	public Board copyBoard() {
		Block[][] ans = new Block[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				ans[i][j] = board[i][j];
			}
		}
		return new Board(ans);
	}

	public void print() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public Block[][] getBlocks() {
		return board;
	}
}
