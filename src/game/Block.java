package game;

public interface Block {
	
	public int remove(Block[][] board,int posx,int posy) throws InvalidMoveException;
	
	public boolean isEmpty();
}
