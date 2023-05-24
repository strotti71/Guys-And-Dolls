package controller;

import entities.Player;
import input.KeyBoardManager;
import view.GamePanel;
import view.GameWindow;
import word.WordMap;

public class Game implements Runnable {

	public enum Direction {
		LEFT, RIGHT
	}

	private GamePanel gp;

	public WordMap wordMap;
	public Player player;
	public KeyBoardManager keyBoardManager;

	private Thread gameloop;
	private long currentTime, previousTime;
	private final long FPS = 1000 / 444;
	
	public final static int ORIGINAL_TILE_SIZE = 32;
	public final static int SCALE_TILE = 3;
	public final static int TILE_SIZE = ORIGINAL_TILE_SIZE* SCALE_TILE;
	
	public final static int NUM_TILES_WIDTH =  15;
	public final static int NUM_TILES_HEIGHT =  8;
	
	public final static int SCREEN_WIDTH = TILE_SIZE * NUM_TILES_WIDTH;
	public final static int SCREEN_HEIGHT = TILE_SIZE * NUM_TILES_HEIGHT;
	
	public final static int LEFT_BORDER = (int) (0.2 * SCREEN_WIDTH);
	public final static int RIGHT_BORDER = (int) (0.6 * SCREEN_WIDTH);
	public static int offSetMap;

	public Game() {

		initClass();
		previousTime = System.currentTimeMillis();
		gameloop.start();

	}

	private void initClass() {
		wordMap = new WordMap(this);
		player = new Player(this);
		keyBoardManager = new KeyBoardManager(this);
		gameloop = new Thread(this);
		gp = new GamePanel(this);
		gp.addKeyListener(keyBoardManager);
		new GameWindow(gp);

	}

	private void update() {
		player.update();
	}

	private void redraw() {
		gp.repaint();
	}

	@Override
	public void run() {
		while (true) {
			currentTime = System.currentTimeMillis();
			if (currentTime - previousTime >= FPS) {
				update();
				redraw();
				previousTime = System.currentTimeMillis();
			}
		}

	}

}
