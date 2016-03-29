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
	
	
	public MapController() {
		listCharacters = new ArrayList<>();
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

}
