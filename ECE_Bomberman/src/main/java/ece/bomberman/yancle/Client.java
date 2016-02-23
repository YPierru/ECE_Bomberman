package ece.bomberman.yancle;

import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{

	private String ip;
	private int port;
	
	public Client(String ip, int port){
		this.ip=ip;
		this.port=port;
	}
	
	@Override
	public void run(){
		try {
			Socket socket = new Socket(ip,port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
