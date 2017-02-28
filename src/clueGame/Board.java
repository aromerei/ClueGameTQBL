package clueGame;

import java.util.Map;
import java.util.Set;



public class Board {
	private int numRows;
	private int numColumns;
	public static final int MAX_BOARD_SIZE = 50;
	private BoardCell[][] board;
	private Map<Character, String> legend;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	
	// variable used for singleton pattern
	private static Board theInstance = new Board();
		// ctor is private to ensure only one can be created
	private Board() {
	}
		// this method returns the only Board
	public static Board getInstance() {
			return theInstance;
		}
	
	
	public void initialize()
	{
		
	}
	
	public void loadRoomConfig()
	{
		
	}
	
	public void loadBoardConfig()
	{
		
	}
	
	public void calcAdjacencies()
	{
		
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) 
	{
		
	}
	public void setConfigFiles(String string, String string2) {
		boardConfigFile = string;
		roomConfigFile = string2;
		
	}
	public Map<Character, String> getLegend() {
	
		return null;
	}
	public int getNumRows() {
		
		return numRows;
	}
	public int getNumColumns() {
	
		return numColumns;
	}
	public BoardCell getCellAt(int i, int j) {
		
		return board[i][j];
	}

}
