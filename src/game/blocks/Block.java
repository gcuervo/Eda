package game.blocks;

import game.InvalidMoveException;

public interface Block {
	
	public int remove(Block[][] board,int posx,int posy) throws InvalidMoveException;
	
	public boolean isEmpty();

	public char getColor();
}
