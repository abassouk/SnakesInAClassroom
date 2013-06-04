package gr.auth.meng.isag.android.snakes.api;

import gr.auth.meng.isag.android.snakes.IntPair;

import java.util.List;

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

	public int getDirection();

	public void setDirection(int direction);

	public List<IntPair> getSnakePositions();

	SnakeTile getTile(int x, int y);
	
	////
	
	public void addNextHead(IntPair xy);

	public void removeTail();

	public void addFruit();
}
