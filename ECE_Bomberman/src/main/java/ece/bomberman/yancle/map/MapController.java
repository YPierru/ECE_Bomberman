package ece.bomberman.yancle.map;

import java.io.Serializable;
import java.util.ArrayList;

import ece.bomberman.yancle.player.Bomb;
import ece.bomberman.yancle.player.Player;
import sun.security.krb5.internal.crypto.Des;

public class MapController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Player> listCharacters;
	private DestructibleWallManager listDestructibleWall;
	private ExplosionCooManager listCooExplosion;
	private ArrayList<Bomb> listBombs;
	
	
	public MapController() {
		listCharacters = new ArrayList<>();
		listBombs = new ArrayList<>();
		listCooExplosion=new ExplosionCooManager();
		generateDestructibleWall();
	}
	
	private void generateDestructibleWall(){
		ArrayList<Integer> possibleValuesX = new ArrayList<>();
		ArrayList<Integer> possibleValuesY = new ArrayList<>();
		listDestructibleWall = new DestructibleWallManager();
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
	
	public void removeInvisibleBombs(){
		for(int i=0;i<listBombs.size();i++){
			if(listBombs.get(i).hasExploded()){
				listBombs.remove(i);
			}
		}
	}
	
	public void removeCooExplosion(){
		listCooExplosion=null;
	}
	
	public void addBomb(Bomb b){
		boolean flag=false;
		for(int i=0;i<listBombs.size();i++){
			if(listBombs.get(i).getIdentifier().equals(b.getIdentifier())){
				listBombs.remove(i);
				listBombs.add(i, b);
				flag=true;
				break;
			}
		}
				
		if(!flag){
			listBombs.add(b);
		}
	}
	
	public ArrayList<Bomb> getListBombs(){
		return listBombs;
	}
	
	public ArrayList<Player> getListPlayers(){
		return listCharacters;
	}
	
	public DestructibleWallManager getListDestructibleWall(){
		return listDestructibleWall;
	}
	
	public void setListDestructibleWall(DestructibleWallManager list){
		listDestructibleWall = list;
	}
	
	public ExplosionCooManager getListCooExplosion(){
		return listCooExplosion;
	}
	
	public void setListCooExplosion(ExplosionCooManager ecm){
		listCooExplosion=ecm;
	}

}
