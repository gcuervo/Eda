package game.blocks;

public class ActiveBlock implements Block {
	private char id;
	private final static int movements[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 },
			{ 0, -1 } };

	public ActiveBlock(char id) {
		this.id = id;
	}

	@Override
	public int remove(Block[][] board, int posX, int posY) {
		return remove(board,posX,posY,id);
	}

	private int remove(Block[][] board, int posX, int posY, char id) {
		if (!this.equals(board[posX][posY]))
			return 0;
			
		board[posX][posY] = new EmptyBlock();
		int suma = 0;
		
		for (int i = 0; i < movements.length; i++) {
			int auxX = posX + movements[i][0];
			int auxY = posY + movements[i][1];
			if (auxX >= 0 && auxX < board.length && auxY >= 0
					&& auxY < board[0].length) {
				suma += remove(board, auxX, auxY,id);
			}
		}
		return suma + 1;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( obj != null && (obj instanceof ActiveBlock) )
			if ( ((ActiveBlock)obj).id == this.id )
				return true;
				
		return false;
	}
	
	public String toString() {
		return String.valueOf(id);
	}

	@Override
	public char getColor() {
		return id;
	}
	
	
}
