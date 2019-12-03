package pong.game.tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pong.game.Game;

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
		assertTrue("Teste de cobertura de teste falhou",!error);
	}
	
	
	
}
