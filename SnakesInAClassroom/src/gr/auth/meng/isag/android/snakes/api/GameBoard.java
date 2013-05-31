package gr.auth.meng.isag.android.snakes.api;

public interface GameBoard {
	int getWidth();

	int getHeight();
	
	Tile getTile(int x, int y);
	
	void setTile(int x, int y, Tile tile);
	
	
}
