/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.entities;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuModel.
 */
public class MenuModel  {
	
	/** The nombre app. */
	private String nombreApp;
	
	/** The modulos menu. */
	private List<MenuModulo> modulosMenu;

	/**
	 * Instantiates a new menu model.
	 */
	public MenuModel() {
		nombreApp = "";
		modulosMenu = new ArrayList<MenuModulo>();
	}

	/**
	 * Gets the modulo menu by id.
	 *
	 * @param id
	 *            the id
	 * @return the modulo menu by id
	 */
	public MenuModulo getModuloMenuById(String id) {
		for (MenuModulo moduloMenu : modulosMenu) {
			if (moduloMenu.getId().equalsIgnoreCase(id)) {
				return moduloMenu;
			}
		}
		return null;
	}

	/**
	 * Gets the nombre app.
	 *
	 * @return the nombre app
	 */
	public String getNombreApp() {
		return nombreApp;
	}

	/**
	 * Sets the nombre app.
	 *
	 * @param nombreApp
	 *            the new nombre app
	 */
	public void setNombreApp(String nombreApp) {
		this.nombreApp = nombreApp;
	}

	/**
	 * Gets the modulos menu.
	 *
	 * @return the modulos menu
	 */
	public List<MenuModulo> getModulosMenu() {
		return modulosMenu;
	}

	/**
	 * Sets the modulos menu.
	 *
	 * @param modulosMenu
	 *            the new modulos menu
	 */
	public void setModulosMenu(List<MenuModulo> modulosMenu) {
		this.modulosMenu = modulosMenu;
	}
	

	


}
