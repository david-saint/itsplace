package net.itsplace.domain;

import javax.persistence.Entity;

@Entity(name="PGRPBASCD")
public class GroupBascd {

	private String grpcd;
	private String grpname;
	private String remark;
	private Boolean isDeleted;
	
	public String getGrpcd() {
		return grpcd;
	}
	public void setGrpcd(String grpcd) {
		this.grpcd = grpcd;
	}
	public String getGrpname() {
		return grpname;
	}
	public void setGrpname(String grpname) {
		this.grpname = grpname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
