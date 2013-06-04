package gr.auth.meng.isag.android.snakes.impl;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import gr.auth.meng.isag.android.snakes.api.GameComponent;

public abstract class BaseSnakeController implements GameComponent<SnakeBoard>,
		OnTouchListener {
	protected SurfaceView surface;
	protected SnakeBoard board;

	public void setup(SnakeBoard board, SurfaceView surface) {
		surface.setOnTouchListener(this);
		this.surface = surface;
		this.board = board;
	}

	public void teardown() {
		surface.setOnTouchListener(null);
	}

	public void doDraw(Canvas c) {
	}

	public boolean onGameTick() {
		return false;
	}

	public abstract boolean onTouch(View v, MotionEvent event);
}
