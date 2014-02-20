package gr.auth.meng.isag.android.snakes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class TitleActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_title, menu);
		return true;
	}

	public void startGame(View w) {
		Intent intent = new Intent(this, SnakeGameActivity.class);
		startActivity(intent);
	}
}
