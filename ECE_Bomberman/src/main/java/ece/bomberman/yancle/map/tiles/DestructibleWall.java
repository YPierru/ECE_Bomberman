package ece.bomberman.yancle.map.tiles;

import javafx.scene.paint.Color;

/**
 * Represent a destructible wall
 * @author YPierru
 *
 */
public class DestructibleWall extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DestructibleWall() {
		super();
		initialize();
	}
	
	@Override
	public void initialize() {
		setFill(Color.GREY);
		setStroke(Color.WHITE);
	}
	
	@Override
	public String toString(){
		return "DestructibleWall X="+getX()+" Y="+getY()+"\n";
	}

}
