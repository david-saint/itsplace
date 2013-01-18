package net.itsplace.domain.dto;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import net.itsplace.domain.User;

/**
 * 가맹점 회원
 * @author dhkim
 *
 */
public class PlaceCustomer {
	private long stampedTotalCount;
	private Date stampedLastDate;
	private User user;
	
	public PlaceCustomer(){};
	
	public PlaceCustomer(long stampedTotalCount, Date stampedLastDate, User user) {
		
		this.stampedTotalCount = stampedTotalCount;
		this.stampedLastDate = stampedLastDate;
		this.user = user;
	}

	public long getStampedTotalCount() {
		return stampedTotalCount;
	}
	public void setStampedTotalCount(long stampedTotalCount) {
		this.stampedTotalCount = stampedTotalCount;
	}
	public Date getStampedLastDate() {
		return stampedLastDate;
	}
	public void setStampedLastDate(Date stampedLastDate) {
		this.stampedLastDate = stampedLastDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
