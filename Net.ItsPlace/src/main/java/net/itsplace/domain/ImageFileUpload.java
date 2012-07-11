package net.itsplace.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ImageFileUpload {
	 private CommonsMultipartFile file;
	 private int fid;
	 private int id; //pk
	 
	 
	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	 
	
}
