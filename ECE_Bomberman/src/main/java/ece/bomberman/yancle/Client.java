package ece.bomberman.yancle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ece.bomberman.yancle.map.MapController;
import ece.bomberman.yancle.map.MapPane;
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
	
	public Client(String i, int p, Main m, String pseudo, String color) {
		ip = i;
		port = p;
		main = m;
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
		
		player = new Player(color, pseudo);
		
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
				}
				
				sendPlayer();
			}
		});

	}
	
	public void movePlayer(Orientation or){
		if(mapPane.isMovePossible(player, or)){
			player.deplacement(or);
		}else{
			player.setOrientation(or);
		}
		player.setPositionUpdated(true);
	}

	public void sendPlayer(){
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
		int iteration = 1;
		while (true) {
			try {
							
				o=reader.readObject();
				System.out.println(iteration);
				
				if(o instanceof MapController){
					mapController=(MapController)o;
				}else{
					System.out.println("["+player.getName()+"]unknow received "+o.getClass());
				}
				
				
				for(Player p : mapController.getListPlayers()){
					if(p.getName().equals(player.getName())){
						player=p;
						break;
					}
				}
				
				 new Thread(new UpdateFrameInfo()).start();
				 
				 new Thread(new UpdateMapPane()).start();
				 

				 if(!player.isDisplayed()){
					int[] xyPlayer = mapPane.getEmptyArrayXY();
					player.setArrayX(xyPlayer[0]);
					player.setArrayY(xyPlayer[1]);
					player.setDisplayed(true);
					sendPlayer();
				 }
				 

				 iteration ++;
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
					mapPane.displayCharacters(mapController.getListPlayers());
					mapPane.displayDestructibleWalls(mapController.getListDestructibleWall());
				}
			});
			return null;
		}
		
	}
	
}
