package ece.bomberman.yancle;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage stage;
    private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		stage.setResizable(false);
		stage.setTitle("Bomberman");
		

        try {
        	System.out.println();
	        // Load root layout from fxml file.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/StartFrame.fxml"));
	        rootLayout = (AnchorPane) loader.load();
	
	        // Show the scene containing the root layout.
	        Scene scene = new Scene(rootLayout);
	        stage.setScene(scene);
	        stage.show();
        
        }catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
