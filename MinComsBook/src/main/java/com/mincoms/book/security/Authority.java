package com.mincoms.book.security;

public enum Authority {
	ADMIN(0), TEAMLEADER(1), WORKER(2);
	
	private int value;
	private Authority(){
		
	}
	private Authority(int authlevel){
		this.value = authlevel;
	}
}
