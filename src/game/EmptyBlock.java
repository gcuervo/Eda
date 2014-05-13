package game;

public class EmptyBlock implements Block {

	@Override
	public int remove(Block[][] board, int posx, int posy) {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( obj != null && (obj instanceof EmptyBlock) )
			return true;
		return false;
	}
}
