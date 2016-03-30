package ece.bomberman.yancle.player;

import javafx.scene.shape.Shape;

public interface IInteractiveShape {
	public abstract Shape getShape();
	public abstract int getArrayX();
	public abstract int getArrayY();
	public abstract void setArrayX(int X);
	public abstract void setArrayY(int Y);
	public abstract int getCenterX();
	public abstract int getCenterY();
	public abstract void setCenterX(int X);
	public abstract void setCenterY(int Y);
}
