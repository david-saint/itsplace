package com.mincoms.domain;

import java.io.Serializable;

public enum Authority  implements Serializable{
	ADMIN(0), TEAMLEADER(1), WORKER(2), BOOKMANAGER;
	
	private int value;
	private Authority(){
		
	}
	private Authority(int authlevel){
		this.value = authlevel;
	}
}
