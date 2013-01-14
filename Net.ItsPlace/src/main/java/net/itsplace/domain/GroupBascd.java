package net.itsplace.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="PGRPBASCD")
public class GroupBascd {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String grpcd;
	private String grpName;
	private String remark;
	private Boolean isDelete;
	
	public String getGrpcd() {
		return grpcd;
	}
	public void setGrpcd(String grpcd) {
		this.grpcd = grpcd;
	}
	
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
