package gr.auth.meng.isag.android.snakes.impl;

import gr.auth.meng.isag.android.snakes.IntPair;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard.SnakeTile;
import gr.auth.meng.isag.android.snakes.api.Tile;

public class BoardBuilder {
	private SnakeBoardImpl board;

	public BoardBuilder() {
		board = new SnakeBoardImpl();
		initialize();
	}

	public BoardBuilder fill(Tile id) {
		drawRect(id, 0, 0, board.getWidth() - 1, board.getHeight() - 1);
		return this;
	}

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
		board.getSnakePositions().clear();
		for (int i = -1; i <= 1; i++) {
			board.getSnakePositions()
					.add(new IntPair(board.getWidth() / 2 + i,
							board.getWidth() / 2));
		}
		drawRect(SnakeTile.TILE_SNAKE, board.getWidth() / 2 - 1,
				board.getWidth() / 2, board.getWidth() / 2 + 1,
				board.getWidth() / 2);
	}

	public BoardBuilder drawRect(Tile id, int x1, int y1, int x2, int y2) {
		for (int y = y1; y <= y2; y++) {
			for (int x = x1; x <= x2; x++) {
				board.setTile(x, y, id);
			}
		}
		return this;
	}

	public BoardBuilder drawWall(int x1, int y1, int x2, int y2) {
		drawRect(SnakeBoard.SnakeTile.TILE_WALL, x1, y1, x2, y2);
		return this;
	}

	public BoardBuilder addFruit() {
		int fx, fy;
		do {
			fx = (int) (Math.random() * board.getWidth());
			fy = (int) (Math.random() * board.getHeight());
		} while (board.getTile(fx, fy) != SnakeTile.TILE_EMPTY);
		board.setTile(fx, fy, SnakeTile.TILE_FRUIT);
		return this;
	}

	public SnakeBoard build() {
		return board;
	}
}
