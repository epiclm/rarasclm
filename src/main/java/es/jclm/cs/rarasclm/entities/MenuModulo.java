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
	
	private String id;
	
	private String deno;
	
	private List<MenuItem> itemsMenu;
	
	private int orden;
	
	private String baseUrl;
	

	public MenuModulo() {
		id = "";
		deno = "";
		itemsMenu = new ArrayList<MenuItem>();
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

	public List<MenuItem> getItemsMenu() {
		return itemsMenu;
	}

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
		if(m==null)
			return false;
		if(this.getId().equals(((MenuModulo)m).getId()))
			return true;
		else
			return false;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
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
