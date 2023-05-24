package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.Game;

public class KeyBoardManager implements KeyListener {

	private Game game;

	public KeyBoardManager(Game game) {
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == e.VK_LEFT) {
			game.player.setMovingLeft(true);
			game.player.setMovingRight(false);
		}
		if (code == e.VK_RIGHT) {
			game.player.setMovingRight(true);
			game.player.setMovingLeft(false);
		}
		if (code == e.VK_UP) {
			game.player.setMovingUp(true);
			game.player.setMovingDown(false);
		}
		if (code == e.VK_DOWN) {
			game.player.setMovingDown(true);
			game.player.setMovingUp(false);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == e.VK_LEFT) {
			game.player.setMovingLeft(false);
		}
		if (code == e.VK_RIGHT) {
			game.player.setMovingRight(false);
		}
		if (code == e.VK_UP) {
			game.player.setMovingUp(false);
		}
		if (code == e.VK_DOWN) {
			game.player.setMovingDown(false);
		}

	}

}
