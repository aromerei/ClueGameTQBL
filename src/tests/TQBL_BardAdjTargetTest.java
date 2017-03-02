package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BoardCell;

public class TQBL_BardAdjTargetTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		lol hi ben
	}

	@Test
	public void test() {
		fail("Not yet implemented");
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
