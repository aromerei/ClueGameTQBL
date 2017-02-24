package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import experiment.BoardCell;

public class IntBoard {
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	private static int  boardRows;
	private static int boardCols;
	
	
	public IntBoard(int x, int y)
	{
		adjMtx = new HashMap<>();
		visited = new HashSet<>();
		targets = new HashSet<>();
		grid = new BoardCell[x][y];
		boardRows = x;
		boardCols = y;
	}
	public void calcAdjacencies()
	{
		Set<BoardCell> cellSet =  new HashSet<>();
		for(int i = 0; i < boardRows; i++)
		{
			for(int e = 0; e < boardCols; i++)
			{
				BoardCell temp = new BoardCell(i,e);
				if(i - 1 >= 0)
				{
					cellSet.add(new BoardCell(i-1, e));
				}
				if(i + 1 < boardRows)
				{
					cellSet.add(new BoardCell(i+1, e));
				}
				if(e - 1 >= 0)
				{
					cellSet.add(new BoardCell(i, e-1));
				}
				if(e + 1 < boardCols)
				{
					cellSet.add(new BoardCell(i, e+1));
				}
				adjMtx.put(temp,cellSet);
			}
		}
	}
	public void calcTargets(BoardCell startCell, int pathLength)
	{
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
	}
	private void findAllTargets(BoardCell thisCell, int numSteps)
	{
		
	}
	public Set<BoardCell> getTargets()
	{
		Set<BoardCell> temp = new HashSet<BoardCell>();
		
		return null;
	}
	public Set<BoardCell> getAdjList(BoardCell cell)
	{
		Set<BoardCell> temp = new HashSet<BoardCell>();
		
		return null;
	}
	public BoardCell getCell(int i, int x) 
	{
		BoardCell temp = new BoardCell(i, x);
		return temp;
	}
}
