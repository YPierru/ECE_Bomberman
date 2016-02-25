package ece.bomberman.yancle.map;

import java.util.Random;

import ece.bomberman.yancle.Constants;
import ece.bomberman.yancle.character.Bomb;
import ece.bomberman.yancle.character.Character;
import ece.bomberman.yancle.map.tiles.DestructibleWall;
import ece.bomberman.yancle.map.tiles.EmptyTile;
import ece.bomberman.yancle.map.tiles.TileContainer;
import ece.bomberman.yancle.map.tiles.UndestructibleWall;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * Represent the board game.
 * Extends an AnchorPane
 * @author YPierru
 *
 */
public class MapGame extends AnchorPane {
	

	public MapGame() {
		super();
		initMap();
	}
	
	/**
	 * Draw the map
	 */
	public void initMap(){
		getChildren().clear();
		int x=0,y=0;
		Random rand = new Random();
		
		for(int i=0;i<Constants.TILES_NUMBER_Y;i++){
			y=Constants.SIZE_TILE*i;
			getChildren().add(new TileContainer(new UndestructibleWall(0, y)));
			getChildren().add(new TileContainer(new UndestructibleWall((Constants.TILES_NUMBER_X-1)*Constants.SIZE_TILE, y)));
			
			for(int j=1;j<Constants.TILES_NUMBER_X-1;j++){
				if(i==0 || i==Constants.TILES_NUMBER_Y-1 || (i%2!=1 && j%2==0)) {
					getChildren().add(new TileContainer(new UndestructibleWall(x+(Constants.SIZE_TILE*j), y)));
				}else{
					if(rand.nextInt(11)>5){
						getChildren().add(new TileContainer(new DestructibleWall(x+(Constants.SIZE_TILE*j), y)));
					}else{
						getChildren().add(new TileContainer(new EmptyTile(x+(Constants.SIZE_TILE*j), y)));
					}
				}
			}
		}
		
		TileContainer tc = (TileContainer)getChildren().get(55);
		tc.addCharacter(new Character());
		
		
		TileContainer tc2 = (TileContainer)getChildren().get(54);
		tc2.addBomb(new Bomb());
		
		for(Node n : getChildren()){
			System.out.println(n +" "+ getChildren().indexOf(n));
		}
		
	}
}
