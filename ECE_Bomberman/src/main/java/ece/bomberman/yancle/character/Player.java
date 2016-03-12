package ece.bomberman.yancle.character;

import java.io.Serializable;
import java.util.HashSet;

import ece.bomberman.yancle.map.tiles.TileContainer;
import ece.bomberman.yancle.utility.Chronometer;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int life;
	private long timer;
	private int power;
	private int numberMaxOfBombe;
	private HashSet<Bomb> bombSet;
	private int arrayX;
	private int arrayY;
	private Orientation orientation = Orientation.EAST;
	private String name;
	private String color;
	private boolean isDisplayed;

	public static final double RADIUS = 20.0f;
	public static final double START_ANGLE = 45.0f;
	public static final double LENGTH = 270.0f;
	public static final ArcType ARC_TYPE= ArcType.ROUND;
	
	
	public Player(String c, String n){
		isDisplayed=false;
		life = 5;
		numberMaxOfBombe = 3;
		bombSet = new HashSet<Bomb>();
		timer = 4;
		power = 2;
		color=c;
		name=n;
	}
	
	public Arc getShape(){
		Arc shape = new Arc();
        shape.setRadiusX(RADIUS);
        shape. setRadiusY(RADIUS);
        shape.setStartAngle(START_ANGLE);
        shape.setLength(LENGTH);
		if(color.equals("Blue")){
	        shape.setFill(Color.BLUE);
		}else if(color.equals("Red")){
	        shape.setFill(Color.RED);
		}else if(color.equals("Green")){
	        shape.setFill(Color.GREEN);		
		}
        shape.setType(ARC_TYPE);
        return shape;
	}

	public Boolean poserBombe(){
		Boolean rtr = false;
		if(bombSet.size()<numberMaxOfBombe){
			bombSet.add(new Bomb(new Chronometer(System.currentTimeMillis()/1000),power));
			rtr = true;
		}
		return rtr;
	}
	
	public void bombExplosion(){
		for(Bomb bomb : bombSet){
			if(bomb.getTimer().compare(bomb.getSeuil())){
				bombSet.remove(bomb);
			}
		}
	}
	
	public void deplacement(Orientation or){
		Arc shape = getShape();
		if(or == Orientation.NORTH){
			setArrayY(arrayY-1);
			shape.setStartAngle(135);
		}
		else if(or == Orientation.SOUTH){
			setArrayY(arrayY+1);
			shape.setStartAngle(-45);
		}
		else if(or == Orientation.EAST){
			setArrayX(arrayX+1);
			shape.setStartAngle(45);
		}
		else if(or == Orientation.WEST){
			setArrayX(arrayX-1);
			shape.setStartAngle(225);
		}	
		
		orientation = or;
	}
	
	public void setOrientation(Orientation or){
		Arc shape = getShape();
		if(or == Orientation.NORTH){
			shape.setStartAngle(135);
		}
		else if(or == Orientation.SOUTH){
			shape.setStartAngle(-45);
		}
		else if(or == Orientation.EAST){
			shape.setStartAngle(45);
		}
		else if(or == Orientation.WEST){
			shape.setStartAngle(225);
		}	
		
		orientation=or;
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
	public HashSet<Bomb> getBombSet() {
		return bombSet;
	}

	/**
	 * @param bombSet the bombSet to set
	 */
	public void setBombSet(HashSet<Bomb> bombSet) {
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
	
}
