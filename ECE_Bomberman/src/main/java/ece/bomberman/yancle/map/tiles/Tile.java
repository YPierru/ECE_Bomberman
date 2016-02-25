package ece.bomberman.yancle.map.tiles;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public abstract class Tile extends Rectangle {
	
	public Tile(int x,int y,int w, int h){
		super(x,y,w,h);
		handleClick();
	}
	
	public abstract void initialize();
	//public abstract String getNameTile();
	
	public void handleClick(){
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("click on ");
			}
			
		});
	}

}
