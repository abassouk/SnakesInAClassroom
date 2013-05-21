package gr.auth.meng.isag.android.snakes;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameActivity extends Activity implements Callback {
	public static final int GRID_SIZE = 30;

	public static final byte ID_EMPTY = 0;
	public static final byte ID_WALL = 1;
	public static final byte ID_FRUIT = 2;
	public static final byte ID_SNAKE = 3;

	private static int[][] colors = { { 0, 0, 0 }, { 128, 128, 128 },
			{ 255, 128, 0 }, { 0, 255, 64 } };

	// //////////////////////////////////

	private int direction = 0;

	private byte[][] board = new byte[GRID_SIZE][GRID_SIZE];

	private List<IntPair> snakeCoordinates = new LinkedList<IntPair>();

	private int growth = 0;

	private SurfaceView surfaceView;

	private SurfaceHolder holder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		initialize();

		surfaceView = new SurfaceView(this);
		holder = surfaceView.getHolder();
		holder.addCallback(this);
		setContentView(surfaceView);
	}

	private void initialize() {
		fillBoard(ID_EMPTY, 1, 1, GRID_SIZE - 2, GRID_SIZE - 2);
		fillBoard(ID_WALL, 0, 0, 0, GRID_SIZE - 1);
		fillBoard(ID_WALL, 0, 0, GRID_SIZE - 1, 0);
		fillBoard(ID_WALL, GRID_SIZE - 1, 0, GRID_SIZE - 1, GRID_SIZE - 1);
		fillBoard(ID_WALL, 0, GRID_SIZE - 1, GRID_SIZE - 1, GRID_SIZE - 1);
		addFruit();
		snakeCoordinates.clear();
		for (int i = -1; i <= 1; i++) {
			snakeCoordinates.add(new IntPair(GRID_SIZE / 2 + i, GRID_SIZE / 2));
		}
		fillBoard(ID_SNAKE, GRID_SIZE / 2 - 1, GRID_SIZE / 2 ,
				GRID_SIZE / 2+1, GRID_SIZE / 2);
	}

	private void fillBoard(byte id, int x1, int y1, int x2, int y2) {
		for (int y = y1; y <= y2; y++) {
			for (int x = x1; x <= x2; x++) {
				board[y][x] = id;
			}
		}
	}

	private void addFruit() {
		int fx, fy;
		do {
			fx = (int) (Math.random() * GRID_SIZE);
			fy = (int) (Math.random() * GRID_SIZE);
		} while (board[fy][fx] != ID_EMPTY);
		board[fy][fx] = ID_FRUIT;
	}

	private void paintBoard(Canvas c) {
		int w = c.getWidth();
		int h = c.getHeight();

		float size = (w > h ? h : w) / (float) GRID_SIZE;
		w -= GRID_SIZE * size;
		h -= GRID_SIZE * size;
		w /= 2;
		h /= 2;

		c.drawRGB(0, 0, 0);
		Paint p = new Paint();
		for (int y = 0; y < GRID_SIZE; y++) {
			for (int x = 0; x < GRID_SIZE; x++) {
				byte val = board[y][x];
				if (val == ID_EMPTY)
					continue;
				p.setARGB(128, colors[val][0], colors[val][1], colors[val][2]);
				float px = w + x * size;
				float py = h + y * size;

				c.drawRect(px, py, px + size - 1, py + size - 1, p);
			}
		}
	}
	
	private void paintBoard(SurfaceHolder holder){
		Canvas c = holder.lockCanvas();
		try {
			paintBoard(c);
		} finally {
			holder.unlockCanvasAndPost(c);
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		paintBoard(holder);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		paintBoard(holder);
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	}
}
