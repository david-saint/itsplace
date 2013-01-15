package net.itsplace.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="CATEGORY")
public class Category {

	@Id
	private String title;
	
	private int displaySeq;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public int getDisplaySeq() {
		return displaySeq;
	}

	public void setDisplaySeq(int displaySeq) {
		this.displaySeq = displaySeq;
	}
	
}
