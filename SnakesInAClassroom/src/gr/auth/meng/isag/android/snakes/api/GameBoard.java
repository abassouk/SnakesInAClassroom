package gr.auth.meng.isag.android.snakes.api;

/**
 * A Game Board is a grid of slots, each one having a kind of tile.
 * 
 * @author abas
 */
public interface GameBoard {
	/**
	 * Get the width of the board
	 * 
	 * @return the width of the board
	 */
	int getWidth();

	/**
	 * Get the height of the board.
	 * 
	 * @return the height of the board.
	 */
	int getHeight();

	/**
	 * Get the tile at a point in the grid.
	 * 
	 * @param x
	 *            the x coordinate, must be within [0,getWidth())
	 * @param y
	 *            the x coordinate, must be within [0,getHeight())
	 * @return a non-null Tile instance that exists in these coordinates.
	 */
	Tile getTile(int x, int y);

	/**
	 * Update the game board with a new tile at a specific set of coordinates.
	 * 
	 * @param x
	 *            the x coordinate, must be within [0,getWidth())
	 * @param y
	 *            the x coordinate, must be within [0,getHeight())
	 * @param tile
	 *            the tile to set at these coordinates.
	 */
	void setTile(int x, int y, Tile tile);
}
