package net.itsplace.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Bascd {
	public interface AddBascd {}
	public interface EditBascd {}
	
	private int no;
	@NotEmpty
	private String grpCd;
	@NotEmpty
	private String grpName;
	private String basName;
	private String baseCd;
	private String remark;
	private String isDelete;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getGrpCd() {
		return grpCd;
	}
	public void setGrpCd(String grpCd) {
		this.grpCd = grpCd;
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
	public String getBaseCd() {
		return baseCd;
	}
	public void setBaseCd(String baseCd) {
		this.baseCd = baseCd;
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
