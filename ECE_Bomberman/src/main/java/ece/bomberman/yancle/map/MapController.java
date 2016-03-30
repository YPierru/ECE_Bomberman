package ece.bomberman.yancle.map;

import java.io.Serializable;
import java.util.ArrayList;

import ece.bomberman.yancle.player.Player;

public class MapController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Player> listCharacters;
	private ArrayList<Integer[]> listDestructibleWall;
	
	
	public MapController() {
		listCharacters = new ArrayList<>();
		generateDestructibleWall();
	}
	
	private void generateDestructibleWall(){
		ArrayList<Integer> possibleValuesX = new ArrayList<>();
		ArrayList<Integer> possibleValuesY = new ArrayList<>();
		listDestructibleWall = new ArrayList<>();
		//int nbDW =new Double(0.1*(MapPane.TILES_NUMBER_X*MapPane.TILES_NUMBER_Y)).intValue();
		int[] xyDW = new int[2];
			
		listDestructibleWall.add(new Integer[]{1,1});
		listDestructibleWall.add(new Integer[]{2,1});
		listDestructibleWall.add(new Integer[]{3,1});
		listDestructibleWall.add(new Integer[]{1,3});
		listDestructibleWall.add(new Integer[]{2,3});
		listDestructibleWall.add(new Integer[]{3,3});
		listDestructibleWall.add(new Integer[]{1,5});
		listDestructibleWall.add(new Integer[]{2,5});
		listDestructibleWall.add(new Integer[]{3,5});
		
	}
	
	public void addCharacter(Player p){
		boolean flag=false;
		for(int i=0;i<listCharacters.size();i++){
			if(listCharacters.get(i).getName().equals(p.getName())){
				listCharacters.remove(i);
				listCharacters.add(i, p);
				flag=true;
				break;
			}
		}
				
		if(!flag){
			listCharacters.add(p);
		}
		
	}
	
	public ArrayList<Player> getListPlayers(){
		return listCharacters;
	}
	
	public ArrayList<Integer[]> getListDestructibleWall(){
		return listDestructibleWall;
	}
	
	public void setListDestructibleWall(ArrayList<Integer[]> list){
		listDestructibleWall = list;
	}

}
