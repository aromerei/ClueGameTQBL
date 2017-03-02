package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;

	public BoardCell() {
		row = 0;
		column = 0;
	}

	public BoardCell(int i, int e, char c) {
		row = i;
		column = e;
		initial = c;
	}
	
	public boolean isWalkway()
	{
		return false;
	}
	
	public boolean isRoom()
	{
		return false;
	}
	
	public boolean isDoorway()
	{
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + "]";
	}

	public DoorDirection getDoorDirection() {
		return DoorDirection.NONE;
	}

	public char getInitial() {
		return initial;
	}

	
}
