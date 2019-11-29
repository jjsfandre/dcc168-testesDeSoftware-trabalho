package pong.main.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pong.game.Game;
import pong.states.GameState;
import pong.states.HelpState;
import pong.states.MenuState;
import pong.states.StateManager;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {
	
	private Game game;
	private static final int EXIT_CODE = 5;
	
	//@Rule
    //public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	public AllTests() {

		game = new Game(false);
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
	void start_enter_sairDoJogo() throws Exception{
		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.MENU);
		MenuState currentState = (MenuState)sm.getState();
		currentState.setChoice(2);
		currentState.checkKeyPressed(10);	
		
		assertTrue("Jogo não foi fechado corretamente",!game.isRunning());
	}	
	
}
