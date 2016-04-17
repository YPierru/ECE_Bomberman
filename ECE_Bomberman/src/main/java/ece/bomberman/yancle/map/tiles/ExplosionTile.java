package ece.bomberman.yancle.map.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;

public class ExplosionTile extends Tile {
	public ExplosionTile() throws IOException{
		super(SwingFXUtils.toFXImage(ImageIO.read(ExplosionTile.class.getResourceAsStream("explosion.jpg")),null));
	}
}
