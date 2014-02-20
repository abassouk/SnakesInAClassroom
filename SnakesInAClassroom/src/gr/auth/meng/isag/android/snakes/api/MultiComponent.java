package gr.auth.meng.isag.android.snakes.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * A component that is constructed by aggregation of other components. Will emit
 * the same {@link GameComponent} methods to all subcomponents.
 * 
 * @author abas
 * 
 * @param <Board>
 */
public class MultiComponent<Board extends GameBoard> implements
		GameComponent<Board> {
	private List<GameComponent<Board>> components = new ArrayList<GameComponent<Board>>();

	public MultiComponent() {
	}

	public MultiComponent(GameComponent<Board>... components) {
		this.components.addAll(Arrays.asList(components));
	}

	public MultiComponent<Board> add(GameComponent<Board> component) {
		components.add(component);
		return this;
	}

	public void remove(GameComponent<Board> component) {
		components.remove(component);
	}

	public void setup(Board board, SurfaceView surface) {
		for (GameComponent<Board> component : components) {
			component.setup(board, surface);
		}
	}

	public void teardown() {
		for (GameComponent<Board> component : components) {
			component.teardown();
		}
	}

	public void doDraw(Canvas c) {
		for (GameComponent<Board> component : components) {
			component.doDraw(c);
		}
	}

	public boolean onGameTick() {
		boolean stop = false;
		for (GameComponent<Board> component : components) {
			stop |= component.onGameTick();
		}
		return stop;
	}
}
