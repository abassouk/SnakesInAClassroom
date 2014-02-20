package gr.auth.meng.isag.android.snakes.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gr.auth.meng.isag.android.snakes.IntPair;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard.SnakeTile;
import gr.auth.meng.isag.android.snakes.api.Tile;

/**
 * Utility class to generate a basic level of Snake. Uses the Builder pattern.
 * Example use:
 * <code>SnakeBoard sb = new BoardBuilder().outlineWalls().addFruit().build();</code>
 * 
 * <p>
 * A lot of the functionality present here could be reused for other kind of
 * boards, but the generics needed to do that are pretty hairy - pass for now.
 * 
 * @author abas
 */
public class BoardBuilder {
	private SnakeBoardImpl board;

	/**
	 * Start with a blank board and an upwards-facing snake.
	 */
	public BoardBuilder() {
		board = new SnakeBoardImpl();
		initialize();
	}

	/**
	 * Fill the board with the given tile.
	 * 
	 * @param id
	 *            the tile that will fill the whole board game.
	 * @return this builder, allowing further construction.
	 */
	public BoardBuilder fill(Tile id) {
		drawRect(id, 0, 0, board.getWidth() - 1, board.getHeight() - 1);
		return this;
	}

	/**
	 * Set impenetrable walls all around the game area.
	 * 
	 * @return this builder, allowing further construction.
	 */
	public BoardBuilder outlineWalls() {
		drawWall(0, 0, 0, board.getHeight() - 1);
		drawWall(0, 0, board.getWidth() - 1, 0);
		drawWall(board.getWidth() - 1, 0, board.getWidth() - 1,
				board.getHeight() - 1);
		drawWall(0, board.getHeight() - 1, board.getWidth() - 1,
				board.getHeight() - 1);
		return this;
	}

	private void initialize() {
		fill(SnakeTile.TILE_EMPTY);
		List<IntPair> coords = new ArrayList<IntPair>();
		for (int i = -1; i <= 1; i++) {
			coords.add(new IntPair(board.getWidth() / 2 + i,
					board.getWidth() / 2));
		}
		setSnake(SnakeBoard.DIRECTION_UP, coords);
	}

	/**
	 * Initialize location and direction of the snake.
	 * 
	 * @param direction
	 * @param coordinates
	 * @return this builder, allowing further construction.
	 */
	public BoardBuilder setSnake(int direction, Collection<IntPair> coordinates) {
		board.setDirection(direction);
		List<IntPair> p = board.getSnakePositions();
		p.clear();
		p.addAll(coordinates);
		return this;
	}

	/**
	 * Fill a rectangle of the game board with a given tile.
	 * 
	 * @param id
	 *            the tile with which to fill the
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return this builder, allowing further construction.
	 */
	public BoardBuilder drawRect(Tile id, int x1, int y1, int x2, int y2) {
		for (int y = y1; y <= y2; y++) {
			for (int x = x1; x <= x2; x++) {
				board.setTile(x, y, id);
			}
		}
		return this;
	}

	/**
	 * Set an impenetrable wall on the board.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return this builder, allowing further construction.
	 */
	public BoardBuilder drawWall(int x1, int y1, int x2, int y2) {
		drawRect(SnakeBoard.SnakeTile.TILE_WALL, x1, y1, x2, y2);
		return this;
	}

	/**
	 * Add a fruit on the game board.
	 * 
	 * @return this builder, allowing further construction.
	 */
	public BoardBuilder addFruit() {
		int fx, fy;
		do {
			fx = (int) (Math.random() * board.getWidth());
			fy = (int) (Math.random() * board.getHeight());
		} while (board.getTile(fx, fy) != SnakeTile.TILE_EMPTY);
		board.setTile(fx, fy, SnakeTile.TILE_FRUIT);
		return this;
	}

	/**
	 * Complete construction and return a ready SnakeBoard.
	 * 
	 * @return the completed snake board.
	 */
	public SnakeBoard build() {
		for (IntPair ip : board.getSnakePositions()) {
			board.setTile(ip.getX(), ip.getY(), SnakeTile.TILE_SNAKE);
		}
		return board;
	}
}
