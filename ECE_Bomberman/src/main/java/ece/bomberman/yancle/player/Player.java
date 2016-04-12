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
	private long timer;
	private int power;
	private int numberMaxOfBombe;
	private int speed=300;//ms
	private ArrayList<Bomb> bombSet;
	private int arrayX;
	private int arrayY;
	private Orientation orientation = Orientation.EAST;
	private String name;
	transient BufferedImage avatarBuff;
	private boolean isDisplayed;
	private boolean positionUpdated;
	transient Client observer;

	
	public Player(BufferedImage a, String n,Client c){
		isDisplayed=false;
		positionUpdated=true;
		life = 5;
		numberMaxOfBombe = 30;
		bombSet = new ArrayList<Bomb>();
		timer = 1000;
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
		av.setX(arrayX*TileContainer.SIZE_TILE);
		av.setY(arrayY*TileContainer.SIZE_TILE);
		return av;
	}

	public boolean poserBombe(){
		boolean rtr = false;
		if(bombSet.size()<numberMaxOfBombe){
			Bomb bomb = null;
			if(orientation==Orientation.EAST){
				bomb = new Bomb(new Chronometer(System.currentTimeMillis()/1000),power, arrayX+1, arrayY, timer,this);
			}
			else if(orientation==Orientation.WEST){
				bomb = new Bomb(new Chronometer(System.currentTimeMillis()/1000),power, arrayX-1, arrayY, timer,this);
			}
			else if(orientation==Orientation.NORTH){
				bomb = new Bomb(new Chronometer(System.currentTimeMillis()/1000),power, arrayX, arrayY-1, timer,this);
			}
			else{
				bomb = new Bomb(new Chronometer(System.currentTimeMillis()/1000),power, arrayX, arrayY+1, timer,this);
			}
			bombSet.add(bomb);
			rtr = true;
		}
		return rtr;
	}
	
	public void deplacement(Orientation or){
		if(or == Orientation.NORTH){
			setArrayY(arrayY-1);
		}
		else if(or == Orientation.SOUTH){
			setArrayY(arrayY+1);
		}
		else if(or == Orientation.EAST){
			setArrayX(arrayX+1);
		}
		else if(or == Orientation.WEST){
			setArrayX(arrayX-1);
		}	
		setOrientation(or);
	}
	
	public void setOrientation(Orientation or){
		orientation=or;
		positionUpdated=true;
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
	public long getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(long timer) {
		this.timer = timer;
	}

	/**
	 * @return the power
	 */
	public int getPower() {
		return power;
	}

	/**
	 * @param power the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * @return the numberMaxOfBombe
	 */
	public int getNumberMaxOfBombe() {
		return numberMaxOfBombe;
	}

	/**
	 * @param numberMaxOfBombe the numberMaxOfBombe to set
	 */
	public void setNumberMaxOfBombe(int numberMaxOfBombe) {
		this.numberMaxOfBombe = numberMaxOfBombe;
	}

	/**
	 * @return the bombSet
	 */
	public ArrayList<Bomb> getBombSet() {
		return bombSet;
	}

	/**
	 * @param bombSet the bombSet to set
	 */
	public void setBombSet(ArrayList<Bomb> bombSet) {
		this.bombSet = bombSet;
	}

	/**
	 * @return the x
	 */
	public int getArrayX() {
		return arrayX;
	}

	/**
	 * @param x the x to set
	 */
	public void setArrayX(int x) {
		this.arrayX = x;
	}

	/**
	 * @return the y
	 */
	public int getArrayY() {
		return arrayY;
	}

	/**
	 * @param y the y to set
	 */
	public void setArrayY(int y) {
		this.arrayY = y;
	}
	
	public void setName(String n){
		name=n;
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

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isPositionUpdated() {
		return positionUpdated;
	}

	public void setPositionUpdated(boolean positionUpdated) {
		this.positionUpdated = positionUpdated;
	}

	public void refresh() {
		System.out.println("bombe explosée");
		bombSet.remove(0);
		observer.sendPlayer(this);
	}
	
	public void setObserver(Client c){
		observer=c;
	}
	
}
