package ece.bomberman.yancle;

import java.io.IOException;

import ece.bomberman.yancle.map.MapGame;
import ece.bomberman.yancle.view.InputIPAndPortController;
import ece.bomberman.yancle.view.InputPortController;
import ece.bomberman.yancle.view.StartFrameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage stage;
    private StackPane stackLayout;
	
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		
		initRootLayout();
		//displayStartFrame();
		displayMap();
	}
	
	
	public void initRootLayout(){
        try {
	        // Load root layout from fxml file.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
	        stackLayout = (StackPane) loader.load();
        
        }catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public void displayStartFrame(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/StartFrame.fxml"));
            AnchorPane startFrame = (AnchorPane) loader.load();
            
            stackLayout.getChildren().clear();
            stackLayout.getChildren().add(startFrame);
    		stage.setTitle("Bomberman");

            // Set person overview into the center of root layout.
            //stackLayout.setCenter(startFrame);
            
            // Give the controller access to the main app.
            StartFrameController controller = loader.getController();
            controller.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public void displayInputIpAndPort(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/InputIPAndPort.fxml"));
            AnchorPane inputIpAndPort = (AnchorPane) loader.load();

            stackLayout.getChildren().clear();
            stackLayout.getChildren().add(inputIpAndPort);
    		stage.setTitle("Bomberman - Join server");

            // Set person overview into the center of root layout.
            //stackLayout.setCenter(inputIpAndPort);
            
            // Give the controller access to the main app.
            InputIPAndPortController controller = loader.getController();
            controller.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void displayInputPort(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/InputPort.fxml"));
            AnchorPane inputPort = (AnchorPane) loader.load();

            stackLayout.getChildren().clear();
            stackLayout.getChildren().add(inputPort);
    		stage.setTitle("Bomberman - Start server");

            // Set person overview into the center of root layout.
            //stackLayout.setCenter(inputIpAndPort);
            
            InputPortController controller = loader.getController();
            controller.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void displayMap(){

        MapGame map = new MapGame();
        map.setPrefSize(800, 600);
        stackLayout.getChildren().clear();
        stackLayout.getChildren().add(map);
		stage.setTitle("Bomberman - Map");
		showFrame();

		
            
	}
	
	public void showFrame(){
        Scene scene = new Scene(stackLayout);
        stage.setScene(scene);
        stage.show();
        stage.sizeToScene();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
