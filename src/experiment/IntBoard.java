package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	
	public IntBoard()
	{
		adjMtx = new HashMap<>();
	}
	public void calcAdjacencies(BoardCell temp)
	{
		
	}
	public void calcTargets(BoardCell startCell, int pathLength)
	{
		//Calculates the targets that are pathLength distance from the startCell. The list of targets will be stored in an 		instance variable.
	}
	public Set<BoardCell> getTargets()
	{
		Set<BoardCell> temp = new HashSet<BoardCell>();
		
		return null;
	}
	public Set<BoardCell> getAdjList()
	{
		Set<BoardCell> temp = new HashSet<BoardCell>();
		
		return null;
	}
}
