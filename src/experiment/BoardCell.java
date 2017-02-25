package experiment;

public class BoardCell {
	private int row;
	private int column;
	public BoardCell()
	{
		row = 0;
		column = 0;
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
