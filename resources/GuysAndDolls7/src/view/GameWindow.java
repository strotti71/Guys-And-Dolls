package view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameWindow {

	private JFrame gameWindow;
	private GamePanel gp;

	public GameWindow(GamePanel gp) {
		this.gp = gp;
		gameWindow = new JFrame("Guys & Dolls");


		gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		gameWindow.add(gp);
		gameWindow.pack();
		gameWindow.setResizable(false);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);

	}
}
