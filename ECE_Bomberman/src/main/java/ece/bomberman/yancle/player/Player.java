package ece.bomberman.yancle.player;

import java.io.Serializable;
import java.util.HashSet;

import ece.bomberman.yancle.utility.Chronometer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;

public class Player implements Serializable, IInteractiveShape{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int life;
	private long timer;
	private int power;
	private int numberMaxOfBombe;
	private int speed=300;//ms
	private HashSet<Bomb> bombSet;
	private int arrayX;
	private int arrayY;
	private int centerX;
	private int centerY;
	private Orientation orientation = Orientation.EAST;
	private String name;
	private String color;
	private boolean isDisplayed;
	private boolean positionUpdated;

	public static final double RADIUS = 20.0f;
	public static final double START_ANGLE = 45.0f;
	public static final double LENGTH = 270.0f;
	public static final ArcType ARC_TYPE= ArcType.ROUND;
	
	
	public Player(String c, String n){
		isDisplayed=false;
		positionUpdated=true;
		life = 5;
		numberMaxOfBombe = 3;
		bombSet = new HashSet<Bomb>();
		timer = 4;
		power = 2;
		color=c;
		name=n;
	}
	
	@Override
	public Shape getShape(){
		Arc shape = new Arc();
        shape.setRadiusX(RADIUS);
        shape.setRadiusY(RADIUS);
        shape.setLength(LENGTH);
		if(color.equals("Blue")){
	        shape.setFill(Color.BLUE);
		}else if(color.equals("Red")){
	        shape.setFill(Color.RED);
		}else if(color.equals("Green")){
	        shape.setFill(Color.GREEN);		
		}
        shape.setType(ARC_TYPE);
        shape.setCenterX(centerX);
        shape.setCenterY(centerY);
        
        if(orientation == Orientation.NORTH){
			shape.setStartAngle(135);
		}
		else if(orientation == Orientation.SOUTH){
			shape.setStartAngle(-45);
		}
		else if(orientation == Orientation.EAST){
			shape.setStartAngle(45);
		}
		else if(orientation == Orientation.WEST){
			shape.setStartAngle(225);
		}
        
        return shape;
	}

	public Boolean poserBombe(){
		Boolean rtr = false;
		if(bombSet.size()<numberMaxOfBombe){
			if(orientation.equals("EAST")){
				bombSet.add(new Bomb(new Chronometer(System.currentTimeMillis()/1000),power, arrayX+1, arrayY));
			}
			else if(orientation.equals("WEST")){
				bombSet.add(new Bomb(new Chronometer(System.currentTimeMillis()/1000),power, arrayX-1, arrayY));
			}
			else if(orientation.equals("NORTH")){
				bombSet.add(new Bomb(new Chronometer(System.currentTimeMillis()/1000),power, arrayX, arrayY+1));
			}
			else{
				bombSet.add(new Bomb(new Chronometer(System.currentTimeMillis()/1000),power, arrayX, arrayY-1));
			}
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
	@Override
	public int getArrayX() {
		return arrayX;
	}

	/**
	 * @param x the x to set
	 */
	@Override
	public void setArrayX(int x) {
		this.arrayX = x;
	}

	/**
	 * @return the y
	 */
	@Override
	public int getArrayY() {
		return arrayY;
	}

	/**
	 * @param y the y to set
	 */
	@Override
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
	@Override
	public int getCenterX() {
		return centerX;
	}
	@Override
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	@Override
	public int getCenterY() {
		return centerY;
	}
	@Override
	public void setCenterY(int centerY) {
		this.centerY = centerY;
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
	
	
}
