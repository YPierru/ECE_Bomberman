package ece.bomberman.yancle.map.tiles;

import ece.bomberman.yancle.Constants;
import ece.bomberman.yancle.character.Bomb;
import ece.bomberman.yancle.character.Character;
import javafx.scene.layout.Pane;

/**
 * Represents a tile container
 * A tile container contain a tile, and can contain a bomb/character
 * @author YPierru
 *
 */
public class TileContainer extends Pane {

	private Tile tile;
	
	
	public TileContainer(Tile t) {
		super(t);
		tile=t;
	}
	
	public void addCharacter(Character c){
		c.setX(tile.getX()+Constants.SIZE_TILE/2);
		c.setY(tile.getY()+Constants.SIZE_TILE/2);
		getChildren().add(c);
	}
	
	public void addBomb(Bomb b){
		b.setX(tile.getX()+Constants.SIZE_TILE/2);
		b.setY(tile.getY()+Constants.SIZE_TILE/2);
		getChildren().add(b);
	}
	
	@Override
	public String toString(){
		return tile.toString();
	}
}
