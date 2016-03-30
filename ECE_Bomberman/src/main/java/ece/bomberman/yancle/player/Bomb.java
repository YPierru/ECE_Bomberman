package ece.bomberman.yancle.player;

import java.io.Serializable;

import ece.bomberman.yancle.utility.Chronometer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Bomb implements Serializable, IInteractiveShape{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Chronometer timer;
	private long seuil;
	private int power;
	private int arrayX;
	private int arrayY;
	private int centerX;
	private int centerY;
	
	
	public Bomb(){
		super();
	}
	
	public Bomb(Chronometer tim, int pow, int arrayX, int arrayY ){
		super();
		setArrayX(arrayX);
		setArrayY(arrayY);
		setTimer(tim);
		setPower(pow);
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
	public void setArrayY(int y) {
		this.arrayY = y;
	}

	
	@Override
	public Shape getShape() {
		Circle shape = new Circle();
		shape.setRadius(5);
		shape.setCenterX(centerX);
		shape.setCenterY(centerY);
		shape.setFill(Color.GREENYELLOW);
		return shape;
	}


	@Override
	public int getCenterX() {
		// TODO Auto-generated method stub
		return centerX;
	}

	@Override
	public int getCenterY() {
		// TODO Auto-generated method stub
		return centerY;
	}

	@Override
	public void setCenterX(int X) {
		// TODO Auto-generated method stub
		centerX = X;
	}

	@Override
	public void setCenterY(int Y) {
		// TODO Auto-generated method stub
		centerY = Y;
	}

}
