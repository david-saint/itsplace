package net.itsplace.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Authcode {

	@NotEmpty
	private int fid;
	@NotEmpty
	private String authCode;
	@NotEmpty
	private String newAuthCode;
	@NotEmpty
	private String confirmAuthCode;
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getNewAuthCode() {
		return newAuthCode;
	}
	public void setNewAuthCode(String newAuthCode) {
		this.newAuthCode = newAuthCode;
	}
	public String getConfirmAuthCode() {
		return confirmAuthCode;
	}
	public void setConfirmAuthCode(String confirmAuthCode) {
		this.confirmAuthCode = confirmAuthCode;
	}
	
	
}
