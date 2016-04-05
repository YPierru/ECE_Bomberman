package ece.bomberman.yancle.map.tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class ImgUtils {

	public static BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(TileContainer.SIZE_TILE, TileContainer.SIZE_TILE, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, TileContainer.SIZE_TILE, TileContainer.SIZE_TILE, null);
		g.dispose();
		return resizedImage;
	}
	
}
