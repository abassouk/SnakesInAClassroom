package gr.auth.meng.isag.android.snakes.impl;

import gr.auth.meng.isag.android.snakes.IntPair;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import gr.auth.meng.isag.android.snakes.api.Tile;

import java.util.LinkedList;
import java.util.List;

public class SnakeBoardImpl implements SnakeBoard {
	private static final int BOARD_WIDTH = 30;
	private static final int BOARD_HEIGHT = 30;

	private int direction = DIRECTION_UP;

	private SnakeTile[][] board = new SnakeTile[BOARD_HEIGHT][BOARD_WIDTH];

	private LinkedList<IntPair> snakeCoordinates = new LinkedList<IntPair>();

	public int getWidth() {
		return BOARD_WIDTH;
	}

	public int getHeight() {
		return BOARD_HEIGHT;
	}

	public void setTile(int x, int y, Tile tile) {
		board[y][x] = (SnakeTile) tile;
	}

	public SnakeTile getTile(int x, int y) {
		return board[y][x];
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		if (direction < DIRECTION_MIN || direction > DIRECTION_MAX)
			throw new IllegalArgumentException("Invalid direction value");
		this.direction = direction;
	}

	public List<IntPair> getSnakePositions() {
		return snakeCoordinates;
	}

	// //
	// I have a feeling these should go to SnakeBehaniour...

	public void addNextHead(IntPair xy) {
		snakeCoordinates.add(0, xy);
		setTile(xy.getX(), xy.getY(), SnakeTile.TILE_SNAKE);
	}

	public void removeTail() {
		IntPair coordinates = snakeCoordinates.removeLast();
		setTile(coordinates.getX(), coordinates.getY(), SnakeTile.TILE_EMPTY);
	}

	/*
	 * (non-Javadoc)
	 * @see gr.auth.meng.isag.android.snakes.api.SnakeBoard#addFruit()
	 */
	public void addFruit() {
		int fx, fy;
		do {
			fx = (int) (Math.random() * getWidth());
			fy = (int) (Math.random() * getHeight());
		} while (getTile(fx, fy) != SnakeTile.TILE_EMPTY);
		setTile(fx, fy, SnakeTile.TILE_FRUIT);
	}
}
