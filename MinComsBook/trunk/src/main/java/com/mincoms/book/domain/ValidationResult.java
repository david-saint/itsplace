package com.mincoms.book.domain;

public class ValidationResult {

	private String field;
	private String code;
	private String message;
	
	public ValidationResult(){}
	public ValidationResult(String field, String code, String message) {
		
		this.field = field;
		this.code = code;
		this.message = message;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
