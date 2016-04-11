package ece.bomberman.yancle;

import java.io.IOException;
import java.io.Serializable;

import ece.bomberman.yancle.map.MapPane;
import ece.bomberman.yancle.map.tiles.TileContainer;
import ece.bomberman.yancle.view.InputIPAndPortController;
import ece.bomberman.yancle.view.InputPortController;
import ece.bomberman.yancle.view.StartFrameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage stage;
    private StackPane stackLayout;
    private Scene scene;
    private StartFrameController sfc;
    private InputIPAndPortController iipc;
    private InputPortController ipc;
	
    public static boolean QUICK=false;
    
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		initRootLayout();
		displayStartFrame();
		
		if(QUICK){
			sfc.clickCreate();
			ipc.clickStart();
			sfc.clickJoin();
			iipc.clickOK();
		}
		
	}
	
	
	public void initRootLayout(){
        try {
	        // Load root layout from fxml file.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
	        stackLayout = (StackPane) loader.load();
	        scene = new Scene(stackLayout);
        
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
            
            startFrame.setPrefSize(300, 300);

            stackLayout.setPrefSize(300, 300);
            stackLayout.getChildren().clear();
            stackLayout.getChildren().add(startFrame);
    		stage.setTitle("Bomberman");

            // Set person overview into the center of root layout.
            //stackLayout.setCenter(startFrame);
            
            // Give the controller access to the main app.
            sfc = loader.getController();
            sfc.setMain(this);
    		showFrame();
            
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

            inputIpAndPort.setPrefSize(330, 300);

            stackLayout.setPrefSize(330, 300);
            stackLayout.getChildren().clear();
            stackLayout.getChildren().add(inputIpAndPort);
    		stage.setTitle("Bomberman - Join server");

            // Set person overview into the center of root layout.
            //stackLayout.setCenter(inputIpAndPort);
            
            // Give the controller access to the main app.
            iipc = loader.getController();
            iipc.setMain(this);
    		showFrame();
            
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

            inputPort.setPrefSize(380, 150);

            stackLayout.setPrefSize(380, 150);
            stackLayout.getChildren().clear();
            stackLayout.getChildren().add(inputPort);
    		stage.setTitle("Bomberman - Start server");

            // Set person overview into the center of root layout.
            //stackLayout.setCenter(inputIpAndPort);
            
            ipc = loader.getController();
            ipc.setMain(this);
    		showFrame();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void displayMap(MapPane mp){

       /*scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				  System.out.println(event.getCode());
			  if(event.getCode() == KeyCode.UP) {
		            //map.moveCharacter(Orientation.NORTH);
		          event.consume();
		        }else if(event.getCode() == KeyCode.DOWN) {
		            //map.moveCharacter(Orientation.SOUTH);
		            event.consume();
		        }else if(event.getCode() == KeyCode.RIGHT){
		            //map.moveCharacter(Orientation.EAST);
		            event.consume();
		        }else if(event.getCode() == KeyCode.LEFT){
		            //map.moveCharacter(Orientation.WEST);
		            event.consume();
		        }
			}
		});*/
        
        stackLayout.setPrefSize(TileContainer.SIZE_TILE*MapPane.TILES_NUMBER_X, TileContainer.SIZE_TILE*MapPane.TILES_NUMBER_Y);
        stackLayout.getChildren().clear();
        stackLayout.getChildren().add(mp);
        AnchorPane t = new AnchorPane();
        if(t instanceof Serializable){
        	System.out.println("kikoo");
        }
        
		stage.setTitle("Bomberman - Map");
		showFrame();

	}
	
	public Scene getScene(){
		return scene;
	}
	
	public void showFrame(){
        stage.setScene(scene);
        stage.show();
        stage.sizeToScene();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
