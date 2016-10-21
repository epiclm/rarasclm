package es.jclm.cs.rarasclm.dao;

public class BusquedaDAOException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6451879538158257355L;

	public BusquedaDAOException(String message) {
		super(message);
	}
	
	public BusquedaDAOException(Exception e) {
	   super(e.getMessage());
	}
}
