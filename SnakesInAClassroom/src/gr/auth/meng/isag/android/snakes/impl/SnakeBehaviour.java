package gr.auth.meng.isag.android.snakes.impl;

import gr.auth.meng.isag.android.snakes.IntPair;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard.SnakeTile;
import gr.auth.meng.isag.android.snakes.api.GameComponent;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class SnakeBehaviour implements GameComponent<SnakeBoard> {
	private SnakeBoard board;

	private int growth = 0;

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
		// check for game over;
		if (tile == SnakeTile.TILE_SNAKE || tile == SnakeTile.TILE_WALL) {
			return true;
		}
		// add head;
		board.addNextHead(new IntPair(x, y));
		// fruit logic;
		if (tile == SnakeTile.TILE_FRUIT) {
			growth += 3;
			board.addFruit();
		}
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
		this.growth=0;
	}

	public void teardown() {
		// nothing to do;
	}

	public void doDraw(Canvas c) {
		// nothing to do;
	}

}
