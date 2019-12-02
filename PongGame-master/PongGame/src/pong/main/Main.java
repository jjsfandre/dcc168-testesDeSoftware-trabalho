package pong.main;

import pong.game.Game;

public class Main {
	
	private static Game game;

	public static void main(String[] args) {
		
		game = new Game();
		game.start();
	}
	
	public Game getGame() {
		return game;
	}

}
