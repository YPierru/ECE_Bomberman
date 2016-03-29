package ece.bomberman.yancle.player;

import java.io.IOException;
import java.util.ArrayList;

import ece.bomberman.yancle.Main;
import ece.bomberman.yancle.view.InfoPlayerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InfoPlayerStage extends Stage {
	
	private Scene scene;
	private InfoPlayerController controller;

	public InfoPlayerStage(){
		super();

		try {
	        // Load root layout from fxml file.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/InfoPlayer.fxml"));
	        AnchorPane rootPane;
			rootPane = (AnchorPane) loader.load();
	        scene = new Scene(rootPane);
	        controller=loader.getController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setData(Player p, ArrayList<Player> lp){
		controller.setInfo(p, lp);
	}
	
	public void display(){
		setScene(scene);
		show();
		sizeToScene();
	}
	
}
