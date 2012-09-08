package net.itsplace.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="test")
public class Test {

	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
