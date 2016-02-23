package ece.bomberman.yancle.personnage;

import java.util.HashSet;

public class Personnage {
	private int life;
	private int timer;
	private int power;
	private int numberMaxOfBombe;
	private HashSet<Bombe> bombSet;
	private int x;
	private int y;
	
	public Personnage(){
		life = 5;
		numberMaxOfBombe = 3;
		bombSet = new HashSet<Bombe>();
		timer = 4;
		power = 2;
	}

	public Boolean poserBombe(){
		Boolean rtr = false;
		if(bombSet.size()<numberMaxOfBombe){
			bombSet.add(new Bombe(timer,power,x,y));
			rtr = true;
		}
		return rtr;
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
	 * @param timer the timer to set
	 */
	public void setTimer(int timer) {
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
	public HashSet<Bombe> getBombSet() {
		return bombSet;
	}

	/**
	 * @param bombSet the bombSet to set
	 */
	public void setBombSet(HashSet<Bombe> bombSet) {
		this.bombSet = bombSet;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

}
