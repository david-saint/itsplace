package com.mincoms.book.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class MincomsException extends RuntimeException{
	private String message;
	 
	
 
	public MincomsException(String message) {
		this.message = message;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	
	
	
}
