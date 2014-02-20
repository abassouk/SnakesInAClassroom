package gr.auth.meng.isag.android.snakes;

/**
 * A pair of ints. Misnamed, should be IntCoordinates....
 * 
 * @author abas
 */
public class IntPair {
	private int x, y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public IntPair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}