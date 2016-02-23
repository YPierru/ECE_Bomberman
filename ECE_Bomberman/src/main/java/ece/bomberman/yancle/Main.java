package ece.bomberman.yancle;

import java.io.IOException;

import ece.bomberman.yancle.view.InputIPAndPortController;
import ece.bomberman.yancle.view.InputPortController;
import ece.bomberman.yancle.view.StartFrameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage stage;
    private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		//stage.setResizable(false);
		stage.setTitle("Bomberman");
		
		initRootLayout();
		displayStartFrame();
	}
	
	
	public void initRootLayout(){
        try {
	        // Load root layout from fxml file.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
	        rootLayout = (BorderPane) loader.load();
	
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootLayout);
	        stage.setScene(scene);
	        stage.show();
        
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

            // Set person overview into the center of root layout.
            rootLayout.setCenter(startFrame);
            
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

            // Set person overview into the center of root layout.
            rootLayout.setCenter(inputIpAndPort);
            
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
            AnchorPane inputIpAndPort = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(inputIpAndPort);
            
            InputPortController controller = loader.getController();
            controller.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void displayMap(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/MapGame.fxml"));
            GridPane map = (GridPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(map);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
