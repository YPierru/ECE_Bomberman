package ece.bomberman.yancle.view;

import java.net.URL;
import java.util.ResourceBundle;

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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void handleClick(){
		System.out.println(ip.getText() + " "+port.getText());
	}
	
}
