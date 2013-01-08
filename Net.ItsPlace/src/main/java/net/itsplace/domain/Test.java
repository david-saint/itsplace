package net.itsplace.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="test")
public class Test {

	@Id
	@Column(columnDefinition="VARCHAR(255) COMMENT '출판일자'")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
