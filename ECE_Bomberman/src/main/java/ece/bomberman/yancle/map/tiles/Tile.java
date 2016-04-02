package ece.bomberman.yancle.map.tiles;

import java.io.Serializable;

import javafx.scene.shape.Rectangle;


/**
 * Abstract class for the Tile.
 * Extend Rectangle
 * @author YPierru
 *
 */
public abstract class Tile extends Rectangle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Tile(){
		super(TileContainer.SIZE_TILE,TileContainer.SIZE_TILE);
		//handleClick();
	}
	
	public abstract void initialize();

}
