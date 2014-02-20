package gr.auth.meng.isag.android.snakes;

import gr.auth.meng.isag.android.snakes.api.MultiComponent;
import gr.auth.meng.isag.android.snakes.api.SnakeBoard;
import gr.auth.meng.isag.android.snakes.impl.BoardBuilder;
import gr.auth.meng.isag.android.snakes.impl.HalfTouchController;
import gr.auth.meng.isag.android.snakes.impl.SnakeBehaviour;
import gr.auth.meng.isag.android.snakes.impl.SnakePainterImpl;
import android.os.Bundle;

public class SnakeGameActivity extends GameActivity<SnakeBoard> {
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		board = new BoardBuilder().outlineWalls().addFruit().build();
		components = new MultiComponent<SnakeBoard>(new HalfTouchController(),
				new SnakeBehaviour(), new SnakePainterImpl());
	}
}
