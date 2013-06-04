package gr.auth.meng.isag.android.snakes.impl;

import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import android.view.MotionEvent;
import android.view.View;

public class HalfTouchController extends BaseSnakeController {
	protected int nextTurn = 0;

	@Override
	public boolean onGameTick() {
		if (nextTurn == 0)
			return false;

		int direction = board.getDirection();
		direction += nextTurn + (SnakeBoard.DIRECTION_MAX + 1);
		direction %= (SnakeBoard.DIRECTION_MAX + 1);
		board.setDirection(direction);

		nextTurn = 0;
		
		
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return false;

		if (event.getX() <= v.getWidth() / 2)
			nextTurn = -1;
		else
			nextTurn = 1;

		return true;
	}
}
