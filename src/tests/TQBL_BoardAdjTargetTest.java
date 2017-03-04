package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class TQBL_BoardAdjTargetTest {

	private static Board board;

	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance and initialize it
		board = Board.getInstance();
		board.initialize();
	}

	@Test
	public void testAdjacenciesInsideRooms() {// fix later
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

	// green border
	@Test
	public void testAdjacencyDoorways() {
		// Test beside a door direction RIGHT
		Set<BoardCell> testList = board.getAdjList(8, 5);
		assertTrue(testList.contains(board.getCellAt(7, 5)));
		assertTrue(testList.contains(board.getCellAt(8, 6)));
		assertTrue(testList.contains(board.getCellAt(9, 5)));
		assertTrue(testList.contains(board.getCellAt(8, 4)));
		assertEquals(4, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(5, 4);
		assertTrue(testList.contains(board.getCellAt(4, 4)));
		assertTrue(testList.contains(board.getCellAt(5, 5)));
		assertTrue(testList.contains(board.getCellAt(6, 4)));
		assertTrue(testList.contains(board.getCellAt(5, 3)));
		assertEquals(4, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(12, 10);
		assertTrue(testList.contains(board.getCellAt(11, 10)));
		assertTrue(testList.contains(board.getCellAt(12, 11)));
		assertTrue(testList.contains(board.getCellAt(13, 10)));
		assertTrue(testList.contains(board.getCellAt(12, 9)));
		assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(9, 8);
		assertTrue(testList.contains(board.getCellAt(8, 8)));
		assertTrue(testList.contains(board.getCellAt(9, 9)));
		assertTrue(testList.contains(board.getCellAt(10, 8)));
		assertTrue(testList.contains(board.getCellAt(9, 7)));
		assertEquals(4, testList.size());
	}

	// test various travel distances of selected cells
	@Test
	public void testWalkwayCells() {
		// test targets for cell adjacent to one doorway and near another
		// 1 step
		board.calcTargets(6, 12, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 12)));
		assertTrue(targets.contains(board.getCellAt(7, 12)));
		assertTrue(targets.contains(board.getCellAt(6, 11)));
		// 2 steps
		board.calcTargets(6, 12, 2);
		targets = board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 11)));
		assertTrue(targets.contains(board.getCellAt(6, 10)));
		assertTrue(targets.contains(board.getCellAt(7, 11)));
		assertTrue(targets.contains(board.getCellAt(8, 12)));
		// 3 steps
		board.calcTargets(6, 12, 3);
		targets = board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCellAt(5, 12)));
		assertTrue(targets.contains(board.getCellAt(7, 12)));
		assertTrue(targets.contains(board.getCellAt(6, 11)));
		assertTrue(targets.contains(board.getCellAt(5, 10)));
		assertTrue(targets.contains(board.getCellAt(6, 9)));
		assertTrue(targets.contains(board.getCellAt(7, 10)));
		assertTrue(targets.contains(board.getCellAt(8, 11)));
		assertTrue(targets.contains(board.getCellAt(8, 13)));
		// 4 steps
		board.calcTargets(6, 12, 4);
		targets = board.getTargets();
		assertEquals(11, targets.size());
		// 5 steps
		board.calcTargets(6, 12, 5);
		targets = board.getTargets();
		assertEquals(16, targets.size());
		// 6 steps
		board.calcTargets(6, 12, 6);
		targets = board.getTargets();
		assertEquals(20, targets.size());

		// test targets for cell with multiple doors in range
		// 1 step
		board.calcTargets(7, 8, 1);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		// 2 steps
		board.calcTargets(7, 8, 2);
		targets = board.getTargets();
		assertEquals(8, targets.size());
		// 3 steps
		board.calcTargets(7, 8, 3);
		targets = board.getTargets();
		assertEquals(16, targets.size());
		// 4 steps
		board.calcTargets(7, 8, 4);
		targets = board.getTargets();
		assertEquals(20, targets.size());
		// 5 steps
		board.calcTargets(7, 8, 5);
		targets = board.getTargets();
		assertEquals(29, targets.size());
		// 6 steps
		board.calcTargets(7, 8, 6);
		targets = board.getTargets();
		assertEquals(32, targets.size());

		// test targets and adjacency list of walkway in corner with no doors in
		// range
		Set<BoardCell> testList = board.getAdjList(10, 0);
		assertTrue(testList.contains(board.getCellAt(10, 1)));
		assertTrue(testList.contains(board.getCellAt(11, 0)));
		assertEquals(2, testList.size());
		// 1 step
		board.calcTargets(10, 0, 1);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		// 2 steps
		board.calcTargets(10, 0, 2);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		// 3 steps
		board.calcTargets(10, 0, 3);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		// 4 steps
		board.calcTargets(10, 0, 4);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		// 5 steps
		board.calcTargets(10, 0, 5);
		targets = board.getTargets();
		assertEquals(6, targets.size());
		// 6 steps
		board.calcTargets(10, 0, 6);
		targets = board.getTargets();
		assertEquals(6, targets.size());

		// test targets and adjacency list of walkway in vertical corridor with
		// no doors in range
		testList = board.getAdjList(13, 0);
		assertTrue(testList.contains(board.getCellAt(12, 0)));
		assertTrue(testList.contains(board.getCellAt(14, 0)));
		assertEquals(2, testList.size());
		// 1 step
		board.calcTargets(13, 0, 1);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		// 2 steps
		board.calcTargets(13, 0, 2);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		// 3 steps
		board.calcTargets(13, 0, 3);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		// 4 steps
		board.calcTargets(13, 0, 4);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		// 5 steps
		board.calcTargets(13, 0, 5);
		targets = board.getTargets();
		assertEquals(6, targets.size());
		// 6 steps
		board.calcTargets(13, 0, 6);
		targets = board.getTargets();
		assertEquals(7, targets.size());

		// test targets and adjacent cells for walkway in T with one door
		// adjacent
		testList = board.getAdjList(21, 5);
		assertTrue(testList.contains(board.getCellAt(21, 4)));
		assertTrue(testList.contains(board.getCellAt(20, 5)));
		assertTrue(testList.contains(board.getCellAt(21, 6)));
		assertEquals(3, testList.size());
		// 1 step
		board.calcTargets(21, 5, 1);
		targets = board.getTargets();
		assertEquals(3, targets.size());
		// 2 steps
		board.calcTargets(21, 5, 2);
		targets = board.getTargets();
		assertEquals(3, targets.size());
		// 3 steps
		board.calcTargets(21, 5, 3);
		targets = board.getTargets();
		assertEquals(6, targets.size());
		// 4 steps
		board.calcTargets(21, 5, 4);
		targets = board.getTargets();
		assertEquals(6, targets.size());
		// 5 steps
		board.calcTargets(21, 5, 5);
		targets = board.getTargets();
		assertEquals(10, targets.size());
		// 6 steps
		board.calcTargets(21, 5, 6);
		targets = board.getTargets();
		assertEquals(10, targets.size());

		// test targets and adjacency cells of walkway in horizontal corridor
		// with one door in range
		testList = board.getAdjList(21, 9);
		assertTrue(testList.contains(board.getCellAt(21, 8)));
		assertTrue(testList.contains(board.getCellAt(21, 10)));
		assertEquals(2, testList.size());
		// 1 step
		board.calcTargets(21, 9, 1);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		// 2 steps
		board.calcTargets(21, 9, 2);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		// 3 steps
		board.calcTargets(21, 9, 3);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		// 4 steps
		board.calcTargets(21, 9, 4);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		// 5 steps
		board.calcTargets(21, 9, 5);
		targets = board.getTargets();
		assertEquals(6, targets.size());
		// 6 steps
		board.calcTargets(21, 9, 6);
		targets = board.getTargets();
		assertEquals(7, targets.size());

		// test targets of walkway in corner with no doors in range
		// 1 step
		board.calcTargets(21, 13, 1);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		// 2 steps
		board.calcTargets(21, 13, 2);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		// 3 steps
		board.calcTargets(21, 13, 3);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		// 4 steps
		board.calcTargets(21, 13, 4);
		targets = board.getTargets();
		assertEquals(5, targets.size());
		// 5 steps
		board.calcTargets(21, 13, 5);
		targets = board.getTargets();
		assertEquals(8, targets.size());
		// 6 steps
		board.calcTargets(21, 13, 6);
		targets = board.getTargets();
		assertEquals(11, targets.size());

		// test targets and adjacent cells of walkway next to one door and
		// closet
		testList = board.getAdjList(21, 20);
		assertTrue(testList.contains(board.getCellAt(21, 19)));
		assertTrue(testList.contains(board.getCellAt(21, 21)));
		assertEquals(2, testList.size());
		// 1 step
		board.calcTargets(21, 20, 1);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		// 2 steps
		board.calcTargets(21, 20, 2);
		targets = board.getTargets();
		assertEquals(2, targets.size());
		// 3 steps
		board.calcTargets(21, 20, 3);
		targets = board.getTargets();
		assertEquals(3, targets.size());
		// 4 steps
		board.calcTargets(21, 20, 4);
		targets = board.getTargets();
		assertEquals(3, targets.size());
		// 5 steps
		board.calcTargets(21, 20, 5);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		// 6 steps
		board.calcTargets(21, 20, 6);
		targets = board.getTargets();
		assertEquals(4, targets.size());
	}
}