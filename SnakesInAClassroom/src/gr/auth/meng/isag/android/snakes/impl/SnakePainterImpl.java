package gr.auth.meng.isag.android.snakes.impl;

import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard.SnakeTile;
import gr.auth.meng.isag.android.snakes.api.GameComponent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

public class SnakePainterImpl implements GameComponent<SnakeBoard> {
	private static int[][] colors = { { 0, 0, 0 }, { 128, 128, 128 },
			{ 255, 128, 0 }, { 0, 255, 64 } };
	private SnakeBoard board;

	public void doDraw(Canvas canvas) {
		int w = canvas.getWidth();
		int h = canvas.getHeight();

		float sizeX = w / (float) board.getWidth();
		float sizeY = h / (float) board.getHeight();

		float size = sizeX > sizeY ? sizeY : sizeX;
		w -= board.getWidth() * size;
		h -= board.getHeight() * size;
		w /= 2;
		h /= 2;

		canvas.drawRGB(0, 0, 0);
		Paint p = new Paint();
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				SnakeTile tile = board.getTile(x, y);
				if (tile == SnakeTile.TILE_EMPTY)
					continue;
				int val = tile.ordinal();
				p.setARGB(128, colors[val][0], colors[val][1], colors[val][2]);
				float px = w + x * size;
				float py = h + y * size;

				canvas.drawRect(px, py, px + size - 1, py + size - 1, p);
			}
		}
	}

	public void setup(SnakeBoard board, SurfaceView surface) {
		this.board = board;
	}

	public void teardown() {
	}

	public boolean onGameTick() {
		return false;
	}
}
