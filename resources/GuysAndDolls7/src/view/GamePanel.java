package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import controller.Game;

public class GamePanel extends JPanel {

	Graphics2D g2;
	public int x = 200;
	public int y = 300;
	private Game game;

	public GamePanel(Game game) {
		super();
		this.game = game;
		this.setPreferredSize(new Dimension(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT));
		this.setBackground(new Color(35, 135, 229));
		this.setDoubleBuffered(true);
		this.setFocusable(true);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;

		game.wordMap.redraw(g2);
		game.player.redraw(g2);

		g2.dispose();

	}

}
