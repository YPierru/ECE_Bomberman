package ece.bomberman.yancle.view;

import java.net.URL;
import java.util.ResourceBundle;

import ece.bomberman.yancle.Client;
import ece.bomberman.yancle.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class InputIPAndPortController implements Initializable{

	@FXML
	private Button join;
	
	@FXML
	private TextField ip;
	
	@FXML
	private TextField port;
	
	@FXML
	private TextField nickname;
	
	@FXML
	private ComboBox<String> color;
	
	private Main main;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Blue",
			        "Red",
			        "Green"
			    );
		color.setItems(options);
		color.setValue("Blue");
	}

	@FXML
	public void handleClick(){
		
		Client c=new Client(ip.getText(), Integer.parseInt(port.getText()),main,color.getValue(),color.getValue());
		new Thread(c).start();
		
	}
	
	public void setMain(Main m){
		main=m;
	}

	public void clickOK(){
		join.fire();
	}
}
