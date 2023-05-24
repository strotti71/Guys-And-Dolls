package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import controller.Game;
import controller.Game.Direction;
import utils.LoadSave;
import word.WordMap;

public class Player extends Entity {

	private BufferedImage sheetPlayer;
	private Game game;

	private BufferedImage[] playerRunningRight;
	private BufferedImage[] playerRunningLeft;

	private BufferedImage[] playerIdleRight;
	private BufferedImage[] playerIdleLeft;
	private BufferedImage image;
	private int numPlayerImage;
	private int numCicliArray;

	private final int SCALE_PLAYER = 3;
	private final int ORIGINAL_PLAYER_WIDTH = 16;
	private final int ORIGINAL_PLAYER_HEIGHT = 24;
	private final int PLAYER_WIDTH = ORIGINAL_PLAYER_WIDTH *SCALE_PLAYER;
	private final int PLAYER_HEIGHT = ORIGINAL_PLAYER_HEIGHT* SCALE_PLAYER;

	public Player(Game game) {
		this.game = game;
		init();
	}

	private void init() {

		direction = Direction.RIGHT;
		sheetPlayer = LoadSave.getImage(LoadSave.SHEET_PLAYER);
		creaArrayMovimento();
		x = Game.TILE_SIZE;
		y = Game.SCREEN_HEIGHT - 2*game.TILE_SIZE - PLAYER_HEIGHT;
		speed = 3;
	}

	private void creaArrayMovimento() {
		playerRunningRight = new BufferedImage[4];
		playerRunningLeft = new BufferedImage[4];
		playerIdleRight = new BufferedImage[4];
		playerIdleLeft = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			playerRunningRight[i] = sheetPlayer.getSubimage(i * ORIGINAL_PLAYER_WIDTH, 48, ORIGINAL_PLAYER_WIDTH, ORIGINAL_PLAYER_HEIGHT);
			playerRunningLeft[i] = sheetPlayer.getSubimage((i + 4) * ORIGINAL_PLAYER_WIDTH, 48, ORIGINAL_PLAYER_WIDTH, ORIGINAL_PLAYER_HEIGHT);
			playerIdleRight[i] = sheetPlayer.getSubimage(i * ORIGINAL_PLAYER_WIDTH, 24, ORIGINAL_PLAYER_WIDTH, ORIGINAL_PLAYER_HEIGHT);
			playerIdleLeft[i] = sheetPlayer.getSubimage((i + 4) * ORIGINAL_PLAYER_WIDTH, 24, ORIGINAL_PLAYER_WIDTH, ORIGINAL_PLAYER_HEIGHT);

		}

	}

	@Override
	public void redraw(Graphics2D g2) {

		g2.drawImage(image, x-Game.offSetMap, y,  PLAYER_WIDTH  ,  PLAYER_HEIGHT , null);

	}

	@Override
	public void update() {
		if (x<0) x= 0;
		else if(x>WordMap.mapWidthInPixel) x= WordMap.mapWidthInPixel;
		
		checkCloseToBorder();

		if (!(isMovingDown() || isMovingLeft() || isMovingRight() || isMovingUp())) {
			if (direction == Direction.RIGHT)
				image = playerIdleRight[numPlayerImage];
			else
				image = playerIdleLeft[numPlayerImage];

			numCicliArray++;
			if (numCicliArray == 10) {
				numPlayerImage++;
				if (numPlayerImage > 3)
					numPlayerImage = 0;
				numCicliArray = 0;
			}
		}

		if (isMovingLeft()) {
			x -= speed;
			direction = Direction.LEFT;
			image = playerRunningLeft[numPlayerImage];

			numCicliArray++;
			if (numCicliArray == 10) {
				numPlayerImage++;
				if (numPlayerImage > 3)
					numPlayerImage = 0;
				numCicliArray = 0;
			}
		}
		if (isMovingRight()) {
			x += speed;
			direction = Direction.RIGHT;
			image = playerRunningRight[numPlayerImage];

			numCicliArray++;
			if (numCicliArray == 10) {
				numPlayerImage++;
				if (numPlayerImage > 3)
					numPlayerImage = 0;
				numCicliArray = 0;
			}

		}
		if (isMovingUp()) {
			y -= speed;
		}
		if (isMovingDown()) {
			y += speed;
		}

	}

	private void checkCloseToBorder() {
		int difference = x-Game.offSetMap;
		if (difference > Game.RIGHT_BORDER)
			Game.offSetMap += difference-Game.RIGHT_BORDER;
		
		if (difference < Game.LEFT_BORDER)
			Game.offSetMap += difference - Game.LEFT_BORDER;
		
		if (Game.offSetMap <0) Game.offSetMap =0;
		else if (Game.offSetMap > WordMap.mapWidthInPixel) Game.offSetMap = WordMap.mapWidthInPixel;
		
		
	}

}
