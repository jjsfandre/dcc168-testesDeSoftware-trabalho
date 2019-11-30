package pong.states.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pong.game.Game;
import pong.states.FPSState;
import pong.states.GameState;
import pong.states.HelpState;
import pong.states.MenuState;
import pong.states.StateManager;

@RunWith(Suite.class)
@SuiteClasses({})
public class GameStateTest {
	
	private Game game;
	
	public GameStateTest() {

		game = new Game(false);
		game.start();
	}
	
	private void iniciarJogo(StateManager sm) {
		StateManager.setState(StateManager.GAME);
	}

	@Test 
	void player1_sobe() {
		StateManager sm = game.getStateManager();
		iniciarJogo(sm);
		
		assertTrue("Jogo não foi iniciado corretamente",sm.getState() instanceof GameState);
	}
	
	
	
}
