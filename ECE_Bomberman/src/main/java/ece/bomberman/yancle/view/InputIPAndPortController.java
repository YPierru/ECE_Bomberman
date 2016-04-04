package ece.bomberman.yancle.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import ece.bomberman.yancle.Client;
import ece.bomberman.yancle.Main;
import ece.bomberman.yancle.map.tiles.TileContainer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

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
	private Button chooseAvatar;
	
	@FXML
	private ImageView avatar;
	
	private Main main;
	private BufferedImage avatarBuffer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		join.setDisable(true);
	}

	@FXML
	public void handleClick(){
		
		Client c=new Client(ip.getText(), Integer.parseInt(port.getText()),main,nickname.getText(),avatarBuffer);
		new Thread(c).start();
		
	}
	
	@FXML
	public void handleChooseAvatar(){
		FileChooser fChooser = new FileChooser();
		File file;
		BufferedImage tmp;
        avatarBuffer=null;
		boolean error = true;
		
		while(error){
			file = fChooser.showOpenDialog(main.getScene().getWindow());
			if(file!=null){
				try {
					tmp = ImageIO.read(file);
					avatarBuffer = resizeImage(tmp,tmp.getType());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        if(avatarBuffer!=null){
		            avatar.setImage(new Image("file:"+file.getAbsolutePath()));
		            error=false;
		            join.setDisable(false);
		        }
		        else{
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("File is not an image");
					alert.setHeaderText(null);
					alert.setContentText("The file you've selected is not an image");
					alert.showAndWait();
		        }
			}else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("File is nul");
				alert.setHeaderText(null);
				alert.setContentText("The file you've selected is nul");
				alert.showAndWait();
			}
		}
	}
	
	private BufferedImage resizeImage(BufferedImage originalImage, int type){
		BufferedImage resizedImage = new BufferedImage(TileContainer.SIZE_TILE, TileContainer.SIZE_TILE, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, TileContainer.SIZE_TILE, TileContainer.SIZE_TILE, null);
		g.dispose();
		return resizedImage;
	}
	
	public void setMain(Main m){
		main=m;
	}

	public void clickOK(){
		join.fire();
	}
}
