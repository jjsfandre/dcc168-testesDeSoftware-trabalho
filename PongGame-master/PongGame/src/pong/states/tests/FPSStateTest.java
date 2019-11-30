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
public class FPSStateTest {
	
	private Game game;
	
	public FPSStateTest() {

		game = new Game(false);
		game.start();
	}
	
	@Test 
	void teclasDiversas_nadaAFazer(){
		boolean error = false;
		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.FPS);
		FPSState currentState = (FPSState)sm.getState();
		for(int tecla = 0; tecla<256;tecla++) {
			currentState.checkKeyPressed(tecla);
			if (!(sm.getState() instanceof MenuState)) {
				error = true;
				break;
			}
			StateManager.setState(StateManager.FPS);
		}
		assertTrue("Qualquer tecla deve disparar o retorno ao menu principal",!error);
	}
	
	
	
}
