package ece.bomberman.yancle.player;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

import javax.imageio.ImageIO;

import ece.bomberman.yancle.Client;
import ece.bomberman.yancle.map.ExplosionCooManager;
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
	private int cooX;
	private int cooY;
	transient BufferedImage bombBuff;
	private boolean hasExploded=false;
	private boolean countDownStarted=false;
	transient Client observer;
	private ExplosionCooManager listCooExplosion;
	
	
	public static final int DURATION=500;//ms
	public static final int POWER=2;//unit of life
		
	public Bomb(int x, int y,Client o,UUID idO){
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
	}
	
	public void setCountdownStarted(boolean er){
		countDownStarted=er;
		sendNotification();
	}
	
	public boolean isCountdownStarted(){
		return countDownStarted;
	}
	
	public void setHasExploded(boolean he){
		hasExploded=he;
		sendListCooExplosionToClient();
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
	
	public void setListCooExplosion(ExplosionCooManager ecm){
		listCooExplosion=ecm;
	}
	
	public BombImage getBombImage(){
		BombImage bi = new BombImage(SwingFXUtils.toFXImage(bombBuff, null));
		bi.setX(cooX*TileContainer.SIZE_TILE);
		bi.setY(cooY*TileContainer.SIZE_TILE);
		return bi;
	}

	public void sendListCooExplosionToClient(){
		if(observer!=null){
			observer.sendListCooExplosion(listCooExplosion);
		}
	}
	
	public void sendNotification() {
		if(observer!=null){
			observer.sendBomb(this);
		}
	}


}
