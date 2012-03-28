package com.myplace.partner.franchiser.image;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FranchiserImage {

	private String imgno;
	private int fid;
	private String dispseq;
	private String fileName;
	private CommonsMultipartFile fileData; //업로드파일	
	private Date inpdate;
	public String getImgno() {
		return imgno;
	}
	public void setImgno(String imgno) {
		this.imgno = imgno;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getDispseq() {
		return dispseq;
	}
	public void setDispseq(String dispseq) {
		this.dispseq = dispseq;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	public Date getInpdate() {
		return inpdate;
	}
	public void setInpdate(Date inpdate) {
		this.inpdate = inpdate;
	}
	
	
}
