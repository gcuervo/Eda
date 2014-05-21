package game;

import java.awt.Point;
import java.util.List;

public class State {
	public static final double BONUS = 0.3;
	private int pointsPC;
	private int pointsHuman;
	private Board board;

	public State(Board board, int pointsPC, int pointsHuman) {
		this.pointsPC = pointsPC;
		this.pointsHuman = pointsHuman;
		this.board = board;
	}

	public State(State state) {
		this.board = state.board;
		this.pointsHuman = state.pointsHuman;
		this.pointsPC = state.pointsPC;
	}

	public List<Point> moves() {
		return board.moves();
	}

	public void removeAndPoints(Point p, Turn turn) throws InvalidMoveException {
		int cant = board.remove(p.x, p.y);
		int points = getPoints(cant);

		switch (turn) {
		case PC:
			pointsPC += points;
			if (board.isEmpty()) {
				pointsPC += pointsPC * BONUS;
			}
			break;
		case HUMAN:
			pointsHuman += points;
			if (board.isEmpty()) {
				pointsHuman += pointsHuman * BONUS;
			}
			break;
		}

	}

	private int getPoints(int cant) {
		switch (cant) {
		case 2:
			return 1;
		case 3:
			return 2;
		case 4:
			return 4;
		case 5:
			return 8;
		default:
			return 2 * cant;
		}
	}

	public int getPointsPC() {
		return pointsPC;
	}

	public int getPointsHuman() {
		return pointsHuman;
	}

	public boolean isOver() {
		return board.isOver();
	}

	public void addPointsPC(int points) {
		pointsPC += points;
	}

	public void addPointsHuman(int points) {
		pointsHuman += points;
	}

	public State copyState() {
		return new State(board.copyBoard(), pointsPC, pointsHuman);
	}
	
	public int getHeuristicValue() {
		return pointsPC - pointsHuman;
	}
	
	public Board getBoard() {
		return board;
	}
}
