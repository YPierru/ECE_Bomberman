package ece.bomberman.yancle.map;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import ece.bomberman.yancle.Client;
import ece.bomberman.yancle.map.tiles.EmptyTile;
import ece.bomberman.yancle.map.tiles.Tile;
import ece.bomberman.yancle.map.tiles.TileContainer;
import ece.bomberman.yancle.map.tiles.UndestructibleWall;
import ece.bomberman.yancle.player.Avatar;
import ece.bomberman.yancle.player.Bomb;
import ece.bomberman.yancle.player.Orientation;
import ece.bomberman.yancle.player.Player;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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
	private Client client;
	
	public static final int TILES_NUMBER_X=19;
	public static final int TILES_NUMBER_Y=13;
	
	
	public MapPane(Client c) {
		super();
		client=c;
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
		getChildren().clear();
		int x=0,y=0;
		
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
	
	public DestructibleWallManager displayExplosion(ExplosionCooManager listCooExplosion,DestructibleWallManager listDW, ArrayList<Player> listPlayers){
		int xDW,yDW;
		for(int i=0;i<listCooExplosion.size();i++){
			int x=listCooExplosion.get(i)[0];
			int y=listCooExplosion.get(i)[1];
			
			
			tilesContainer[x][y].replaceWithExplosion();
			
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					PauseTransition pause = new PauseTransition(Duration.millis(500));
			        pause.setOnFinished(event ->{
			        	tilesContainer[x][y].replaceExplosionWithEmpty();
			        } );
			        pause.play();
				}
			});
			t.start();
			
			for(int j=0;j<listDW.size();j++){
				xDW=listDW.get(j)[0];
				yDW=listDW.get(j)[1];
				
				if(x==xDW && y==yDW){
					listDW.remove(j);
				}
			}
			
			for(Player p : listPlayers){
				if(p.getCooX()==x && p.getCooY()==y){
					p.setLife(p.getLife()-Bomb.POWER);
				}
			}
			
		}
		return listDW;
				
	}
	
	public void displayCharactersImage(ArrayList<Player> listPlayers,boolean printAll){
		TileContainer tc;
		
		for(Player p : listPlayers){
			tc = tilesContainer[p.getCooX()][p.getCooY()];
			
			/*if(p.isDead()){
				tc.removeAvatar();
			}else{*/
				if(printAll){
					if(!tc.isAvatarPresent()){
						tc.addAvatar(p.getAvatar());
					}
				}else{
					if(p.hasMoved()){
						
						tilesContainer[p.getPreviousCooX()][p.getPreviousCooY()].removeAvatar();
						if(!tc.isAvatarPresent()){
							tc.addAvatar(p.getAvatar());
						}		
						p.setHasMoved(false);
					}
				}
			//}

		}
	}
	
	public void displayBombs(ArrayList<Bomb> listBombs){		
		for(Bomb b : listBombs){
			if(!b.isCountdownStarted()){
				TileContainer tc = tilesContainer[b.getCooX()][b.getCooY()];
				
				if(!tc.isBombPresent()){
					tc.addBomb(b.getBombImage());
					b.setCountdownStarted(true);
					ExplosionCooManager listCooExplosion = generateExplosionCoo(b);
					
					new Thread(new Runnable() {
						@Override
						public void run() {
							PauseTransition pause = new PauseTransition(Duration.millis(Bomb.DURATION));
					        pause.setOnFinished(event ->{
					        	tc.removeBomb();
					        	b.setListCooExplosion(listCooExplosion);
								b.setHasExploded(true);
					        } );
					        pause.play();
						}
					}).start();
				}
			}
		}
	}
	
	public ExplosionCooManager generateExplosionCoo(Bomb b){
		ExplosionCooManager listCooExplosion = new ExplosionCooManager();
		int xBomb=b.getCooX()+1;
		
		while(!tilesContainer[xBomb][b.getCooY()].isUndestructibleWallPresent()){
			listCooExplosion.add(new Integer[]{xBomb,b.getCooY()});
			xBomb++;
		}
		
		xBomb=b.getCooX()-1;
		while(!tilesContainer[xBomb][b.getCooY()].isUndestructibleWallPresent()){
			listCooExplosion.add(new Integer[]{xBomb,b.getCooY()});
			xBomb--;
		}
		
		int yBomb=b.getCooY()+1;
		while(!tilesContainer[b.getCooX()][yBomb].isUndestructibleWallPresent()){
			listCooExplosion.add(new Integer[]{b.getCooX(),yBomb});
			yBomb++;
		}
		
		yBomb=b.getCooY()-1;
		while(!tilesContainer[b.getCooX()][yBomb].isUndestructibleWallPresent()){
			listCooExplosion.add(new Integer[]{b.getCooX(),yBomb});
			yBomb--;
		}
		
		return listCooExplosion;
	}
	
	public void displayDestructibleWalls(DestructibleWallManager listCooDW){
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
		
		int possibleX=p.getCooX();
		int possibleY=p.getCooY();
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
