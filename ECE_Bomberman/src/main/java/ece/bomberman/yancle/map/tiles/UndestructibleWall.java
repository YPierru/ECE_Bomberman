package ece.bomberman.yancle.map.tiles;

import ece.bomberman.yancle.Constants;
import javafx.scene.paint.Color;

/**
 * Represent an undestructible wall.
 * @author YPierru
 *
 */
public class UndestructibleWall extends Tile {

	public UndestructibleWall(int x, int y) {
		super(x,y,Constants.SIZE_TILE,Constants.SIZE_TILE);
		initialize();
	}
	
	@Override
	public void initialize() {
		setFill(Color.BLACK);
		setStroke(Color.WHITE);
	}
	
	@Override
	public String toString(){
		return "UndestructibleWall";
	}

}
