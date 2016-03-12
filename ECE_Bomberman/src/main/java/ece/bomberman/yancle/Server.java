package ece.bomberman.yancle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ece.bomberman.yancle.character.Player;
import ece.bomberman.yancle.map.MapController;

public class Server implements Runnable{
	
	
	private int port;
	private ServerSocket socket;
	private ArrayList<ConnectionHandler> listPlayers;
	private MapController mapController;
	 
	
	public Server(int p){
		port=p;
		listPlayers = new ArrayList<>();
		mapController = new MapController();
	}
	


	@Override
	public void run() {
		try {
			socket = new ServerSocket(port);
			System.out.println("server started !");
			
			while(true){
				Socket s=socket.accept();
				ConnectionHandler player = new ConnectionHandler(s) {
					
					@Override
					public void broadcastMapController() {
						for(ConnectionHandler ch : listPlayers){
							ch.sendMapController(mapController);
						}
					}
					
					@Override
					public void addPlayer(Player p){
						mapController.addCharacter(p);
					}
				};
				
				listPlayers.add(player);
				
				
				for(ConnectionHandler ch : listPlayers){
					ch.sendMapController(mapController);
				}
				
				new Thread(player).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
