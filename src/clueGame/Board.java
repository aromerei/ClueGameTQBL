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
	private boolean debug = true;

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
		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		calcAdjacencies();
	}

	public void loadRoomConfig() throws FileNotFoundException {
		FileReader reader;
		reader = new FileReader(roomConfigFile);
		Scanner in = new Scanner(reader);
		while (in.hasNextLine()) {
			String entry = in.nextLine();
			if (debug)
				System.out.println("adding entry: " + entry);
			legend.put(entry.charAt(0), entry.substring(3, entry.indexOf(",", 3)));
		}
		if (debug)
			System.out.println("added " + legend.size() + " legend entries");
	}

	public void loadBoardConfig() throws FileNotFoundException {
		FileReader reader;
		reader = new FileReader(boardConfigFile);
		Scanner in = new Scanner(reader);
		int col = 0;
		in.useDelimiter(",");
		int row = 0;		
		while (in.hasNext()) {
			break;
		}
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
