package es.jclm.cs.rarasclm.dao;


public class UnableToSaveException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8472995591548179928L;

	public UnableToSaveException(String message)
	{
		super(message);
	}
	
	public UnableToSaveException(Exception e)
	{
	   super(e.getMessage());
	}
}
