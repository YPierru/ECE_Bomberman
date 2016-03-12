package ece.bomberman.yancle.character;

import ece.bomberman.yancle.utility.Chronometer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bomb extends Circle{
	private Chronometer timer;
	private long seuil;
	private int power;
	private double x;
	private double y;
	
	
	public Bomb(){
		super();
		initialize();
	}
	
	public Bomb(Chronometer tim, int pow){
		super();
		initialize();
		setTimer(tim);
		setPower(pow);
	}
	
	public void initialize(){
		setRadius(10);
		setFill(Color.RED);
	}
	
	public void moveTo(double x, double y){
		setX(x);
		setY(y);
		setCenterX(x);
		setCenterY(y);
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
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
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
	}

}
