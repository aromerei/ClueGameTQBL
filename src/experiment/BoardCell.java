package experiment;

public class BoardCell {
	private int row;
	private int column;
	public BoardCell()
	{
	}
	public BoardCell(int i, int e)
	{
		row = i;
		column = e;
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	
}
