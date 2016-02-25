package ece.bomberman.yancle.map.tiles;

import ece.bomberman.yancle.Constants;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class EmptyTile extends Tile {

	public EmptyTile(int x, int y){
		super(x,y,Constants.SIZE_TILE,Constants.SIZE_TILE);
		initialize();
	}

	@Override
	public void initialize() {
		setFill(Color.WHITE);
		//setStroke(Color.WHITE);
		//setStroke(Color.LIGHTGRAY);
	}
	
	@Override
	public String toString(){
		return "Empty";
	}
	
	
	
}
