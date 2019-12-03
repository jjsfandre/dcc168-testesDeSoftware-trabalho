package pong.game.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pong.game.Game;
import pong.main.Main;
import pong.states.FPSState;
import pong.states.GameState;
import pong.states.HelpState;
import pong.states.MenuState;
import pong.states.StateManager;

@RunWith(Suite.class)
@SuiteClasses({})
public class GameTest {
	
	private Game game;
	
	@Test 
	void game_run_test(){
		boolean error;
		try {
			game = new Game(false);
			game.start();
			error = false;
			Thread.sleep(2000);
			game.stopGame(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error =true;
		}
		//StateManager sm = game.getStateManager();
		/*StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.FPS);
		FPSState currentState = (FPSState)sm.getState();
		for(int tecla = 0; tecla<256;tecla++) {
			currentState.checkKeyPressed(tecla);
			if (!(sm.getState() instanceof MenuState)) {
				error = true;
				break;
			}
			StateManager.setState(StateManager.FPS);
		}*/
		assertTrue("Teste de cobertura de teste falhou",!error);
	}
	
	
	
}
