package es.jclm.cs.rarasclm.dao;


public class UnableToSaveException extends Exception{
	
	public UnableToSaveException(String message)
	{
		super(message);
	}
	
	public UnableToSaveException(Exception e)
	{
	   super(e.getMessage());
	}
}
