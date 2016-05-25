package es.jclm.cs.rarasclm.dao;

public class NotFoundException extends Exception {
	
	private static final long serialVersionUID = -407530109586842069L;

	public NotFoundException(String message)
	{
		super(message);
	}
	
	public NotFoundException(Exception e)
	{
	   super(e.getMessage());
	}
}
