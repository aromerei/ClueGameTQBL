package clueGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.sun.jmx.snmp.Timestamp;

import clueGame.BoardCell;

public class Board {
	private int numRows;
	private int numColumns;
	public static final int MAX_BOARD_SIZE = 50;
	private BoardCell[][] board;
	private Map<Character, String> legend;
	private Set<BoardCell> targets;
	private Map<BoardCell, HashSet<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
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
		adjMtx = new HashMap<BoardCell, HashSet<BoardCell>>();
		visited = new HashSet<BoardCell>();
		board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (Exception e) {
			if (debug)
				System.out.println("caught error, printing to logfile");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
			File errorlog = new File(sdf.format(date) + ".log");
			if (debug)
				System.out.println("creating log " + errorlog);
			try {
				errorlog.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(errorlog));
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				writer.write(sw.toString());
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
					&& !entry.substring(entry.lastIndexOf(",") + 2).equals("Other")) {
				throw new BadConfigFormatException("Room type incorrect, must be of type Card or Other");
			}
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
		for (int i = 0; i < numRows; i++) {
			for (int e = 0; e < numColumns; e++) {
				// e = cols; i = rows.
				HashSet<BoardCell> temp = new HashSet<BoardCell>();
				if(board[i][e].getDoorDirection() != DoorDirection.NONE)
				{
					switch(board[i][e].getDoorDirection())
					{
					case UP:
						temp.add(board[i-1][e]);
						break;
					case DOWN:
						temp.add(board[i+1][e]);
						break;
					case LEFT:
						temp.add(board[i][e-1]);
						break;
					case RIGHT:
						temp.add(board[i][e+1]);
						break;
					case NONE:
						break;
					}
				}
				else if(board[i][e].getInitial() == 'W')
				{
					if (e - 1 >= 0) // if Left is in bounds
					{
						if(board[i][e - 1].getInitial() == 'W' || board[i][e-1].getDoorDirection() == DoorDirection.RIGHT)
						{
							temp.add(board[i][e - 1]);		
						}	
					}
					if (e + 1 < numColumns) // if right is in bounds
					{
						if(board[i][e+1].getInitial() == 'W' || board[i][e+1].getDoorDirection() == DoorDirection.LEFT)
							temp.add(board[i][e + 1]);
					}
					if (i - 1 >= 0)// if Up is in bounds
					{
						if(board[i - 1][e].getInitial() == 'W' || board[i - 1][e].getDoorDirection() == DoorDirection.DOWN)
							temp.add(board[i - 1][e]);
					}
					if (i + 1 < numRows)// if Down is in bounds
					{
						if(board[i + 1][e].getInitial() == 'W' || board[i + 1][e].getDoorDirection() == DoorDirection.UP)
							temp.add(board[i + 1][e]);			
					}
					
				}
				adjMtx.put(board[i][e], temp);
				
				
			}
		}
	}

//	public void calcTargets(BoardCell startCell, int pathLength) {
//		visited.clear();
//		visited.add(startCell);
//		targets.clear();
//		findAllTargets(startCell, pathLength);
//	}

	private void findAllTargets(BoardCell startCell, int pathLength) {
		Set<BoardCell> cells = new HashSet<BoardCell>();
		cells = adjMtx.get(board[startCell.getRow()][startCell.getColumn()]);
		for (BoardCell c : cells) {
			if (visited.contains(board[c.getRow()][c.getColumn()]))
			{
			}
			else
			{
				visited.add(board[c.getRow()][c.getColumn()]);
				if (pathLength == 1) 
				{
						targets.add(board[c.getRow()][c.getColumn()]);
				}
				else if(board[c.getRow()][c.getColumn()].isDoorway())
				{
					targets.add(board[c.getRow()][c.getColumn()]);
				}
				else
					findAllTargets(board[c.getRow()][c.getColumn()], pathLength - 1);
				visited.remove(board[c.getRow()][c.getColumn()]);
			}
			
		}
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

	public Set<BoardCell> getAdjList(int i, int j) {
		return adjMtx.get(board[i][j]);
	}

	public void calcTargets(int row, int col, int steps) {
		visited.clear();
		targets.clear();
		visited.add(board[row][col]);
		findAllTargets(board[row][col], steps);
	}

	

	public Set<BoardCell> getTargets() {
		return targets;
	}
}
