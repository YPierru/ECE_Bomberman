package ece.bomberman.yancle.utilitaires;

public class Chronometre {
	private long Depart;
	
	public Chronometre(long Dep){
		setDepart(Dep);
	}
	
	public Boolean compare(long timer){
		Boolean rtr = false;
		if((Depart-System.currentTimeMillis()/1000) >= timer){
			rtr = true;
		}
		return rtr;
	}

	public long getDepart() {
		return Depart;
	}

	public void setDepart(long dep) {
		Depart = dep;
	}
}
