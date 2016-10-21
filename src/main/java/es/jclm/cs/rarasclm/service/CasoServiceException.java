package es.jclm.cs.rarasclm.service;

public class CasoServiceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6423475224958968348L;

	public CasoServiceException(String message) {
		super(message);
	}
	
	public CasoServiceException(Exception e) {
	   super(e.getMessage());
	}
	
}
