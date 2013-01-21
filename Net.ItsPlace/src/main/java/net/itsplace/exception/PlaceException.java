package net.itsplace.exception;

public class PlaceException extends RuntimeException{
	private String message;
	 
	//getter and setter methods
 
	public PlaceException(String message) {
		this.message = message;
	}
}
