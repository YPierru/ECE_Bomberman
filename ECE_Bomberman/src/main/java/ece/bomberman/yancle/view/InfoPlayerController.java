package ece.bomberman.yancle.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ece.bomberman.yancle.player.Player;
import javafx.embed.swing.SwingFXUtils;
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
	private Label remainingBombs;
	
	@FXML
	private Label life;
	
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

		if(listPlayers.size()>1){
			players.setText(playersName.substring(0,playersName.length()-2));
		}else{
			players.setText(playersName.toString());
		}
		
		me.setText(player.getName());	
		avatar.setImage(SwingFXUtils.toFXImage(player.getBuff(), null));
		remainingBombs.setText((Player.NUMBER_BOMB_MAX-player.getNumberBombsPlanted())+"/"+Player.NUMBER_BOMB_MAX);
		if(player.getLife()>0){
			life.setText(player.getLife()+"/"+Player.STARTING_LIFE);
		}else{
			life.setText("DEAD");
		}
	}
	
}
