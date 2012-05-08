package net.itsplace.domain;

import java.util.List;

public class Stamped {
	List<Stamp> stampList;
	int stampid;
	String theme;
	public List<Stamp> getStampList() {
		return stampList;
	}
	public void setStampList(List<Stamp> stampList) {
		this.stampList = stampList;
	}
	public int getStampid() {
		return stampid;
	}
	public void setStampid(int stampid) {
		this.stampid = stampid;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	
	
}
