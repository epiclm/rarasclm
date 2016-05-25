package es.jclm.cs.rarasclm.service;

public class ServiceException extends Exception{

	private static final long serialVersionUID = 4030425294918572138L;

	public ServiceException(String message)
	{
		super(message);
	}
	
	public ServiceException(Exception e)
	{
	   super(e.getMessage());
	}
}
