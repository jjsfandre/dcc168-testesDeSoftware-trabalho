package pong.states.tests;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pong.game.Game;
import pong.input.KeyEventMock;
import pong.input.KeyManager;
import pong.states.FPSState;
import pong.states.GameState;
import pong.states.HelpState;
import pong.states.MenuState;
import pong.states.StateManager;

@RunWith(Suite.class)
@SuiteClasses({})
public class GameStateTest {
	
	private Game game;

	private List<Integer> teclasComAcao;
	
	public GameStateTest() {

		game = new Game(false);
		game.start();
		teclasComAcao  = new ArrayList();
		populateTeclasComAcao();
	}
	
	private void populateTeclasComAcao() {
		teclasComAcao.add(KeyEvent.VK_W);
		teclasComAcao.add(KeyEvent.VK_S);
		teclasComAcao.add(KeyEvent.VK_UP);
		teclasComAcao.add(KeyEvent.VK_DOWN);
		teclasComAcao.add(KeyEvent.VK_ESCAPE);
		teclasComAcao.add(KeyEvent.VK_END);
	}
	
	private void iniciarJogo(StateManager sm) {
		StateManager.setState(StateManager.GAME);
	}

	@Test 
	void player1_sobe() throws InterruptedException {
		StateManager sm = game.getStateManager();
		iniciarJogo(sm);
		
		GameState currentState = (GameState)sm.getState();
		KeyManager keyManager = game.getKeyManager();
		int positionInit = currentState.getPlayerA().getYPosition();
		
		KeyEventMock keyPressedEvent = new KeyEventMock(KeyEvent.VK_W);
		keyManager.keyPressed(keyPressedEvent);
		currentState.checkKeyPressed(2);
		Thread.sleep(100);
		keyManager.keyReleased(keyPressedEvent);
		int positionEnd = currentState.getPlayerA().getYPosition();
		assertTrue("Ao pressionar W o jogador 1 deve se mover para cima",positionEnd<positionInit);
	}

	@Test 
	void player1_desce() throws InterruptedException {
		StateManager sm = game.getStateManager();
		iniciarJogo(sm);
		
		GameState currentState = (GameState)sm.getState();
		KeyManager keyManager = game.getKeyManager();
		int positionInit = currentState.getPlayerA().getYPosition();
		
		KeyEventMock keyPressedEvent = new KeyEventMock(KeyEvent.VK_S);
		keyManager.keyPressed(keyPressedEvent);
		currentState.checkKeyPressed(2);
		Thread.sleep(100);
		keyManager.keyReleased(keyPressedEvent);
		int positionEnd = currentState.getPlayerA().getYPosition();
		assertTrue("Ao pressionar S o jogador 1 deve se mover para baixo",positionEnd>positionInit);
	}

	@Test 
	void player2_sobe() throws InterruptedException {
		StateManager sm = game.getStateManager();
		iniciarJogo(sm);
		
		GameState currentState = (GameState)sm.getState();
		KeyManager keyManager = game.getKeyManager();
		int positionInit = currentState.getPlayerB().getYPosition();
		
		KeyEventMock keyPressedEvent = new KeyEventMock(KeyEvent.VK_UP);
		keyManager.keyPressed(keyPressedEvent);
		currentState.checkKeyPressed(2);
		Thread.sleep(100);
		keyManager.keyReleased(keyPressedEvent);
		int positionEnd = currentState.getPlayerB().getYPosition();
		assertTrue("Ao pressionar UP o jogador 2 deve se mover para cima",positionEnd<positionInit);
	}

	@Test 
	void player2_desce() throws InterruptedException {
		StateManager sm = game.getStateManager();
		iniciarJogo(sm);
		
		GameState currentState = (GameState)sm.getState();
		KeyManager keyManager = game.getKeyManager();
		int positionInit = currentState.getPlayerB().getYPosition();
		
		KeyEventMock keyPressedEvent = new KeyEventMock(KeyEvent.VK_DOWN);
		keyManager.keyPressed(keyPressedEvent);
		currentState.checkKeyPressed(2);
		Thread.sleep(100);
		keyManager.keyReleased(keyPressedEvent);
		int positionEnd = currentState.getPlayerB().getYPosition();
		assertTrue("Ao pressionar DOWN o jogador 2 deve se mover para baixo",positionEnd>positionInit);
	}
	

	@Test 
	void esc_voltarAoMenu() throws InterruptedException{

		StateManager sm = game.getStateManager();
		iniciarJogo(sm);
		
		GameState currentState = (GameState)sm.getState();
		KeyManager keyManager = game.getKeyManager();
		KeyEventMock keyPressedEvent = new KeyEventMock(KeyEvent.VK_ESCAPE);
		keyManager.keyPressed(keyPressedEvent);
		currentState.checkKeyPressed(2);
		Thread.sleep(100);
		keyManager.keyReleased(keyPressedEvent);
		
		
		assertTrue("O jogo deve ser encerrado e retornar ao menu ao clicar em esc",sm.getState() instanceof MenuState);
	}

	
	
	@Test 
	void teclasDiversas_nadaAFazer() throws InterruptedException{
		KeyEventMock keyPressedEvent;
		String message = "";
		boolean error = false;
		KeyManager keyManager = game.getKeyManager();
		StateManager sm = game.getStateManager();
		for(int tecla = 0; tecla<256;tecla++) {
			if (teclasComAcao.contains(tecla))
				continue;
			System.out.println("Tecla testada:"+tecla);
			iniciarJogo(sm);
			GameState currentState = (GameState)sm.getState();
			int positionAInit = currentState.getPlayerA().getYPosition();
			int positionBInit = currentState.getPlayerB().getYPosition();
			
			keyPressedEvent = new KeyEventMock(tecla);
			keyManager.keyPressed(keyPressedEvent);
			currentState.checkKeyPressed(2);
			Thread.sleep(100);
			keyManager.keyReleased(keyPressedEvent);
			
			if (!(sm.getState() instanceof GameState)) {
				error = true;
				message = "Sair do jogo";
				break;
			}

			int positionAEnd = currentState.getPlayerA().getYPosition();
			int positionBEnd = currentState.getPlayerB().getYPosition();
			
			if ((positionAEnd!=positionAInit)) {
				error = true;
				message = "Movimento do jogador 1";
				break;
			}
			
			if ((positionBInit!=positionBEnd)) {
				error = true;
				message = "Movimento do jogador 2";
				break;
			}
		}
		assertTrue("O jogo não pode sofrer alteração nenhuma quando uma tecla sem comando atribuído é acionada."
				+ "Ação inválida: " + message,!error);
	} 
		

	@Test 
	void end_alterarVelocidade() throws InterruptedException{

		StateManager sm = game.getStateManager();
		iniciarJogo(sm);
		
		GameState currentState = (GameState)sm.getState();
		KeyManager keyManager = game.getKeyManager();
		KeyEventMock keyPressedEvent = new KeyEventMock(KeyEvent.VK_END);
		int currentXVel=currentState.getXVel();
		while(currentXVel>=0) {
			currentXVel=currentState.getXVel();
			System.out.println("Jogada do player 1. xVel="+currentXVel);
		}
		keyManager.keyPressed(keyPressedEvent);
		currentState.checkKeyPressed(2);
		Thread.sleep(100);
		int finalXvel = currentState.getXVel();
		assertTrue("A velocidade da bolinha deve ser alterada ao clicar em End e a jogada for do player 2",finalXvel==-10);
	}
	@Test 
	void end_retornarVelocidade() throws InterruptedException{

		StateManager sm = game.getStateManager();
		iniciarJogo(sm);
		
		GameState currentState = (GameState)sm.getState();
		KeyManager keyManager = game.getKeyManager();
		KeyEventMock keyPressedEvent = new KeyEventMock(KeyEvent.VK_END);
		int currentXVel=currentState.getXVel();
		while(currentXVel>=0) {
			currentXVel=currentState.getXVel();
			System.out.println("Jogada do player 1. xVel="+currentXVel);
		}
		keyManager.keyReleased(keyPressedEvent);
		currentState.checkKeyPressed(2);
		Thread.sleep(100);
		int finalXvel = currentState.getXVel();
		assertTrue("A velocidade da bolinha deve ser restaurada ao soltar a tecla End e a jogada for do player 2",finalXvel==-4);
	}
	
}
