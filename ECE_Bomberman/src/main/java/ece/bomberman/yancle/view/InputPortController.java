package ece.bomberman.yancle.view;

import java.net.URL;
import java.util.ResourceBundle;

import ece.bomberman.yancle.Server;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InputPortController implements Initializable{

	@FXML
	private Button start;
	
	@FXML
	private TextField port;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void handleClick(){
		System.out.println(port.getText());
		
		new Server(Integer.parseInt(port.getText()));
	}
	
}
