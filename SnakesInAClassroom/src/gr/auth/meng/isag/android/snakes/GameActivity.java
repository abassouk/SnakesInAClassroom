package gr.auth.meng.isag.android.snakes;

import gr.auth.meng.isag.android.snakes.api.GameBoard;
import gr.auth.meng.isag.android.snakes.api.GameComponent;
import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public abstract class GameActivity<Board extends GameBoard> extends Activity implements
		Callback, Runnable {
	protected SurfaceView surfaceView;

	protected SurfaceHolder holder;

	protected Board board;

	protected GameComponent<Board> components;

	protected boolean surface;

	protected boolean running;

	protected Thread thread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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
		maybeStartThread();
	}

	private void maybeStartThread() {
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

	protected void paintBoard(SurfaceHolder holder) {
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
		maybeStartThread();
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
