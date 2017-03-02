package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class TQBL_FileInitTests {

	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;
	
	private static Board board;
	@BeforeClass
	public static void setUp()  {
		board = Board.getInstance();
		board.setConfigFiles("Clue Layout TQBL.csv", "Key.txt");		
		board.initialize();
	}

	@Test
	public void testRooms() {
		// Get the map of initial => room 
		Map<Character, String> legend = board.getLegend();
		// Ensure we read the correct number of rooms
		assertEquals(LEGEND_SIZE, legend.size());
		// To ensure data is correctly loaded, test retrieving a few rooms 
		// from the hash, including the first and last in the file and a few others
		assertEquals("Movie Theatre", legend.get('M'));
		assertEquals("BedRoom", legend.get('B'));
		assertEquals("Kitchen", legend.get('K'));
		assertEquals("Bar", legend.get('A'));
		assertEquals("Study Room", legend.get('S'));
		assertEquals("BathRoom", legend.get('T'));
		assertEquals("Computer Room", legend.get('C'));
		assertEquals("Sun Room", legend.get('H'));
		assertEquals("Observatory", legend.get('O'));
	}
	
	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	
	@Test
	public void FourDoorDirections() {
		BoardCell room = board.getCellAt(8, 4);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getCellAt(4, 8);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
		room = board.getCellAt(12, 11);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		room = board.getCellAt(10, 8);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());
		// Test that room pieces that aren't doors know it
		room = board.getCellAt(0, 0);
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCellAt(5, 5);
		assertFalse(cell.isDoorway());		

	}
	
	@Test
	public void testNumberOfDoorways() 
	{
		int numDoors = 0;
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(13, numDoors);
	}
	
	@Test
	public void testRoomInitials() {
		// Test first cell in room
		assertEquals('O', board.getCellAt(0, 0).getInitial());
		assertEquals('K', board.getCellAt(0, 7).getInitial());
		assertEquals('M', board.getCellAt(0, 17).getInitial());
		// Test last cell in room
		assertEquals('T', board.getCellAt(7, 2).getInitial());
		assertEquals('A', board.getCellAt(9, 11).getInitial());
		// Test a walkway
		assertEquals('B', board.getCellAt(10, 6).getInitial());
		// Test the closet
		assertEquals('X', board.getCellAt(11,20).getInitial());
		assertEquals('H', board.getCellAt(16,6).getInitial());
		assertEquals('S', board.getCellAt(16,17).getInitial());
		assertEquals('C', board.getCellAt(17,0).getInitial());
		assertEquals('W', board.getCellAt(0,5).getInitial());
	}

}
