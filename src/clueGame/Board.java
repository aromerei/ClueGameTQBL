package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
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
	private boolean debug = false;

	// variable used for singleton pattern
	private static Board theInstance = new Board();

	// ctor is private to ensure only one can be created
	private Board() {
	}

	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}

	public void initialize() {
		legend = new HashMap<Character, String>();
		targets = new HashSet<BoardCell>();
		board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (Exception e) {
			System.out.println(e);
		}
		calcAdjacencies();
	}

	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader reader;
		reader = new FileReader(roomConfigFile);
		Scanner in = new Scanner(reader);
		while (in.hasNextLine()) {
			String entry = in.nextLine();
			if (debug)
				System.out.println("adding entry: " + entry + "\ntesting room type: \""
						+ entry.substring(entry.lastIndexOf(",") + 2) + "\"");
			if (!entry.substring(entry.lastIndexOf(",") + 2).equals("Card")
					&& !entry.substring(entry.lastIndexOf(",") + 2).equals("Other"))
				throw new BadConfigFormatException("Room type incorrect, must be of type Card or Other");
			legend.put(entry.charAt(0), entry.substring(3, entry.indexOf(",", 3)));
		}
		if (debug)
			System.out.println("added " + legend.size() + " legend entries");
	}

	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader reader;
		reader = new FileReader(boardConfigFile);
		Scanner in = new Scanner(reader);
		int col = 0;
		int row = 0;
		while (in.hasNextLine()) {
			String boardrow = in.nextLine();
			col = 0;
			while (boardrow.length() > 0) {
				if (debug)
					System.out.println("adding boardcell at " + row + ", " + col + " of type " + boardrow.charAt(0));
				if (!legend.containsKey(boardrow.charAt(0)))
					throw new BadConfigFormatException("Room type does not exist ");
				board[row][col] = new BoardCell(row, col, boardrow.charAt(0));
				if (boardrow.length() > 1 && boardrow.charAt(1) != ',') {
					if (boardrow.charAt(1) != 'N') {
						switch (boardrow.charAt(1)) {
						case 'U':
							board[row][col].setDoorDirection(DoorDirection.UP);
							break;
						case 'D':
							board[row][col].setDoorDirection(DoorDirection.DOWN);
							break;
						case 'R':
							board[row][col].setDoorDirection(DoorDirection.RIGHT);
							break;
						case 'L':
							board[row][col].setDoorDirection(DoorDirection.LEFT);
							break;
						}
					}
				}
				col++;
				if (boardrow.contains(","))
					boardrow = boardrow.substring(boardrow.indexOf(",") + 1);
				else
					boardrow = "";
			}
			if (debug)
				System.out.println("Columns: " + numColumns + " read cols: " + col);
			if (row > 1 && col != numColumns)
				throw new BadConfigFormatException("Column count does not match");
			numColumns = col;
			row++;
		}
		if (debug)
			System.out.println("rows: " + row + " cols: " + col);
		numRows = row;
	}

	public void calcAdjacencies() {

	}

	public void calcTargets(BoardCell startCell, int pathLength) {

	}

	public void setConfigFiles(String board, String rooms) {
		boardConfigFile = board;
		if (debug)
			System.out.println(boardConfigFile);
		roomConfigFile = rooms;
	}

	public Map<Character, String> getLegend() {
		return legend;
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
