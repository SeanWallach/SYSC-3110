//Class written by Ashton and Andrew

/**
 * Class for game piece Mushroom
 * @author (for JavaDoc comments) Nicholas
 */
public class Mushroom extends GamePiece {
	
	/**
     * Mushroom constructor. A mushroom is a piece that
     * cannot move, so it does not need to extend
     * MovableGamePiece.
     * 
     * @param s Name of game piece
     * @param xpos X-coordinate of Mushroom
     * @param ypos Y-coordinate of Mushroom
     */
	public Mushroom(String s, int xpos, int ypos) {
		super(xpos, ypos, 1, s);
	}
	
	/**
	 * Return a single char to represent that the piece is a Mushroom.
	 */
	@Override
	public char getAcronym() {
		return 'M';
	}

	@Override
	protected boolean canMove() {
		// TODO Auto-generated method stub
		return false;
	}
}