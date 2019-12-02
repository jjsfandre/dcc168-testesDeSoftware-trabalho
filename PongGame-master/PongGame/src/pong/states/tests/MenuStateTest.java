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
public class MenuStateTest {
	
	private Game game;
	
	public MenuStateTest() {

		game = new Game(false);
		game.start();
	}

	@Test 
	void enter_iniciarJogo() {

		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.MENU);
		MenuState currentState = (MenuState)sm.getState();
		currentState.setChoice(0);
		currentState.checkKeyPressed(10);
		
		assertTrue("Jogo não foi iniciado corretamente",sm.getState() instanceof GameState);
	}
	@Test 
	void enter_telaAjuda() {
		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.MENU);
		MenuState currentState = (MenuState)sm.getState();
		currentState.setChoice(1);
		currentState.checkKeyPressed(10);
		
		assertTrue("Tela de ajuda não foi acessada corretamente",sm.getState() instanceof HelpState);
	}
	@Test 
	void enter_sairDoJogo() {
		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.MENU);
		MenuState currentState = (MenuState)sm.getState();
		currentState.setChoice(2);
		currentState.checkKeyPressed(10);	
		
		assertTrue("Jogo não foi fechado corretamente",!game.isRunning());
	}
	@Test 
	void esc_nadaAFazer(){
		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.MENU);
		MenuState currentState = (MenuState)sm.getState();
		currentState.checkKeyPressed(27);	
		
		assertTrue("O estado não deve ser alterado ao clicar em esc",sm.getState() instanceof MenuState);
	}
	
	@Test 
	void teclasDiversas_nadaAFazer(){
		boolean error = false;
		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.MENU);
		MenuState currentState = (MenuState)sm.getState();
		for(int tecla = 0; tecla<256;tecla++) {
			if (tecla==10 || tecla == 70)
				continue;
			currentState.checkKeyPressed(tecla);
			if (!(sm.getState() instanceof MenuState)) {
				error = true;
				break;
			}
		}
		assertTrue("O estado não deve ser alterado ao clicar em qualquer tecla exceto o Enter",!error);
	}
	
	@Test 
	void f_fpsMode(){
		StateManager sm = game.getStateManager();
		StateManager.setState(StateManager.MENU);
		MenuState currentState = (MenuState)sm.getState();
		currentState.checkKeyPressed(70);	
		
		assertTrue("Ao digitar F deve entrar em FPSMode",sm.getState() instanceof FPSState);
	}
	
	
	
}
