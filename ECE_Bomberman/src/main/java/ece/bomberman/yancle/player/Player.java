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

public class Player implements Serializable{

	private static final long serialVersionUID = 1L;
	private int life;
	private int timer;//ms
	private int power;
	private int numberMaxOfBombe;
	private int speed=300;//ms
	private int cooX;
	private int cooY;
	private Orientation orientation = Orientation.EAST;
	private String name;
	transient BufferedImage avatarBuff;
	private boolean isDisplayed;
	transient Client observer;

	
	public Player(BufferedImage a, String n,Client c){
		isDisplayed=false;
		life = 5;
		numberMaxOfBombe = 30;
		timer = 1500;
		power = 2;
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
		stateChanged();
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
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * @return the timer
	 */
	public int getTimer() {
		return timer;
	}

	/**
	 * @return the power
	 */
	public int getPower() {
		return power;
	}

	/**
	 * @return the numberMaxOfBombe
	 */
	public int getNumberMaxOfBombe() {
		return numberMaxOfBombe;
	}

	/**
	 * @return the x
	 */
	public int getCooX() {
		return cooX;
	}
	
	public void setXY(int x,int y){
		cooX=x;
		cooY=y;
		stateChanged();
	}

	/**
	 * @return the y
	 */
	public int getCooY() {
		return cooY;
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
	
	public void stateChanged() {
		observer.sendPlayer(this);
	}
	
	public void setObserver(Client c){
		observer=c;
	}
	
}
