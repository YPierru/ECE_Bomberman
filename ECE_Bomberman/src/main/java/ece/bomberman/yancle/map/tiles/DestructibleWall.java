package ece.bomberman.yancle.map.tiles;

import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

public class DestructibleWall extends Tile {

	public DestructibleWall() throws IOException{
		super(SwingFXUtils.toFXImage(ImageIO.read(DestructibleWall.class.getResourceAsStream("destructibleWall.png")),null));
	}
	
}
