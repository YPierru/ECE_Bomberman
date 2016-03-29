package ece.bomberman.yancle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ece.bomberman.yancle.map.MapController;
import ece.bomberman.yancle.player.Player;

public abstract class ConnectionHandler implements Runnable {

	private Socket socket;
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	
	public ConnectionHandler(Socket s){
		socket = s;
		try{
			reader = new ObjectInputStream(socket.getInputStream());
			writer = new ObjectOutputStream(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void sendMapController(MapController mc){
		try {
			writer.reset();
			writer.writeObject(mc);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Object o;
		while(true){
	
			
			try {
				o=reader.readObject();
			
				if(o instanceof Player){
					addPlayer((Player)o);
				}

				broadcastMapController();
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public abstract void broadcastMapController();
	public abstract void addPlayer(Player p);
	
}
