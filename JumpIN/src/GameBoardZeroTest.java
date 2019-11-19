import static org.junit.Assert.*;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import java.io.*;
import org.junit.*;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;


/**
 * 
 * @author Nicholas Porter
 * Testing game board 0
 *
 */
public class GameBoardZeroTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private Game jumpin = null;
	private InfoBook testBookZero = null;
	private GameBoard testGameBoard = null;
	private GamePiece testPiece = null;
	
	@Rule
	public final TextFromStandardInputStream input
		= emptyStandardInputStream();
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		testBookZero = new InfoBook(0);
		input.provideLines("0");
		jumpin = new Game(0);
		testGameBoard = new GameBoard(testBookZero.getPieces());
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	/**
	 * Perform undo action before any moves
	 * have been made. Check if pieces match up with
	 * InfoBook 0 to confirm that undo has not caused
	 * any unexpected changes
	 */
	@Test
	public void testUndoInvalid() {
		testGameBoard.undo();
		testGameBoard = new GameBoard(testBookZero.getPieces());
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard));
	}
	
	/**
	 * Perform undo action after move Bunny1
	 * first check to make sure the board actually changed
	 * then perform undo and check if bunny is in the proper position
	 */
	@Test
	public void testUndo() {
		testGameBoard.movePiece(testBookZero.getPieces().get(0).getX(), testBookZero.getPieces().get(0).getY(), 2);
		assertEquals(false, jumpin.getGameBoard().equals(testGameBoard)); // check that piece was moved from original position
		testGameBoard.undo();
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard));
	}
	
	/**
	 * Perform undo action after moving Bunny1
	 * check to make sure undo was performed
	 * perform redo
	 * check if Bunny1 is in the expected position
	 */
	@Test
	public void testRedo() {
		testGameBoard.movePiece(testBookZero.getPieces().get(0).getX(), testBookZero.getPieces().get(0).getY(), 2);
		testGameBoard.undo();
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard)); // check that undo was performed (piece should be back in original position)
		testGameBoard.redo();
		assertEquals(3, testBookZero.getPieces().get(0).getX());
		assertEquals(2, testBookZero.getPieces().get(0).getY());
		
	}
	
	/**
	 * Testing that the proper puzzle
	 * has been selected by comparing 
	 * current gameboard to InfoBook0
	 */
	@Test
	public void testGetPieces() {
		assertEquals(true, testBookZero.getPieces().equals(jumpin.getPieces()));
	}
	
	/**
	 * Test that the game is running
	 */
	@Test
	public void testGetRunning() {
		assertEquals(true, jumpin.getRunning());
	}
	
	/**
	 * Test the gameboard has been 
	 * correctly set up by comparing
	 * to expected gameboard
	 */
	@Test
	public void TestGetGameBoard() {
		assertEquals(true, jumpin.getGameBoard().equals(testGameBoard));
	}

}