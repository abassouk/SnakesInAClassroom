package gr.auth.meng.isag.android.snakes;

import gr.auth.meng.isag.android.snakes.api.MultiComponent;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import gr.auth.meng.isag.android.snakes.api.GameComponent;
import gr.auth.meng.isag.android.snakes.impl.BoardBuilder;
import gr.auth.meng.isag.android.snakes.impl.HalfTouchController;
import gr.auth.meng.isag.android.snakes.impl.SnakeBehaviour;
import gr.auth.meng.isag.android.snakes.impl.SnakePainterImpl;
import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameActivity extends Activity implements Callback, Runnable {
	private SurfaceView surfaceView;

	private SurfaceHolder holder;

	private SnakeBoard board;

	private GameComponent<SnakeBoard> components;

	private boolean surface;

	private boolean running;

	private Thread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		board = new BoardBuilder().outlineWalls().addFruit().build();
		components = new MultiComponent<SnakeBoard>(new HalfTouchController(),
				new SnakeBehaviour(), new SnakePainterImpl());

		surfaceView = new SurfaceView(this);
		holder = surfaceView.getHolder();
		holder.addCallback(this);
		setContentView(surfaceView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.running = false;
		stopThread();
		components.teardown();
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.running = true;
		components.setup(board, surfaceView);
		possiblyStartThread();
	}

	private void possiblyStartThread() {
		if (running == false || surface == false)
			return;
		if (thread == null) {
			this.thread = new Thread(this, "Game Thread");
			this.thread.start();
		}
	}

	private void stopThread() {
		if (this.thread == null)
			return;
		this.thread.interrupt();
	}

	private void paintBoard(SurfaceHolder holder) {
		Canvas c = holder.lockCanvas();
		try {
			components.doDraw(c);
		} finally {
			holder.unlockCanvasAndPost(c);
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		paintBoard(holder);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		this.surface = true;
		possiblyStartThread();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		this.surface = false;
		stopThread();
	}

	public void run() {
		try {
			while (true) {
				if (components.onGameTick())
					break;
				paintBoard(holder);
				Thread.sleep(300);
			}
		} catch (InterruptedException e) {
		} finally {
			this.thread = null;
		}
	}
}
