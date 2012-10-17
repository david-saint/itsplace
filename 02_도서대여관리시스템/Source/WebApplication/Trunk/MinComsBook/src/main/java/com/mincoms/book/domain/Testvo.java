package com.mincoms.book.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Testvo {
	private int id;
	private String title;
	private BookInfo bookInfo;
	public Testvo(int id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	public Testvo(int id, BookInfo bookInfo) {
		super();
		this.id = id;
		this.bookInfo = bookInfo;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	   
	  
}
