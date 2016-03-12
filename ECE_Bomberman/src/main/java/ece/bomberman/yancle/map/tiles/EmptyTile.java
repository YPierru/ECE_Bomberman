package ece.bomberman.yancle.map.tiles;

import javafx.scene.paint.Color;


/**
 * Represents an empty tile (which means there's no wall, but possibly a char/bomb)
 * @author YPierru
 *
 */
public class EmptyTile extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyTile(){
		super();
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
		return "Empty X="+getX()+" Y="+getY()+"\n";
	}
	
	
	
}
