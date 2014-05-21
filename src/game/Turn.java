package game;

public enum Turn {
	HUMAN , PC;
	
	public Turn changeTurn() {
		return (this == HUMAN)?PC:HUMAN;
	}
}
