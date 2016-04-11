package ece.bomberman.yancle.map.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

public class UndestructibleWall extends Tile {

	public UndestructibleWall() throws IOException{
		super(SwingFXUtils.toFXImage(ImageIO.read(UndestructibleWall.class.getResourceAsStream("wall.png")),null));
	}
	
}
