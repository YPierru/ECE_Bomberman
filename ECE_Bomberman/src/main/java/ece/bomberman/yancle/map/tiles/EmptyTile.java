package ece.bomberman.yancle.map.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;

public class EmptyTile extends Tile {
	
	public EmptyTile() throws IOException{
		super(SwingFXUtils.toFXImage(ImageIO.read(EmptyTile.class.getResourceAsStream("empty.png")),null));
	}
	
}
