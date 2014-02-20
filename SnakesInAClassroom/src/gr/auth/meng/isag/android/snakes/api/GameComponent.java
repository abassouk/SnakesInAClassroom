package gr.auth.meng.isag.android.snakes.api;

import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * A GameComponent is a component of a game, be it UI such as touch controls, UI
 * such as board painters, behavior such as next tick calculation, timers, or
 * other components.
 * 
 * @author abas
 * 
 * @param <Board>
 *            The board type that this game component manipulates or presents.
 */
public interface GameComponent<Board extends GameBoard> {
	/**
	 * Called when the board is being set up, to perform any startup tasks this
	 * component has. Callbacks can be registered here.
	 * 
	 * @param board
	 *            the game board that is being set up.
	 * @param surface
	 *            the {@link SurfaceView} that will render this game board.
	 */
	public void setup(Board board, SurfaceView surface);

	/**
	 * Perform any clean up when the game ends or is interrupted.
	 */
	public void teardown();

	/**
	 * Callback called when the canvas needs redrawing.
	 * 
	 * @param c
	 *            the canvas that will render the game board.
	 */
	public void doDraw(Canvas c);

	/**
	 * Perform any state changes and internal updates of the game board, a time
	 * slot has passed, and determine if the game should end right now. 
	 * 
	 * @return true if the game has ended.
	 */
	public boolean onGameTick();
}