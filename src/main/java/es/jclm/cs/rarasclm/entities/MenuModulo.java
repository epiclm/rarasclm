/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.entities;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuModulo.
 */
public class MenuModulo implements java.lang.Comparable<MenuModulo>{
	
	/** The id. */
	private String id;
	
	/** The deno. */
	private String deno;
	
	/** The items menu. */
	private List<MenuItem> itemsMenu;
	
	/** El Orden **/
	private int orden;
	

	/**
	 * Instantiates a new menu modulo.
	 */
	public MenuModulo() {
		id = "";
		deno = "";
		itemsMenu = new ArrayList<MenuItem>();
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

	/**
	 * Gets the items menu.
	 *
	 * @return the items menu
	 */
	public List<MenuItem> getItemsMenu() {
		return itemsMenu;
	}

	/**
	 * Sets the items menu.
	 *
	 * @param itemsMenu
	 *            the new items menu
	 */
	public void setItemsMenu(List<MenuItem> itemsMenu) {
		this.itemsMenu = itemsMenu;
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
		if(this.getId()==((MenuModulo)m).getId())
			return true;
		else
			return false;
	}
	

	public int compareTo(MenuModulo o) {
		// TODO Auto-generated method stub
		if (getOrden()>o.getOrden())
			return 1;
		else if(getOrden()==o.getOrden())
			return 0;
		else
			return -1;
	}

}
