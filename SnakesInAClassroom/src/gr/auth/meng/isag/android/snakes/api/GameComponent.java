package gr.auth.meng.isag.android.snakes.api;

import android.graphics.Canvas;
import android.view.SurfaceView;

public interface GameComponent<Board extends GameBoard> {
	public void setup(Board board, SurfaceView surface);

	public void teardown();

	public void doDraw(Canvas c);
	
	public boolean onGameTick();
}