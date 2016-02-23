package ece.bomberman.yancle;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	
	
	private int port;
	private ServerSocket socket;
	 
	
	public Server(int p){
		port=p;
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void start() throws IOException{
		socket = new ServerSocket(port);
		System.out.println("started !");
	}

}
