package ece.bomberman.yancle.utility;

public class Chronometer {
	private long Depart;
	
	public Chronometer(long Dep){
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
