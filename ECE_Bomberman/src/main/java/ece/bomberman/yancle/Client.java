package ece.bomberman.yancle;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

import ece.bomberman.yancle.map.MapController;
import ece.bomberman.yancle.map.MapPane;
import ece.bomberman.yancle.player.Bomb;
import ece.bomberman.yancle.player.InfoPlayerStage;
import ece.bomberman.yancle.player.Orientation;
import ece.bomberman.yancle.player.Player;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Client implements Runnable {

	private String ip;
	private int port;
	private ObjectOutputStream writer;
	private ObjectInputStream reader;
	private MapController mapController;
	private MapPane mapPane;
	private Main main;
	private Player player;
	private Scene scene;
	private InfoPlayerStage frameInfo;
	private UUID identifier;
	
	public Client(String i, int p, Main m, String pseudo, BufferedImage avatar) {
		ip = i;
		port = p;
		main = m;
		identifier=UUID.randomUUID();
		try {
			Socket socket = new Socket(ip, port);
			writer = new ObjectOutputStream(socket.getOutputStream());
			reader = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frameInfo = new InfoPlayerStage();
		
		mapPane = new MapPane();
		main.displayMap(mapPane);
		
		player = new Player(avatar, pseudo,this);
		
		scene=main.getScene();
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if(event.getCode() == KeyCode.UP) {
					movePlayer(Orientation.NORTH);					
					event.consume();
				}else if(event.getCode() == KeyCode.DOWN) {
					movePlayer(Orientation.SOUTH);
				    event.consume();
				}else if(event.getCode() == KeyCode.RIGHT){
					movePlayer(Orientation.EAST);
				    event.consume();
				}else if(event.getCode() == KeyCode.LEFT){
					movePlayer(Orientation.WEST);
				    event.consume();
				}else if(event.getCode() == KeyCode.SPACE){
					plantBomb();
					event.consume();
				}
				
				//sendPlayer();
			}
		});

	}
	
	public void movePlayer(Orientation or){
		if(mapPane.isMovePossible(player, or)){
			player.deplacement(or);
		}else{
			player.setOrientation(or);
		}
	}
	
	public void plantBomb(){
		if(mapPane.isMovePossible(player, player.getOrientation())){
			int x=player.getCooX();
			int y=player.getCooY();

			if(player.getOrientation()==Orientation.EAST){
				x++;
			}
			else if(player.getOrientation()==Orientation.WEST){
				x--;
			}
			else if(player.getOrientation()==Orientation.NORTH){
				y--;
			}
			else{
				y++;
			}
			
			Bomb b = new Bomb(player.getPower(), x, y, player.getTimer(), this, identifier);
			sendBomb(b);
		}
	}
	
	
	public void sendBomb(Bomb b){
		try {
			writer.reset();
			writer.writeObject(b);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendPlayer(Player p){
		player=p;
		try {
			writer.reset();
			writer.writeObject(player);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Object o;
		//int iteration = 1;
		while (true) {
			try {
							
				o=reader.readObject();
				
				if(o instanceof MapController){
					mapController=(MapController)o;
				}else{
					System.out.println("["+player.getName()+"]unknow received "+o.getClass());
				}
				
				
				for(Player p : mapController.getListPlayers()){
					if(p.getName().equals(player.getName())){
						player=p;
						player.setObserver(this);
						break;
					}
				}
				
				for(Bomb b : mapController.getListBombs()){
					if(b.getIdentifierObserver().equals(identifier)){
						b.setObserver(this);
					}
				}
				
				 //new Thread(new UpdateFrameInfo()).start();
				 
				 new Thread(new UpdateMapPane()).start();
				 
				 

				 if(!player.isDisplayed()){
					 try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					int[] xyPlayer = mapPane.getEmptyArrayXY();
					player.setDisplayed(true);
					player.setXY(xyPlayer[0], xyPlayer[1]);
				 }
				 

				 //iteration ++;
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private class UpdateFrameInfo extends Task<Void>{

		@Override
		protected Void call() throws Exception {
			
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					frameInfo.setData(player, mapController.getListPlayers());
					frameInfo.display();
				}
			});
			return null;
		}
		
	}
	
	private class UpdateMapPane extends Task<Void>{

		@Override
		protected Void call() throws Exception {
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					mapPane.displayDestructibleWalls(mapController.getListDestructibleWall());
					mapPane.displayCharactersImage(mapController.getListPlayers());
					mapPane.displayBombs(mapController.getListBombs());
					int i=0;
					for(Bomb b : mapController.getListBombs()){
						System.out.println("bomb#"+i+" hasExploded="+b.hasExploded()+" isExplosionRunning="+b.isExplosionRunning());
					}
				}
			});
			return null;
		}
		
	}
	
}
