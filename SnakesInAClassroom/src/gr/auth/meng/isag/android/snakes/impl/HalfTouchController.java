package gr.auth.meng.isag.android.snakes.impl;

import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import android.view.MotionEvent;
import android.view.View;

/**
 * Sample controller that turns the snake left or right based on whether th user
 * touched the left or right part of the screen.
 * <p>
 * Note: This component should be active *before* the snake moves, in order to
 * lessen lag between touch and rotation.
 * 
 * @author abas
 */
public class HalfTouchController extends BaseSnakeController {
	protected int nextTurn = 0;

	/*
	 * On the game tick, rotate the direction appropriately.
	 * 
	 * @see
	 * gr.auth.meng.isag.android.snakes.impl.BaseSnakeController#onGameTick()
	 */
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

	/*
	 * When the user touches the screen, determine if it's the left or right
	 * part of the screen, and store that for later.
	 * 
	 * @see
	 * gr.auth.meng.isag.android.snakes.impl.BaseSnakeController#onTouch(View
	 * ,MotionEvent)
	 */
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
