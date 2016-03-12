package ece.bomberman.yancle.view;

import java.net.URL;
import java.util.ResourceBundle;

import ece.bomberman.yancle.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class StartFrameController implements Initializable {

	@FXML
	private Button joinServerButton;
	
	@FXML
	private Button startServerButton;
	
	private Main main;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void actionStartServer(){
		main.displayInputPort();
	}
	
	@FXML
	public void actionJoinServer(){
		main.displayInputIpAndPort();
	}
	
	
	public void setMain(Main m){
		main=m;
	}
	
	public void clickCreate(){
		startServerButton.fire();
	}
	
	public void clickJoin(){
		joinServerButton.fire();
	}

}
