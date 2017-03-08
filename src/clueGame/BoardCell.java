package clueGame;

public class BoardCell {
	public enum CellType {
		ROOM, WALKWAY, DOORWAY
	}

	private int row;
	private int column;
	private char initial;
	private DoorDirection doordir;

	public BoardCell() {
		row = 0;
		column = 0;
	}
	public BoardCell(int i, int e)
	{
		row = i;
		column = e;
	}

	public BoardCell(int i, int e, char c) {
		row = i;
		column = e;
		initial = c;
		doordir = DoorDirection.NONE;
	}

	public boolean isWalkway() {
		return false;
	}

	public boolean isRoom() {
		return false;
	}

	public boolean isDoorway() {
		return doordir != DoorDirection.NONE;
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
	
	public void setDoorDirection(DoorDirection dir) {
		doordir = dir;
	}

	public DoorDirection getDoorDirection() {
		return doordir;
	}

	public char getInitial() {
		return initial;
	}

}
