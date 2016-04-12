package ece.bomberman.yancle.player;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import ece.bomberman.yancle.map.tiles.TileContainer;
import ece.bomberman.yancle.utility.Chronometer;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Bomb implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Chronometer timer;
	private long seuil;
	private int power;
	private int arrayX;
	private int arrayY;
	transient BufferedImage bombBuff;
	private Player owner;
	
	
	public Bomb(){
		super();
	}
	
	public Bomb(Chronometer tim, int pow, int arrayX, int arrayY, long seuil,Player p){
		try {
			bombBuff=ImageIO.read(Bomb.class.getResourceAsStream("bomb.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setArrayX(arrayX);
		setArrayY(arrayY);
		setTimer(tim);
		setPower(pow);
		setSeuil(seuil);
		owner=p;
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1500);
					sendNotification();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(bombBuff, "png", out); // png is lossless
        
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        bombBuff = ImageIO.read(in);
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
	
	public BombImage getBombImage(){
		BombImage bi = new BombImage(SwingFXUtils.toFXImage(bombBuff, null));
		bi.setX(arrayX*TileContainer.SIZE_TILE);
		bi.setY(arrayY*TileContainer.SIZE_TILE);
		return bi;
	}

	public void sendNotification() {
		owner.refresh();
	}

}
