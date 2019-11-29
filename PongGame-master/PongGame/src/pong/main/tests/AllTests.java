package pong.main.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


import pong.game.Game;
import pong.main.tests.ExitDeniedSecurityManager.ExitSecurityException;
import pong.states.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.*;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {
	
	private Game game;
	private static final int EXIT_CODE = 5;
	
	//@Rule
    //public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	public AllTests() {

		game = new Game();
		game.start();
	}

	@Before
    public void setUp() {
		//game = new Game();
		//game.start();
    } 

	@Test 
	void start_enter_iniciarJogo() {

		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.MENU);
		MenuState currentState = (MenuState)sm.getState();
		currentState.setChoice(0);
		currentState.checkKeyPressed(10);
		
		assertTrue("Jogo não foi iniciado corretamente",sm.getState() instanceof GameState);
	}
	@Test 
	void start_enter_telaAjuda() {
		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.MENU);
		MenuState currentState = (MenuState)sm.getState();
		currentState.setChoice(1);
		currentState.checkKeyPressed(10);
		
		assertTrue("Tela de ajuda não foi acessada corretamente",sm.getState() instanceof HelpState);
	}
	
    @Test
    public void start_enter_sairDoJogo() {
        try {
    		System.setSecurityManager(new ExitDeniedSecurityManager());
        	StateManager sm = game.getStateManager();
    		StateManager.setState(StateManager.MENU);
    		MenuState currentState = (MenuState)sm.getState();
    		currentState.setChoice(2);
    		currentState.checkKeyPressed(10);	
            fail("Expected exit");
        } catch (ExitSecurityException e) {
            int status = e.getStatus();
            assertTrue("Jogo não foi fechado corretamente",!game.isRunning());
        }
    }
	
	
	

	/*@Test 
	void start_enter_sairDoJogo() throws Exception{
		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.MENU);
		MenuState currentState = (MenuState)sm.getState();
		currentState.setChoice(2);
		currentState.checkKeyPressed(10);	
		
		assertTrue("Jogo não foi fechado corretamente",!game.isRunning());
	} */
	
}
