package net.itsplace.exception;

public class ItsPlaceException extends RuntimeException{
	private String message;
	 
	//getter and setter methods
 
	public ItsPlaceException(String message) {
		this.message = message;
	}
}
