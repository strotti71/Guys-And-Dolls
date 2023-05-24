package word;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import controller.Game;
import entities.Drawable;
import utils.LoadSave;

public class WordMap implements Drawable {
	private Game game;
	private BufferedImage sheetTilesWord;

	public static int mapWidthinTiles, mapHeightInTiles, mapWidthInPixel;

	public WordMap(Game game) {
		this.game = game;
		init();
	}

	private BufferedImage GRASS_CENTER;
	private BufferedImage EARTH_CENTER;

	private int[][] levelMap;
	//	= { 
	//			{ 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99 },
	//			{ 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99 },
	//			{ 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99 },
	//			{ 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99 },
	//			{ 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99 },
	//			{ 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99 },
	//			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
	//			{ 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 }
	//
	//	};

	private void init() {

		sheetTilesWord = LoadSave.getImage(LoadSave.SHEET_TILES_WORD);
		levelMap = LoadSave.getMap(LoadSave.Level_1_MAP);
		GRASS_CENTER = sheetTilesWord.getSubimage(Game.ORIGINAL_TILE_SIZE, 0, Game.ORIGINAL_TILE_SIZE,
				Game.ORIGINAL_TILE_SIZE);
		EARTH_CENTER = sheetTilesWord.getSubimage(Game.ORIGINAL_TILE_SIZE, Game.ORIGINAL_TILE_SIZE,
				Game.ORIGINAL_TILE_SIZE, Game.ORIGINAL_TILE_SIZE);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redraw(Graphics2D g2) {

		for (int i = 0; i < Game.NUM_TILES_HEIGHT; i++)
			for (int j = 0; j < mapWidthInPixel; j++) {

				long pos = j * Game.TILE_SIZE - Game.offSetMap;

				 if (pos < game.player.getX() + Game.SCREEN_WIDTH) {
				g2.drawImage(getImage(levelMap[i][j]), j * game.TILE_SIZE -Game.offSetMap , i*  Game.TILE_SIZE,
						Game.TILE_SIZE, Game.TILE_SIZE, null);
				 }
			}

	}

	private Image getImage(int numImage) {

		switch (numImage) {
		case 20:
			return GRASS_CENTER;
		case 90:
			return EARTH_CENTER;

		default:
			break;
		}
		return null;
	}

}
