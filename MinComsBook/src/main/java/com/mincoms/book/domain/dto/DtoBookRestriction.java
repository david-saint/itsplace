package com.mincoms.book.domain.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

import com.mincoms.book.domain.BookInfo.AddBook;
import com.mincoms.validation.ExistUsers;

public class DtoBookRestriction {

	public interface AddDtoBookRestriction {}
	public interface EditDtoBookRestriction {}
	
	@NotEmpty
	private String restrictReason;
	
	
	@NotEmpty
	@ExistUsers
	private List<String> restrictUsers;
	

	public String getRestrictReason() {
		return restrictReason;
	}


	public void setRestrictReason(String restrictReason) {
		this.restrictReason = restrictReason;
	}


	public List<String> getRestrictUsers() {
		return restrictUsers;
	}


	public void setRestrictUsers(List<String> restrictUsers) {
		this.restrictUsers = restrictUsers;
	}


	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
