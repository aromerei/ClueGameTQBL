package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTests  {
	
	public static IntBoard board;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		board = new IntBoard(4,4);
	}

	@Test
	public void testAdjacency0() //Top Left Corner
	{
		
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}

}
