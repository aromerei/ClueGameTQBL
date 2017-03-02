package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class TQBL_BardAdjTargetTest {

	private static Board board;

	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance and initialize it
		board = Board.getInstance();
		board.initialize();
	}

	@Test
	public void testAdjacenciesInsideRooms() {//fix later
		// Test a corner
		Set<BoardCell> testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(1, 13);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(15, 4);
		assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(18, 10);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(18, 17);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(5, 20);
		assertEquals(0, testList.size());
	}
	
	@Test
	public void testAdjacencyDoorways() {
		// Test beside a door direction RIGHT
		Set<BoardCell> testList = board.getAdjList(4, 5);
		assertTrue(testList.contains(board.getCellAt(3, 5)));
		assertTrue(testList.contains(board.getCellAt(4, 6)));
		assertTrue(testList.contains(board.getCellAt(5, 5)));
		assertTrue(testList.contains(board.getCellAt(4, 4)));
		assertEquals(3, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(6, 15);
		assertTrue(testList.contains(board.getCellAt(5, 15)));
		assertTrue(testList.contains(board.getCellAt(6, 14)));
		assertTrue(testList.contains(board.getCellAt(6, 16)));
		assertEquals(3, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(15, 17);
		assertTrue(testList.contains(board.getCellAt(15, 16)));
		assertTrue(testList.contains(board.getCellAt(15, 18)));
		assertTrue(testList.contains(board.getCellAt(14, 17)));
		assertTrue(testList.contains(board.getCellAt(16, 17)));
		assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(13, 11);
		assertTrue(testList.contains(board.getCellAt(13, 10)));
		assertTrue(testList.contains(board.getCellAt(13, 12)));
		assertTrue(testList.contains(board.getCellAt(12, 11)));
		assertTrue(testList.contains(board.getCellAt(14, 11)));
		assertEquals(4, testList.size());
	}

}
