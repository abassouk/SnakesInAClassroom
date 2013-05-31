package gr.auth.meng.isag.android.snakes.impl;

import gr.auth.meng.isag.android.snakes.IntPair;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import gr.auth.meng.isag.android.snakes.api.Tile;

import java.util.LinkedList;
import java.util.List;

public class SnakeBoardImpl implements SnakeBoard {
	private static final int BOARD_WIDTH = 30;
	private static final int BOARD_HEIGHT = 30;

	private static IntPair[] directions = new IntPair[] { new IntPair(0, -1),
			new IntPair(1, 0), new IntPair(0, 1), new IntPair(-1, 0) };

	private int direction = 0;

	private SnakeTile[][] board = new SnakeTile[BOARD_HEIGHT][BOARD_WIDTH];

	private List<IntPair> snakeCoordinates = new LinkedList<IntPair>();

	private int growth = 0;

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

	public boolean moveSnake() {
		IntPair head = snakeCoordinates.get(0);
		IntPair dir = directions[direction];
		int x = head.getX() + dir.getX();
		int y = head.getY() + dir.getY();
		// check bounds, stay within bounds
		if (x < 0)
			x = getWidth() - 1;
		else if (x >= getWidth())
			x = 0;
		if (y < 0)
			y = getHeight() - 1;
		else if (y >= getHeight())
			y = 0;
		SnakeTile tile = getTile(x, y);
		// check for game over;
		if (tile == SnakeTile.TILE_SNAKE || tile == SnakeTile.TILE_WALL) {
			return false;
		}
		// add head;
		snakeCoordinates.add(0, new IntPair(x, y));
		setTile(x, y, SnakeTile.TILE_SNAKE);
		// fruit logic;
		if (tile == SnakeTile.TILE_FRUIT) {
			growth += 3;
			addFruit();
		}
		if (growth > 0) {
			growth--;
		} else {
			IntPair pair = snakeCoordinates.remove(snakeCoordinates.size() - 1);
			setTile(pair.getX(), pair.getY(), SnakeTile.TILE_EMPTY);
		}
		return true;
	}

	private void addFruit() {
		int fx, fy;
		do {
			fx = (int) (Math.random() * getWidth());
			fy = (int) (Math.random() * getHeight());
		} while (getTile(fx, fy) != SnakeTile.TILE_EMPTY);
		setTile(fx, fy, SnakeTile.TILE_FRUIT);
	}
}
