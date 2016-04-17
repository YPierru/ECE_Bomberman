package ece.bomberman.yancle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ece.bomberman.yancle.map.DestructibleWallManager;
import ece.bomberman.yancle.map.ExplosionCooManager;
import ece.bomberman.yancle.map.MapController;
import ece.bomberman.yancle.player.Bomb;
import ece.bomberman.yancle.player.Player;

public class Server implements Runnable{
	
	
	private int port;
	private ServerSocket socket;
	private ArrayList<ConnectionHandler> listConnections;
	private MapController mapController;
	 
	
	public Server(int p){
		port=p;
		listConnections = new ArrayList<>();
		mapController = new MapController();
	}

	@Override
	public void run() {
		try {
			socket = new ServerSocket(port);
			System.out.println("server started !");
			
			while(true){
				Socket s=socket.accept();
				ConnectionHandler connection = new ConnectionHandler(s) {
					
					@Override
					public void broadcastMapController() {
						mapController.removeInvisibleBombs();
						for(ConnectionHandler ch : listConnections){
							ch.sendMapController(mapController);
						}
					}
					
					@Override
					public void addPlayer(Player p){
						mapController.addCharacter(p);
					}
					
					@Override
					public void addBomb(Bomb b){
						mapController.addBomb(b);
					}
					

					@Override
					public void updateListDestructibleWalls(DestructibleWallManager list){
						mapController.setListDestructibleWall(list);
					}
					
					@Override
					public void setExplosionCooManager(ExplosionCooManager list){
						mapController.setListCooExplosion(list);
					}
				};
				
				listConnections.add(connection);
				
				
				for(ConnectionHandler ch : listConnections){
					ch.sendMapController(mapController);
				}
				
				new Thread(connection).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
