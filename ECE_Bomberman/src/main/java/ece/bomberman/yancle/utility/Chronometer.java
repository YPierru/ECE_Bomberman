package ece.bomberman.yancle.utility;

import java.io.Serializable;

public class Chronometer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long Depart;
	
	public Chronometer(long Dep){
		setDepart(Dep);
	}
	
	public boolean compare(long timer){
		boolean rtr = false;
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
