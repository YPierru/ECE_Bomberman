package ece.bomberman.yancle.map.tiles;

import javafx.scene.paint.Color;

/**
 * Represent an undestructible wall.
 * @author YPierru
 *
 */
public class UndestructibleWall extends Tile {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UndestructibleWall() {
		super();
		initialize();
	}
	
	@Override
	public void initialize() {
		setFill(Color.FUCHSIA);
		setStroke(Color.WHITE);
	}
	
	@Override
	public String toString(){
		return "UndestructibleWall X="+getX()+" Y="+getY()+"\n";
	}

}
