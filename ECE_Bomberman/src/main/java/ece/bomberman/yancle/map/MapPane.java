package ece.bomberman.yancle.map;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import ece.bomberman.yancle.character.Orientation;
import ece.bomberman.yancle.character.Player;
import ece.bomberman.yancle.map.tiles.EmptyTile;
import ece.bomberman.yancle.map.tiles.Tile;
import ece.bomberman.yancle.map.tiles.TileContainer;
import ece.bomberman.yancle.map.tiles.UndestructibleWall;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Represent the board game.
 * Extends an AnchorPane
 * @author YPierru
 *
 */
public class MapPane extends AnchorPane implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TileContainer[][] tilesContainer;

	public static final int TILES_NUMBER_X=19;
	public static final int TILES_NUMBER_Y=13;
	
	public MapPane() {
		super();
		tilesContainer = new TileContainer[TILES_NUMBER_X][TILES_NUMBER_Y];
		initMap();
	}
	
	/**
	 * Draw the map
	 */
	public void initMap(){
		//ArrayList<TileContainer> listTC=new ArrayList<>();
		getChildren().clear();
		int x=0,y=0;
		Random rand = new Random();
		
		for(int i=0;i<TILES_NUMBER_Y;i++){
			y=TileContainer.SIZE_TILE*i;
			getChildren().add(new TileContainer(new UndestructibleWall(), 0, y));
			for(int j=1;j<TILES_NUMBER_X-1;j++){
				if(i==0 || i==TILES_NUMBER_Y-1 || (i%2!=1 && j%2==0)) {
					getChildren().add(new TileContainer(new UndestructibleWall(),x+(TileContainer.SIZE_TILE*j), y));
				}else{
					/*if(rand.nextInt(11)>5){
						getChildren().add(new TileContainer(new DestructibleWall(),x+(TileContainer.SIZE_TILE*j), y));
						listTC.clear();
					}else{*/
						getChildren().add(new TileContainer(new EmptyTile(),x+(TileContainer.SIZE_TILE*j), y));
					//}
				}
			}
			
			getChildren().add(new TileContainer(new UndestructibleWall(), (TILES_NUMBER_X-1)*TileContainer.SIZE_TILE, y));
		}
		
		for(int j=0;j<TILES_NUMBER_Y;j++){
			for(int i=0;i<TILES_NUMBER_X;i++){
				tilesContainer[i][j]=(TileContainer)getChildren().get(i+(TILES_NUMBER_X*j));
			}
		}
		
		
		//TileContainer tc2 = (TileContainer)getChildren().get(24);
		//tc2.addBomb(new Bomb());
		
	}

	public TileContainer[][] getTC(){
		return tilesContainer;
	}
	
	public void addCharacter(ArrayList<Player> listPlayers){
		TileContainer tc;
		Arc shape;
		
		for(int j=0;j<TILES_NUMBER_Y;j++){
			for(int i=0;i<TILES_NUMBER_X;i++){
				if(tilesContainer[i][j].isShapePresent()){
					//tilesContainer[i][j].getChildren().remove(tilesContainer[i][j].getChildren().size()-1);
				}
			}
		}
		
		for(Player p : listPlayers){
			shape = p.getShape();
			tc = tilesContainer[p.getArrayX()][p.getArrayY()];

			Timeline timeline = new Timeline();
			
			System.out.println(shape.getCenterX()+" "+tc.getCenterX());
			
			/*KeyValue kv = new KeyValue(shape.centerXProperty(), tc.getCenterX());
			KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
			KeyValue kv2 = new KeyValue(shape.centerYProperty(), tc.getCenterY());
			KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
			timeline.getKeyFrames().add(kf);
			timeline.getKeyFrames().add(kf2);
			timeline.play();*/
			
			shape.setCenterX(tc.getCenterX());
			shape.setCenterY(tc.getCenterY());
			tc.getChildren().add(shape);
			

			System.out.println(shape.centerXProperty().doubleValue()+" "+tc.getCenterX());
		}
	}
	
	@Override
	public String toString(){
		String str="";
		
		for(int j=0;j<TILES_NUMBER_Y;j++){
			for(int i=0;i<TILES_NUMBER_X;i++){
				str+=tilesContainer[i][j]+"\n";
			}
		}
		
		return str;
	}
	
	public void moveCharacter(Player p,Orientation orientation){
		
		int possibleX=p.getArrayX();
		int possibleY=p.getArrayY();
		Tile tile;
		Arc shape = p.getShape();
		
		if(orientation == Orientation.NORTH){
			possibleY--;
		}
		else if(orientation == Orientation.SOUTH){
			possibleY++;
		}
		else if(orientation == Orientation.EAST){
			possibleX++;
		}
		else if(orientation == Orientation.WEST){
			possibleX--;
		}	
		
		tile = tilesContainer[possibleX][possibleY].getTile();
		
		if(tile instanceof EmptyTile){
			p.deplacement(orientation);
			TileContainer tc = tilesContainer[p.getArrayX()][p.getArrayY()];
			shape.setCenterX(tc.getCenterX());
			shape.setCenterY(tc.getCenterY());
			tc.getChildren().add(shape);
		}
		
		p.setOrientation(orientation);
		
	}
	
	
	public int[] getEmptyArrayXY(){
		ArrayList<int[]> coordinates = new ArrayList<>();
		int[] xy = new int[2];
		
		for(int j=0;j<TILES_NUMBER_Y;j++){
			for(int i=0;i<TILES_NUMBER_X;i++){
				if(tilesContainer[i][j].getTile() instanceof EmptyTile && !tilesContainer[i][j].isShapePresent()){
					xy[0]=i;
					xy[1]=j;
					coordinates.add(xy);
					xy = new int[2];
				}
			}
		}

		Random rand = new Random();
		
		return coordinates.get(rand.nextInt(coordinates.size()));
		
	}
	
	
}
