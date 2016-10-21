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
	
	private String modulo;
	private String id;
	private String deno;	
	private int orden;
	
	public String getModulo() {
		return modulo;
	}
	
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDeno() {
		return deno;
	}
	
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
		if(this.getId().equals(((MenuItem)m).getId()))
			return true;
		else
			return false;
	}
	

	public int compareTo(MenuItem o) {
		if (getOrden()>o.getOrden())
			return 1;
		else if(getOrden()==o.getOrden())
			return 0;
		else
			return -1;
	}

}
