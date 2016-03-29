package ece.bomberman.yancle.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ece.bomberman.yancle.player.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class InfoPlayerController implements Initializable {
	
	@FXML
	private Label players;
	
	@FXML
	private Label me;

	@FXML
	private ImageView avatar;
	
	@FXML
	private Label speed;
	
	@FXML
	private Label bomb;
	
	@FXML
	private Label impact;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setInfo(Player player,ArrayList<Player> listPlayers){
		StringBuilder playersName = new StringBuilder();
		
		for(Player p : listPlayers){
			if(!p.getName().equals(player.getName())){
				playersName.append(p.getName());
				playersName.append(", ");
			}
		}
		players.setText(playersName.toString());
		
		me.setText(player.getName());
		speed.setText(Integer.toString(player.getSpeed()));		
		
	}
	
}
