package ece.bomberman.yancle.map.tiles;

import ece.bomberman.yancle.Constants;
import javafx.scene.paint.Color;

public class DestructibleWall extends Tile {

	public DestructibleWall(int x, int y) {
		super(x,y,Constants.SIZE_TILE,Constants.SIZE_TILE);
		initialize();
	}
	
	@Override
	public void initialize() {
		setFill(Color.GREY);
		setStroke(Color.WHITE);
	}
	
	@Override
	public String toString(){
		return "DestructibleWall";
	}

}
