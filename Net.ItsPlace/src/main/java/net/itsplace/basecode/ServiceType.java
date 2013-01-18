package net.itsplace.basecode;

import java.util.ArrayList;
import java.util.Collection;

public enum ServiceType {
	Normal, Stamp, Premier; //가맹점 서비스종 // 
	
    private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
  
}
