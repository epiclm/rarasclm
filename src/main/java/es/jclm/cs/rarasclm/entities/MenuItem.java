/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.entities;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuItem.
 */
public class MenuItem implements java.lang.Comparable<MenuItem>{
	
	/** The modulo. */
	private String modulo;
	
	/** The id. */
	private String id;
	
	/** The deno. */
	private String deno;	
	
	/** El Orden **/
	private int orden;
	

	/**
	 * Gets the modulo.
	 *
	 * @return the modulo
	 */
	public String getModulo() {
		return modulo;
	}
	
	/**
	 * Sets the modulo.
	 *
	 * @param modulo
	 *            the new modulo
	 */
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the deno.
	 *
	 * @return the deno
	 */
	public String getDeno() {
		return deno;
	}
	
	/**
	 * Sets the deno.
	 *
	 * @param deno
	 *            the new deno
	 */
	public void setDeno(String deno) {
		this.deno = deno;
	}
	
	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	
	@Override
	public boolean equals(Object m)
	{
		if(this.getId()==((MenuItem)m).getId())
			return true;
		else
			return false;
	}
	


	public int compareTo(MenuItem o) {
		// TODO Auto-generated method stub
		if (getOrden()>o.getOrden())
			return 1;
		else if(getOrden()==o.getOrden())
			return 0;
		else
			return -1;
	}

}
