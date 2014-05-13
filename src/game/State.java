package game;

import java.awt.Point;
import java.util.List;

public abstract class State {
	private int pointsPC;
	private int pointsHuman;
	private Board board;
	
	public State(Board board,int pointsPC,int pointsHuman) {
		this.pointsPC = pointsPC;
		this.pointsHuman = pointsHuman;
		this.board = board;
	}
	
	public List<State> moves(List<Point> coord) {
		return null;
	}
	
	public void remove(Point p){
	}
}
