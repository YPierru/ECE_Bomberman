package ece.bomberman.yancle.map.tiles;

import java.io.IOException;
import java.io.Serializable;

import ece.bomberman.yancle.player.Avatar;
import ece.bomberman.yancle.player.BombImage;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;

/**
 * Represents a tile container
 * A tile container contain a tile, and can contain a bomb/characterw
 * @author YPierru
 *
 */
public class TileContainer extends Pane implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tile tile;
	private Avatar savedAvatar;
	private int x;
	private int y;
	
	public static final int SIZE_TILE=50;
	
	
	public TileContainer(Tile t,int x, int y) {
		super(t);
		tile=t;
		this.x=x;
		this.y=y;
		moveTile();
	}
	
	public void moveTile(){
		tile.setX(x);
		tile.setY(y);
	}
	
	@Override
	public String toString(){
		return tile.toString();
	}
	
	public Tile getTile(){
		return tile;
	}
	
	public boolean isDestructibleWallPresent(){
		for(Node n : getChildren()){
			if(n instanceof DestructibleWall){
				return true;
			}
		}
		return false;
	}
	
	public boolean isUndestructibleWallPresent(){
		for(Node n : getChildren()){
			if(n instanceof UndestructibleWall){
				return true;
			}
		}
		return false;
	}
	
	public void replaceDestructibleWallWithEmpty(){
		for(int i = 0;i<getChildren().size();i++){
			if(getChildren().get(i) instanceof DestructibleWall){
				getChildren().remove(i);
				try {
					tile=new EmptyTile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getChildren().add(tile);
				moveTile();
			}
		}
	}
	
	public void replaceEmptyWithDestructibleWall(){
		for(int i = 0;i<getChildren().size();i++){
			if(getChildren().get(i) instanceof EmptyTile){
				getChildren().remove(i);
				try {
					tile=new DestructibleWall();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getChildren().add(tile);
				moveTile();
			}
		}
	}
	
	public void replaceExplosionWithEmpty(){
		for(int i = 0;i<getChildren().size();i++){
			if(getChildren().get(i) instanceof ExplosionTile){
				getChildren().remove(i);
				try {
					tile=new EmptyTile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getChildren().add(tile);
				moveTile();
			}
		}
		
		
		
		for(int i=0;i<getChildren().size();i++){
			if(getChildren().get(i) instanceof Avatar){
				savedAvatar=(Avatar)getChildren().get(i);
				getChildren().remove(i);
				addAvatar(savedAvatar);
				break;
			}
		}
	}
	
	public void replaceWithExplosion(){
		
		for(int i = 0;i<getChildren().size();i++){
			if(getChildren().get(i) instanceof EmptyTile || getChildren().get(i) instanceof DestructibleWall){
				getChildren().remove(i);
				try {
					tile=new ExplosionTile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getChildren().add(tile);
				moveTile();
			}
		}
	}
	
	public boolean isAvatarPresent(){
		for(Node n : getChildren()){
			if(n instanceof Avatar){
				return true;
			}
		}
		return false;
	}
	
	public boolean isBombPresent(){
		for(Node n : getChildren()){
			if(n instanceof BombImage){
				return true;
			}
		}
		return false;
	}
	
	
	public void addAvatar(Avatar a){
		getChildren().add(a);
	}
	
	public void addBomb(BombImage b){
		getChildren().add(b);
	}
	
	
	public void removeAvatar(){
		for(int i = 0;i<getChildren().size();i++){
			if(getChildren().get(i) instanceof Avatar){
				getChildren().remove(i);
			}
		}
	}
	
	public void removeBomb(){
		for(int i = 0;i<getChildren().size();i++){
			if(getChildren().get(i) instanceof BombImage){
				getChildren().remove(i);
			}
		}
	}
	
	public void addShape(Shape s){
		getChildren().add(s);
	}
	
}
