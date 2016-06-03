package es.jclm.cs.rarasclm.service;

public class ServiceRarasCLMException extends Exception{

	private static final long serialVersionUID = 4030425294918572138L;

	public ServiceRarasCLMException(String message)
	{
		super(message);
	}
	
	public ServiceRarasCLMException(Exception e)
	{
	   super(e.getMessage());
	}
}
