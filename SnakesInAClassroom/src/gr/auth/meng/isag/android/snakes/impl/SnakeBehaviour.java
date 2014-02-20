package gr.auth.meng.isag.android.snakes.impl;

import gr.auth.meng.isag.android.snakes.IntPair;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard.SnakeTile;
import gr.auth.meng.isag.android.snakes.api.GameComponent;
import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * Component that operates on the snake on the game board, and handles
 * <ul>
 * <li>Moving the snake
 * <li>Eating fruit
 * <li>Growing after the fruit has been eaten
 * <li>collision detection if hitting a wall.
 * </ul>
 * 
 * @author abas
 */
public class SnakeBehaviour implements GameComponent<SnakeBoard> {
	private SnakeBoard board;

	/* the number of tiles that the snake's tail should be extended */
	private int growth = 0;

	/*
	 * the directions - I think I may need to refactor this into the directions
	 * themselves...
	 */
	private static IntPair[] directions = new IntPair[] { new IntPair(0, -1),
			new IntPair(1, 0), new IntPair(0, 1), new IntPair(-1, 0) };

	public boolean onGameTick() {
		IntPair head = board.getSnakePositions().get(0);
		IntPair dir = directions[board.getDirection()];
		int x = head.getX() + dir.getX();
		int y = head.getY() + dir.getY();
		// check bounds, stay within bounds
		if (x < 0)
			x = board.getWidth() - 1;
		else if (x >= board.getWidth())
			x = 0;
		if (y < 0)
			y = board.getHeight() - 1;
		else if (y >= board.getHeight())
			y = 0;
		SnakeTile tile = board.getTile(x, y);
		// check for game over if a wall or self has been hit.
		if (tile == SnakeTile.TILE_SNAKE || tile == SnakeTile.TILE_WALL) {
			return true;
		}
		// add head;
		board.addNextHead(new IntPair(x, y));
		// fruit logic; if we just ate a fruit, we will grow for 3 game ticks
		// more.
		if (tile == SnakeTile.TILE_FRUIT) {
			growth += 3;
			// don't forget to replace the fruit.
			board.addFruit();
		}
		// if we're still growing, don't trim the tail of the snake.
		if (growth > 0) {
			growth--;
		} else {
			board.removeTail();
		}
		return false;
	}

	// /////

	public void setup(SnakeBoard board, SurfaceView surface) {
		this.board = board;
		this.growth = 0;
	}

	public void teardown() {
		// nothing to do;
	}

	public void doDraw(Canvas c) {
		// nothing to do;
	}

}
