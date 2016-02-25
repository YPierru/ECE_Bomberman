package ece.bomberman.yancle.character;

import java.util.HashSet;

import ece.bomberman.yancle.utility.Chronometer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Character extends Arc{
	private int life;
	private long timer;
	private int power;
	private int numberMaxOfBombe;
	private HashSet<Bomb> bombSet;
	private double x;
	private double y;
	
	
	public Character(){
		super();
		life = 5;
		numberMaxOfBombe = 3;
		bombSet = new HashSet<Bomb>();
		timer = 4;
		power = 2;
		initialize();
	}
	
	public void initialize(){
        setRadiusX(12.0f);
        setRadiusY(12.0f);
        setStartAngle(45.0f);
        setLength(270.0f);
        setFill(Color.BLUE);
        setType(ArcType.ROUND);
	}

	public Boolean poserBombe(){
		Boolean rtr = false;
		if(bombSet.size()<numberMaxOfBombe){
			bombSet.add(new Bomb(new Chronometer(System.currentTimeMillis()/1000),power,x,y));
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
	
	public void deplacement(String move){
		if(move.equals("N")){
			
		}
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
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
		setCenterX(x);
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
		setCenterY(y);
	}

}
