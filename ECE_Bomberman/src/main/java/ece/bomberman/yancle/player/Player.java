package ece.bomberman.yancle.player;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ece.bomberman.yancle.Client;
import ece.bomberman.yancle.map.tiles.TileContainer;
import ece.bomberman.yancle.utility.Chronometer;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Player implements Serializable{

	private static final long serialVersionUID = 1L;
	private int life;
	private int speed=300;//ms
	private int cooX;
	private int cooY;
	private int numberBombsPlanted;
	private int previousCooX;
	private int previousCooY;
	private Orientation orientation = Orientation.EAST;
	private String name;
	transient BufferedImage avatarBuff;
	private boolean isDisplayed;
	transient Client observer;
	private boolean hasMoved=false;
	//private boolean dead = false;

	public static final int STARTING_LIFE = 5;
	public static final int NUMBER_BOMB_MAX=10;
	
	public Player(BufferedImage a, String n,Client c){
		isDisplayed=false;
		life = STARTING_LIFE;
		numberBombsPlanted=0;
		avatarBuff=a;
		name=n;
		observer=c;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(avatarBuff, "png", out); // png is lossless
        
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        avatarBuff = ImageIO.read(in);
    }
	
	public Avatar getAvatar(){
		Avatar av = new Avatar(SwingFXUtils.toFXImage(avatarBuff, null));
		av.setX(cooX*TileContainer.SIZE_TILE);
		av.setY(cooY*TileContainer.SIZE_TILE);
		return av;
	}

	public void deplacement(Orientation or){
		int x=cooX;
		int y=cooY;
		orientation=or;
		if(or == Orientation.NORTH){
			y--;
		}
		else if(or == Orientation.SOUTH){
			y++;
		}
		else if(or == Orientation.EAST){
			x++;
		}
		else if(or == Orientation.WEST){
			x--;
		}
		setXY(x, y);
	}
	
	public void setOrientation(Orientation or){
		orientation=or;
		setHasMoved(true);
	}
	
	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life the life to set
	 */
	public void setLife(int l) {
		life = l;
		stateChanged();
	}

	/**
	 * @return the x
	 */
	public int getCooX() {
		return cooX;
	}
	
	public void setXY(int x,int y){
		previousCooX=cooX;
		previousCooY=cooY;
		cooX=x;
		cooY=y;
		setHasMoved(true);
	}

	/**
	 * @return the y
	 */
	public int getCooY() {
		return cooY;
	}
	
	
	public int getPreviousCooX() {
		return previousCooX;
	}

	public int getPreviousCooY() {
		return previousCooY;
	}
	
	public String getName(){
		return name;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public boolean isDisplayed() {
		return isDisplayed;
	}

	public void setDisplayed(boolean isDisplayed) {
		this.isDisplayed = isDisplayed;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void incrementNumberBombPlanted(){
		numberBombsPlanted++;
		stateChanged();
	}
	
	public int getNumberBombsPlanted(){
		return numberBombsPlanted;
	}
	
	public boolean canPlantBomb(){
		return (numberBombsPlanted<NUMBER_BOMB_MAX);
	}
	
	public void setObserver(Client c){
		observer=c;
	}
	
	public void setHasMoved(boolean h){
		hasMoved=h;
		stateChanged();
	}
	
	public boolean hasMoved(){
		return hasMoved;
	}
	
	public BufferedImage getBuff(){
		return avatarBuff;
	}
	
	/*public boolean isDead(){
		return dead;
	}*/
	
	

	public void stateChanged() {
		if(observer!=null){
			observer.sendPlayer(this);
		}
	}
}
