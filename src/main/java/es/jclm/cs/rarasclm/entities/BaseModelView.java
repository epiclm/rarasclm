/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.entities;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseModelView.
 */
public class BaseModelView implements IBaseModelView {

	/** The baseapp. */
	private String baseapp = "rarasclm";
	
	/** The menu model. */
	private MenuModel menuModel;
	
	/** The menus. */
	private List<MenuItem> menus;

	/**
	 * Gets the list menu item by modulo.
	 *
	 * @param modeloId
	 *            the modelo id
	 * @return the list menu item by modulo
	 */
	private List<MenuItem> getListMenuItemByModulo(String modeloId) {
		List<MenuItem> ret = new ArrayList<MenuItem>();
		for (MenuItem item : menus) {
			if (item.getModulo().equalsIgnoreCase(modeloId)) {
				ret.add(item);
			}
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see es.jclm.cs.rarasclm.entities.IBaseModelView#getBaseApp()
	 */
	public String getBaseApp() {
		return baseapp;
	}

	/**
	 * Instantiates a new base model view.
	 */
	public BaseModelView() {
		// Todos los items de menus
		
		menus = new ArrayList<MenuItem>();

		MenuItem mrarasClm = new MenuItem();
		mrarasClm.setModulo("enfermedades");
		mrarasClm.setDeno("Raras CLM");
		mrarasClm.setId("enfermedades/enfrara");
		menus.add(mrarasClm);

		MenuItem mrarasCie9Mc = new MenuItem();
		mrarasCie9Mc.setModulo("enfermedades");
		mrarasCie9Mc.setDeno("CIE9MC");
		mrarasCie9Mc.setId("enfermedades/cie9mc");
		menus.add(mrarasCie9Mc);

		MenuItem mrarasCie10 = new MenuItem();
		mrarasCie10.setModulo("enfermedades");
		mrarasCie10.setDeno("CIE10");
		mrarasCie10.setId("enfermedades/cie10");
		menus.add(mrarasCie10);

		menuModel = new MenuModel();
		menuModel.setNombreApp("rarasApp");

		// Módulo enfermedades
		MenuModulo enfermedadesModulo = new MenuModulo();
		enfermedadesModulo.setId("enfermedades");
		enfermedadesModulo.setDeno("Enfermedades");
		enfermedadesModulo.setItemsMenu(getListMenuItemByModulo("enfermedades"));
		menuModel.getModulosMenu().add(enfermedadesModulo);

		// Módulo pacientes
		MenuModulo pacientesModulo = new MenuModulo();
		pacientesModulo.setId("paciente");
		pacientesModulo.setDeno("Pacientes");
		pacientesModulo.setItemsMenu(getListMenuItemByModulo("caso"));
		menuModel.getModulosMenu().add(pacientesModulo);

		// Módulo casos
		MenuModulo casosModulo = new MenuModulo();
		casosModulo.setId("caso");
		casosModulo.setDeno("Casos");
		casosModulo.setItemsMenu(getListMenuItemByModulo("caso"));
		menuModel.getModulosMenu().add(casosModulo);

		// Módulo admin
		MenuModulo adminModulo = new MenuModulo();
		adminModulo.setId("admin");
		adminModulo.setDeno("Administración");
		adminModulo.setItemsMenu(getListMenuItemByModulo("caso"));
		menuModel.getModulosMenu().add(adminModulo);

	}

	/* (non-Javadoc)
	 * @see es.jclm.cs.rarasclm.entities.IBaseModelView#getMenuModel()
	 */
	public MenuModel getMenuModel() {
		return menuModel;
	}

	@Override
	public DatosAuxiliaresCacheados getCache() {
		return null;
	}

	@Override
	public MensajeResultado getMensaje() {
		return null;
	}

	@Override
	public void setMensaje(MensajeResultado mensaje) {
		
	}

}
