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
	

}
