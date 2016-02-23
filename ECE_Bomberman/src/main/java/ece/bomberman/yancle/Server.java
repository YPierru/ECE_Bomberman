package ece.bomberman.yancle;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable{
	
	
	private int port;
	private ServerSocket socket;
	 
	
	public Server(int p){
		port=p;
	}
	


	@Override
	public void run() {
		try {
			socket = new ServerSocket(port);
			System.out.println("started !");
			
			while(true){
				socket.accept();
				System.out.println("new client");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
