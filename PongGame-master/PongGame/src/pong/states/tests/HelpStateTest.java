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
public class HelpStateTest {
	
	private Game game;
	
	public HelpStateTest() {

		game = new Game(false);
		game.start();
	}
	
	@Test 
	void teclasDiversas_retornarAoMenu(){
		boolean error = false;
		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.HELP);
		HelpState currentState = (HelpState)sm.getState();
		for(int tecla = 0; tecla<256;tecla++) {
			currentState.checkKeyPressed(tecla);
			if (!(sm.getState() instanceof MenuState)) {
				error = true;
				break;
			}
			StateManager.setState(StateManager.HELP);
		}
		assertTrue("Qualquer tecla deve disparar o retorno ao menu principal",!error);
	}
	
	
	
}
