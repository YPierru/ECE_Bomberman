package ece.bomberman.yancle.player;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

import javax.imageio.ImageIO;

import ece.bomberman.yancle.Client;
import ece.bomberman.yancle.map.tiles.TileContainer;
import ece.bomberman.yancle.utility.Chronometer;
import javafx.embed.swing.SwingFXUtils;

public class Bomb implements Serializable{
	/**
	 * 
	 */
	
	private UUID identifier;
	private UUID identifierObserver;
	private static final long serialVersionUID = 1L;
	private int duration;//timer before explosion in ms
	private int power;
	private int cooX;
	private int cooY;
	transient BufferedImage bombBuff;
	private boolean hasExploded=false;
	private boolean explosionRunning=false;
	transient Client observer;
		
	public Bomb(int pow, int x, int y, int dur ,Client o,UUID idO){
		identifier = UUID.randomUUID();
		try {
			bombBuff=ImageIO.read(Bomb.class.getResourceAsStream("bomb.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		observer=o;
		identifierObserver=idO;
		cooX=x;
		cooY=y;
		power=pow;
		duration=dur;
	}
	
	public void setExplosionRunning(boolean er){
		explosionRunning=er;
		sendNotification();
	}
	
	public boolean isExplosionRunning(){
		return explosionRunning;
	}
	
	public void setHasExploded(boolean he){
		hasExploded=he;
		sendNotification();
	}
	
	public boolean hasExploded(){
		return hasExploded;
	}
	
	public UUID getIdentifier(){
		return identifier;
	}
	
	public UUID getIdentifierObserver(){
		return identifierObserver;
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
	 * @return the seuil
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param seuil the seuil to set
	 */
	public void setDuration(int d) {
		this.duration = d;
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
	public int getCooX() {
		return cooX;
	}

	/**
	 * @return the y
	 */
	public int getCooY() {
		return cooY;
	}
	
	public void setObserver(Client c){
		observer=c;
	}
	
	public BombImage getBombImage(){
		BombImage bi = new BombImage(SwingFXUtils.toFXImage(bombBuff, null));
		bi.setX(cooX*TileContainer.SIZE_TILE);
		bi.setY(cooY*TileContainer.SIZE_TILE);
		return bi;
	}

	public void sendNotification() {
		if(observer!=null){
			observer.sendBomb(this);
		}
	}


}
