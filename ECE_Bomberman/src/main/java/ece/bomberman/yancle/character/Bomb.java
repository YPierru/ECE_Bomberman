package ece.bomberman.yancle.character;

import ece.bomberman.yancle.utility.Chronometer;

public class Bomb {
	private Chronometer timer;
	private long seuil;
	private int power;
	private int x;
	private int y;
	
	public Bomb(Chronometer tim, int pow, int x, int y){
		setTimer(tim);
		setPower(pow);
		setX(x);
		setY(y);
	}
	
	/**
	 * @return the timer
	 */
	public Chronometer getTimer() {
		return timer;
	}

	/**
	 * @param tim the timer to set
	 */
	public void setTimer(Chronometer tim) {
		this.timer = tim;
	}

	/**
	 * @return the seuil
	 */
	public long getSeuil() {
		return seuil;
	}

	/**
	 * @param seuil the seuil to set
	 */
	public void setSeuil(long seuil) {
		this.seuil = seuil;
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
