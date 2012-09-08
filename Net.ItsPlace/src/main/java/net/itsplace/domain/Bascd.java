package net.itsplace.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="bascd")
public class Bascd {
	public interface AddBascd {}
	public interface EditBascd {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int no;
	
	@NotEmpty
	private String grpcd;
	private String grpName;
	private String basName;
	private String basecd;
	private String remark;
	private String isDelete;
	private String baseval;
	private String basekey;
	
	
	public String getBasekey() {
		return basekey;
	}
	public void setBasekey(String basekey) {
		this.basekey = basekey;
	}
	public String getBaseval() {
		return baseval;
	}
	public void setBaseval(String baseval) {
		this.baseval = baseval;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
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
	public String getBasName() {
		return basName;
	}
	public void setBasName(String basName) {
		this.basName = basName;
	}
	public String getBasecd() {
		return basecd;
	}
	public void setBasecd(String basecd) {
		this.basecd = basecd;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	


}
