package net.itsplace.domain;

import java.util.List;

public class Stamped {
	List<Stamp> stampList;
	PlaceStamp placeStamp;
	
	public PlaceStamp getPlaceStamp() {
		return placeStamp;
	}
	public void setPlaceStamp(PlaceStamp placeStamp) {
		this.placeStamp = placeStamp;
	}
	public List<Stamp> getStampList() {
		return stampList;
	}
	public void setStampList(List<Stamp> stampList) {
		this.stampList = stampList;
	}
	
	
	
	
}
