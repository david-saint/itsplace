package net.itsplace.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import net.itsplace.basecode.ServiceType;

@Entity(name="TestEnum")
public class TestEnum {

	@Id
	@Column(columnDefinition="VARCHAR(255) COMMENT '출판일자'")
	private String name;

	@Enumerated(EnumType.STRING)
	private ServiceType serviceType;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	
}
