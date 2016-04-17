package ece.bomberman.yancle.map;

import java.io.Serializable;
import java.util.ArrayList;

import ece.bomberman.yancle.map.tiles.TileContainer;
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
		listDestructibleWall = new DestructibleWallManager();
		
		for(int i=1;i<= MapPane.TILES_NUMBER_X-2;i++){
			for(int j=1;j<=MapPane.TILES_NUMBER_Y-2;j++){
				if(i%2!=0 || j%2!=0){
					listDestructibleWall.add(new Integer[]{i,j});
				}
			}
		}
		
		Integer[] coo;
		for(int i=0;i<listDestructibleWall.size();i++){
			coo=listDestructibleWall.get(i);
			if(coo[1]==1){
				listDestructibleWall.remove(i);
			}
		}
		
		for(int i=0;i<listDestructibleWall.size();i++){
			coo=listDestructibleWall.get(i);
			if(coo[1]==4){
				listDestructibleWall.remove(i);
			}
		}

		for(int i=0;i<listDestructibleWall.size();i++){
			coo=listDestructibleWall.get(i);
			if(coo[1]==10){
				listDestructibleWall.remove(i);
			}
		}
		
		for(int i=0;i<listDestructibleWall.size();i++){
			coo=listDestructibleWall.get(i);
			if(coo[1]==8){
				listDestructibleWall.remove(i);
			}
		}

		for(int i=0;i<listDestructibleWall.size();i++){
			coo=listDestructibleWall.get(i);
			if(coo[0]==7){
				listDestructibleWall.remove(i);
			}
		}

		for(int i=0;i<listDestructibleWall.size();i++){
			coo=listDestructibleWall.get(i);
			if(coo[0]==9){
				listDestructibleWall.remove(i);
			}
		}

		for(int i=0;i<listDestructibleWall.size();i++){
			coo=listDestructibleWall.get(i);
			if(coo[1]==9){
				listDestructibleWall.remove(i);
			}
		}
		
		for(int i=0;i<listDestructibleWall.size();i++){
			coo=listDestructibleWall.get(i);
			if(coo[0]==1){
				listDestructibleWall.remove(i);
			}
		}
		
		for(int i=0;i<listDestructibleWall.size();i++){
			coo=listDestructibleWall.get(i);
			if(coo[1]==11){
				listDestructibleWall.remove(i);
			}
		}
		
		for(int i=0;i<listDestructibleWall.size();i++){
			coo=listDestructibleWall.get(i);
			if(coo[0]==17){
				listDestructibleWall.remove(i);
			}
		}
		
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
