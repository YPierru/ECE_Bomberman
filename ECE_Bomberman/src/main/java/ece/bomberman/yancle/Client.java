package ece.bomberman.yancle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ece.bomberman.yancle.character.Player;
import ece.bomberman.yancle.map.MapController;
import ece.bomberman.yancle.map.MapPane;
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
		
		mapPane = new MapPane();
		main.displayMap(mapPane);
		
		player = new Player(color, pseudo);
		
		scene=main.getScene();
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				System.out.println(event.getCode());
				if(event.getCode() == KeyCode.UP) {
					player.setArrayY(player.getArrayY()-1);
					//map.moveCharacter(Orientation.NORTH);
					event.consume();
				}else if(event.getCode() == KeyCode.DOWN) {
					player.setArrayY(player.getArrayY()+1);
				    //map.moveCharacter(Orientation.SOUTH);
				    event.consume();
				}else if(event.getCode() == KeyCode.RIGHT){
					player.setArrayX(player.getArrayX()+1);
				    //map.moveCharacter(Orientation.EAST);
				    event.consume();
				}else if(event.getCode() == KeyCode.LEFT){
					player.setArrayX(player.getArrayX()-1);
				    //map.moveCharacter(Orientation.WEST);
				    event.consume();
				}
				
				sendPlayer();
			}
		});

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
		while (true) {
			try {
								
				o=reader.readObject();
				
				if(o instanceof MapController){
					//System.out.println("["+player.getName()+"]NEW MC RECEIVED");
					mapController=null;
					mapController=(MapController)o;
				}else{
					System.out.println("["+player.getName()+"]unknow received "+o.getClass());
				}
				
				
				Task<Void> task = new Task<Void>() {

				     @Override protected Void call() throws Exception {
                 
				    	 Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								mapPane.addCharacter(mapController.getListPlayers());
							}
						});
				         return null;
				     }
				 };
				 new Thread(task).start();
				 

				 if(!player.isDisplayed()){
					int[] xyPlayer = mapPane.getEmptyArrayXY();
					player.setArrayX(xyPlayer[0]);
					player.setArrayY(xyPlayer[1]);
					player.setDisplayed(true);
					sendPlayer();
				 }
				 

			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
