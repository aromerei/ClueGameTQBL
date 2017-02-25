package experiment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {

		private Map<BoardCell, HashSet<BoardCell>> adjMtx;
		private Set<BoardCell> visited;
		private Set<BoardCell> targets;
		private BoardCell[][] grid = new BoardCell[4][4];
		
		public IntBoard()
		{
			for(int i = 0; i < grid.length-1; i++)
			{
				for(int e = 0; e < grid[i].length-1; e++)
				{
					
					grid[i][e] = new BoardCell(i,e);
				}
			}
			adjMtx = new HashMap<BoardCell, HashSet<BoardCell>>();
			visited = new HashSet<BoardCell>();
			targets = new HashSet<BoardCell>();
		}
		public void calcAdjacencies()
		{
			for(int i = 0; i < grid.length; i ++)
			{
				for(int e = 0; e < grid.length; e++)
				{
					//e = cols; i = rows.
					HashSet<BoardCell> temp = new HashSet<BoardCell>();
					if(e - 1 >= 0) // if up is in bounds
					{
						temp.add(grid[i][e-1]);
					}
					if(e+1<=grid.length - 1) // if down is in bounds
					{
						temp.add(grid[i][e+1]);
					}
					if(i - 1 >= 0)// if left is in bounds
					{
						temp.add(grid[i-1][e]);
					}
					if(i+1 <=grid.length-1)// if right is in bounds 
					{
						temp.add(grid[i+1][e]);
					}
					adjMtx.put(grid[i][e], temp);
				}
			}
		}
		public void calcTargets(BoardCell startCell, int pathLength)
		{
			visited.clear();
			visited.add(startCell);
			findAllTargets(startCell, pathLength);
		}
		private void findAllTargets(BoardCell startCell, int pathLength)
		{
			for(BoardCell c: adjMtx.get(startCell))
			{
				if(!(visited.contains(c)))
				{
					visited.add(c);
					if(pathLength == 1)
					{
						targets.add(c);
					}
					findAllTargets(c,pathLength-1);
					visited.remove(c);
				}
			}
		}
		public Set<BoardCell> getTargets()
		{
			return targets;
		}
		public Set<BoardCell> getAdjList(BoardCell place)
		{
			return adjMtx.get(place);
		}
		public BoardCell getCell(int x, int y)
		{
			return grid[x][y];
		}
}
