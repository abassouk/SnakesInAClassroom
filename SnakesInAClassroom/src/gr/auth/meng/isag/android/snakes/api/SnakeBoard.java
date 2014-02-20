package gr.auth.meng.isag.android.snakes.api;

import gr.auth.meng.isag.android.snakes.IntPair;

import java.util.List;

/**
 * A Game Board for playing Snake. Defines the tiles used, directions and
 * methods to manipulate the snake.
 * 
 * @author abas
 */
public interface SnakeBoard extends GameBoard {
	public static enum SnakeTile implements Tile {
		TILE_EMPTY, TILE_WALL, TILE_FRUIT, TILE_SNAKE
	}

	public static final int DIRECTION_UP = 0;
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_DOWN = 2;
	public static final int DIRECTION_LEFT = 3;

	public static final int DIRECTION_MIN = DIRECTION_UP;
	public static final int DIRECTION_MAX = DIRECTION_LEFT;

	/**
	 * The Current direction of the snake.
	 * 
	 * @return one of DIRECTION_(UP|DOWN|LEFT|RIGHT)
	 */
	public int getDirection();

	/**
	 * Set the current direction of the snake.
	 * 
	 * @param direction
	 */
	public void setDirection(int direction);

	/**
	 * Get the list with the tiles that the snake occupies
	 * 
	 * @return
	 */
	public List<IntPair> getSnakePositions();

	/**
	 * Get the tile at the specified coordinates. Overriden to return SnakeTiles
	 * 
	 * @see GameBoard#getTile(int, int)
	 */
	public SnakeTile getTile(int x, int y);

	// //

	/**
	 * Snake: add a next head.
	 * 
	 * @param xy
	 */
	public void addNextHead(IntPair xy);

	/**
	 * Remove 1 tile from the tail of the snake.
	 */
	public void removeTail();

	/**
	 * Add a fruit to the game board.
	 */
	public void addFruit();
}
