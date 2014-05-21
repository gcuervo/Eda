package game;

import java.awt.Point;

public class Game {	
	private State state;
	private MiniMax minimax;
	private Turn turn;
	
	public Game(State state,MiniMax minimax,Turn turn) {
		this.state = state;
		this.minimax = minimax;
		this.turn = turn;
	}
	
	public void consoleGame() {
		if ( state.isOver() ) {
			System.out.println("No hay movidas posibles");
		} else {
			Point ans = minimax.miniMax(state);
			System.out.println("(" + ans.y + "," + ans.x + ")");
		}
	}

	public void visualGame() {
		
	}
	
	public int getPointsPC() {
		return state.getPointsPC();
	}
	
	public int getPointsHuman() {
		return state.getPointsHuman();
	}
	
	public int getBoardCols() {
		return state.getBoard().get;
	}
	
	public int getBoardRows() {
		
	}
}
