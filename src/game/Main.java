package game;

import file.FileState;

public class Main {
	public static void main(String[] args) {
		//Leer por linea de comando 
		/*State state = readFile(file,turn);
		MiniMax minimax = new MiniMax(prune,tree,depth,time);
		Game game = new Game(state,minimax);
*/
		
		State state = readFile("archivo.txt",Turn.PC);
		MiniMax minimax = new MiniMax(false,true,3,0);
		Game game = new Game(state,minimax);
		boolean visual = false;
		
		if ( visual ) {
			game.visualGame();
		} else {
			game.consoleGame();
		}
	}
	
	
	
	public static State readFile(String file,Turn turn) {
		FileState fs = new FileState(file,turn);
		return fs.initialize();
	}
}
