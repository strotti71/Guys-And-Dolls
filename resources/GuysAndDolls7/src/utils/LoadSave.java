package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import controller.Game;
import word.WordMap;

public class LoadSave {

	public static final String SHEET_PLAYER = "/player/sheetPlayer.png";
	public static final String SHEET_TILES_WORD = "/atlas/sheetTilesWord.png";
	public static final String Level_1_MAP = "/atlas/level_1.png";

	public static BufferedImage getImage(String string) {
		BufferedImage image = null;
		try {

			image = ImageIO.read(LoadSave.class.getResource(string));

		} catch (Exception e) {
		}
		return image;
	}

	public static int[][] getMap(String string) {
		BufferedImage imgTemp = getImage(string);
		WordMap.mapHeightInTiles = imgTemp.getHeight();
		WordMap.mapWidthinTiles = imgTemp.getWidth();
		WordMap.mapWidthInPixel = WordMap.mapWidthinTiles * Game.TILE_SIZE;

		int[][] level = new int[WordMap.mapHeightInTiles][WordMap.mapWidthinTiles];
		for (int i = 0; i < WordMap.mapHeightInTiles; i++) {
			for (int j = 0; j < WordMap.mapWidthinTiles; j++) {
				Color colore = new Color(imgTemp.getRGB(j, i));
				level[i][j] = colore.getRed();

			}
		}
		return level;
	}

}
