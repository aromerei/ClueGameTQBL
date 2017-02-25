package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
		visited = new TreeSet<>();
		targets = new TreeSet<>();
		grid = new BoardCell[x][y];
		boardRows = x;
		boardCols = y;
	}
	public void calcAdjacencies()
	{
		Set<BoardCell> cellSet =  new TreeSet<>();
		for(int i = 0; i < boardRows; i++)
		{
			for(int e = 0; e < boardCols; i++)
			{
				BoardCell temp = new BoardCell(i,e);
				if(i - 1 >= 0)
				{
					cellSet.add(new BoardCell(i-1, e));
				}
				if(i + 1 != boardRows -1)
				{
					cellSet.add(new BoardCell(i+1, e));
				}
				if(e - 1 >= 0)
				{
					cellSet.add(new BoardCell(i, e-1));
				}
				if(e + 1 < boardCols-1)
				{
					cellSet.add(new BoardCell(i, e+1));
				}
				adjMtx.put(temp,cellSet);
			}
		}
	}
	public void calcTargets(BoardCell startCell, int pathLength)
	{
		targets.clear();
		visited.clear();
		visited.add(startCell);
		calcAdjacencies();//might not need this later when we start coding.
		findAllTargets(startCell, pathLength);
	}
	private void findAllTargets(BoardCell thisCell, int numSteps)
	{
		for(BoardCell c: adjMtx.get(thisCell))
		{
			if(!visited.contains(c))
			{
				visited.add(c);
				if(numSteps == 1)
				{
					targets.add(c);
				}
				else
				{
					findAllTargets(c,numSteps-1);
				}
				visited.remove(c);
			}
		}
		
	}
	public Set<BoardCell> getTargets()
	{
		Set<BoardCell> temp = new HashSet<BoardCell>();
		
		return temp;
	}
	public Set<BoardCell> getAdjList(BoardCell cell)
	{
		Set<BoardCell> temp = new HashSet<BoardCell>();
		
		return temp;
	}
	public BoardCell getCell(int i, int x) 
	{
		BoardCell temp = new BoardCell(i, x);
		return temp;
	}
}
