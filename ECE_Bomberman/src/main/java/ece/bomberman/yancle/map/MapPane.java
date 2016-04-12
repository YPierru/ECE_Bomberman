package ece.bomberman.yancle.map;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import ece.bomberman.yancle.map.tiles.EmptyTile;
import ece.bomberman.yancle.map.tiles.Tile;
import ece.bomberman.yancle.map.tiles.TileContainer;
import ece.bomberman.yancle.map.tiles.UndestructibleWall;
import ece.bomberman.yancle.player.Bomb;
import ece.bomberman.yancle.player.Orientation;
import ece.bomberman.yancle.player.Player;
import javafx.scene.layout.AnchorPane;

/**
 * Represent the board game.
 * Extends an AnchorPane
 *
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
		try {
			initMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Draw the map
	 * @throws IOException 
	 */
	public void initMap() throws IOException{
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
						getChildren().add(new TileContainer(new EmptyTile(),x+(TileContainer.SIZE_TILE*j), y));
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
	
	public void refresh(){
		getChildren().remove(0, getChildren().size());
		
		
		for(int j=0;j<TILES_NUMBER_Y;j++){
			for(int i=0;i<TILES_NUMBER_X;i++){
				getChildren().add(tilesContainer[i][j]);
			}
		}
	}
	
	public void displayCharactersImage(ArrayList<Player> listPlayers){
		TileContainer tc;
		TileContainer tcBomb;
		
		for(int j=0;j<TILES_NUMBER_Y;j++){
			for(int i=0;i<TILES_NUMBER_X;i++){
				tilesContainer[i][j].removeAvatar();
				tilesContainer[i][j].removeBomb();
			}
		}
		
		for(Player p : listPlayers){
			tc = tilesContainer[p.getArrayX()][p.getArrayY()];
			
			for(int i=0;i<p.getBombSet().size();i++){
				Bomb b = p.getBombSet().get(i);
				tcBomb=tilesContainer[b.getArrayX()][b.getArrayY()];
				if(!tcBomb.isBombPresent()){
					System.out.println("bomb#"+i);
					tcBomb.addBomb(b.getBombImage());
				}
			}
			System.out.println("**************************");

			if(!tc.isAvatarPresent()){
				tc.addAvatar(p.getAvatar());
			}

		}
		refresh();
	}
	
	public void displayDestructibleWalls(ArrayList<Integer[]> listCooDW){
		int x,y;

		for(int j=0;j<TILES_NUMBER_Y;j++){
			for(int i=0;i<TILES_NUMBER_X;i++){
				tilesContainer[i][j].replaceDestructibleWallWithEmpty();
			}
		}
		
		for(int i=0;i<listCooDW.size();i++){
			x=listCooDW.get(i)[0];
			y=listCooDW.get(i)[1];
			
			tilesContainer[x][y].replaceEmptyWithDestructibleWall();
		}
		refresh();
		
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
	
	public boolean isMovePossible(Player p,Orientation orientation){
		
		int possibleX=p.getArrayX();
		int possibleY=p.getArrayY();
		Tile tile;
		TileContainer tc;
		
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
		
		tc = tilesContainer[possibleX][possibleY];
		tile = tc.getTile();
		
		if(!tc.isAvatarPresent() && !tc.isBombPresent()){
			if(tile instanceof EmptyTile){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	
	}
	
	
	public int[] getEmptyArrayXY(){
		ArrayList<int[]> coordinates = new ArrayList<>();
		int[] xy = new int[2];
		
		for(int j=0;j<TILES_NUMBER_Y;j++){
			for(int i=0;i<TILES_NUMBER_X;i++){
				if(tilesContainer[i][j].getTile() instanceof EmptyTile && !tilesContainer[i][j].isAvatarPresent() && !tilesContainer[i][j].isBombPresent()){
					//System.out.println(tilesContainer[i][j]);
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
