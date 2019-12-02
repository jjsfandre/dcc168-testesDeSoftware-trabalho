package pong.input;
/*
 * This is necessary to mock KeyEvent object to use in unit tests
 */
public class KeyEventMock {

	
	private final int keyCode;

	public KeyEventMock(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyCode() {
		return keyCode;
	}
	
	
}
