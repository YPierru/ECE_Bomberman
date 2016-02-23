package ece.bomberman.yancle.view;

import java.net.URL;
import java.util.ResourceBundle;

import ece.bomberman.yancle.Client;
import ece.bomberman.yancle.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InputIPAndPortController implements Initializable{

	@FXML
	private Button join;
	
	@FXML
	private TextField ip;
	
	@FXML
	private TextField port;
	
	private Main main;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	@FXML
	public void handleClick(){
		
		Client c=new Client(ip.getText(), Integer.parseInt(port.getText()));
		new Thread(c).start();
		
		main.displayMap();
		
	}
	
	public void setMain(Main m){
		main=m;
	}
	
}
