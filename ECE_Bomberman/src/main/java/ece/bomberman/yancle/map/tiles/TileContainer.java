package ece.bomberman.yancle.map.tiles;

import java.io.Serializable;

import ece.bomberman.yancle.player.IInteractiveShape;
import ece.bomberman.yancle.player.Player;
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
	private int x;
	private int y;
	private int centerX;
	private int centerY;
	
	public static final int SIZE_TILE=50;
	
	
	public TileContainer(Tile t,int x, int y) {
		super(t);
		tile=t;
		this.x=x;
		this.y=y;
		centerX=x+SIZE_TILE/2;
		centerY=y+SIZE_TILE/2;
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

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	
	public Tile getTile(){
		return tile;
	}
	
	public boolean isArcPresent(){
		for(Node n : getChildren()){
			if(n instanceof Arc){
				return true;
			}
		}
		return false;
	}
	
	public boolean isDestructibleWallPresent(){
		for(Node n : getChildren()){
			if(n instanceof DestructibleWall){
				return true;
			}
		}
		return false;
	}
	
	public void removeDestructibleWallIfPresent(){
		for(int i = 0;i<getChildren().size();i++){
			if(getChildren().get(i) instanceof DestructibleWall){
				getChildren().remove(i);
			}
		}
	}
	
	public boolean isInteractiveShapePresent(IInteractiveShape p){
		
		Node n;
		
		for(int i = 0;i<getChildren().size();i++){
			n=getChildren().get(i);
			if(n instanceof Arc && getCenterX()==p.getCenterX() && getCenterY()==p.getCenterY()){
				return true;
			}
		}
		
		return false;
		
	}
	
	public void addDestructibleWall(DestructibleWall dw){
		
	}
	
	public void removePlayer(){
		for(int i = 0;i<getChildren().size();i++){
			if(getChildren().get(i) instanceof Arc){
				getChildren().remove(i);
			}
		}
	}
	
	public void addShape(Shape s){
		getChildren().add(s);
	}
}
