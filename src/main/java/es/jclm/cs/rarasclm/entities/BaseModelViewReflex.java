package es.jclm.cs.rarasclm.entities;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.controller.BaseController;
import es.jclm.cs.rarasclm.util.ClassFinder;
import es.jclm.cs.rarasclm.util.DatosAuxiliaresCacheados;

public class BaseModelViewReflex implements IBaseModel {

	/** The log. */
	static Log log = LogFactory.getLog(BaseModelViewReflex.class.getName());

	/** The baseapp. */
	private String baseapp;

	/** The menu model. */
	private MenuModel menuModel;
	
	/** The modulo model. */
	private List<MenuModulo> modulos;

	/** The menus. */
	private List<MenuItem> menus;

	/** paquete de búsqueda de controladores **/
	private final static String baseControllerPackage = "es.jclm.cs.rarasclm.controller";
	
	private MensajeResultado mensaje;
	
	@Autowired
	DatosAuxiliaresCacheados cacheAuxiliares;

	public BaseModelViewReflex() {

		log.info("Creando la estructura de la aplicación (IModelView) mediante reflexión " + baseControllerPackage);

		menus = new ArrayList<MenuItem>();
		modulos = new ArrayList<MenuModulo>();
		menuModel = new MenuModel();
		
		List<Class<?>> classes = ClassFinder.find(baseControllerPackage);

		for (Class<?> clase : classes) {
			log.info("Examinando: "+clase.getName());
			if (clase.getSuperclass().getName() == BaseController.class.getName()) {
				Annotation annotation = clase.getAnnotation(RequestMapping.class);
				if(annotation==null)
					continue;
				String[] value = ((RequestMapping)annotation).value();
				if(value.length>0 && value[0]!=null) {
					String[] path = value[0].split("/");
					if(path.length==2) {
						MenuModulo menuModulo = new MenuModulo();
						MenuItem itemMenu = new MenuItem();
						RarasClmItemMenu rarasClmItemMenu = clase.getAnnotation(RarasClmItemMenu.class);
						RarasClmItemModulo rarasClmItemModulo = clase.getAnnotation(RarasClmItemModulo.class);
						if(rarasClmItemMenu==null || rarasClmItemModulo==null)
							continue;
						itemMenu.setId(path[1]);
						itemMenu.setDeno(rarasClmItemMenu.caption());
						itemMenu.setModulo(rarasClmItemMenu.modulo());
						itemMenu.setOrden(rarasClmItemMenu.orden());
						menuModulo.setId(rarasClmItemModulo.modulo());
						menuModulo.setDeno(rarasClmItemModulo.deno());
						menuModulo.setOrden(rarasClmItemModulo.orden());
						menuModulo.setBaseUrl(path[1]);
						if(!modulos.contains(menuModulo))
							modulos.add(menuModulo);
						if(!menus.contains(itemMenu))
							menus.add(itemMenu);
					}
					if(path.length==3) {
						RarasClmItemMenu rarasClmItemMenu = clase.getAnnotation(RarasClmItemMenu.class);
						RarasClmItemModulo rarasClmItemModulo = clase.getAnnotation(RarasClmItemModulo.class);
						MenuItem itemMenu = new MenuItem();
						MenuModulo menuModulo = new MenuModulo();
						if(rarasClmItemMenu!=null) {
							itemMenu.setId(path[1]+"/"+path[2]);
							itemMenu.setDeno(rarasClmItemMenu.caption());
							itemMenu.setModulo(rarasClmItemMenu.modulo());
							itemMenu.setOrden(rarasClmItemMenu.orden());
						}
						if(rarasClmItemModulo!=null) {
							menuModulo.setBaseUrl(path[1]+"/"+path[2]);
							menuModulo.setId(rarasClmItemModulo.modulo());
							menuModulo.setDeno(rarasClmItemModulo.deno());
							menuModulo.setOrden(rarasClmItemModulo.orden());
						}
						if(rarasClmItemMenu!=null && !menus.contains(itemMenu))
							menus.add(itemMenu);
						if(rarasClmItemModulo!=null && !modulos.contains(menuModulo))
							modulos.add(menuModulo);
					}
					if(path.length==0) {
						MenuModulo menuModulo = new MenuModulo();
						MenuItem itemMenu = new MenuItem();
						RarasClmItemMenu rarasClmItemMenu = clase.getAnnotation(RarasClmItemMenu.class);
						RarasClmItemModulo rarasClmItemModulo = clase.getAnnotation(RarasClmItemModulo.class);
						itemMenu.setId("/");
						itemMenu.setDeno(rarasClmItemMenu.caption());
						itemMenu.setModulo(rarasClmItemMenu.modulo());
						itemMenu.setOrden(rarasClmItemMenu.orden());
						menuModulo.setId(rarasClmItemModulo.modulo());
						menuModulo.setDeno(rarasClmItemModulo.deno());
						menuModulo.setOrden(rarasClmItemModulo.orden());
						menuModulo.setBaseUrl("");
						if(!modulos.contains(menuModulo))
							modulos.add(menuModulo);
					}
				}
			}
		}
		
		//JAVA -> FEO
		Collections.sort(modulos, new Comparator<MenuModulo>() {
			public int compare(MenuModulo o1, MenuModulo o2) {
				return o1.compareTo(o2);
			}
		});
		
		
		
		for(MenuModulo m : modulos)
		{
			m.setItemsMenu(getListMenuItemByModulo(m.getId()));
			Collections.sort(m.getItemsMenu(),new Comparator<MenuItem>() {
			//JAVA -> MUY PERO QUE MUY FEO :-( Linq c# I'll miss you :-(
			public int compare(MenuItem o1, MenuItem o2) {
				return o1.compareTo(o2);
			}});
		}
			
		menuModel.setModulosMenu(modulos);
		menuModel.setNombreApp(baseapp);
	} 

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
	 * @see es.jclm.cs.rarasclm.entities.IBaseModel#getBaseapp()
	 */
	@Override
	public String getBaseapp() {
		return baseapp;
	}

	/* (non-Javadoc)
	 * @see es.jclm.cs.rarasclm.entities.IBaseModel#setBaseapp(java.lang.String)
	 */
	@Override
	public void setBaseapp(String baseapp) {
		this.baseapp = baseapp;
	}


	/* (non-Javadoc)
	 * @see es.jclm.cs.rarasclm.entities.IBaseModel#getMenuModel()
	 */
	@Override
	public MenuModel getMenuModel() {
		return menuModel;
	}

	/* (non-Javadoc)
	 * @see es.jclm.cs.rarasclm.entities.IBaseModel#getBaseApp()
	 */
	@Override
	public String getBaseApp() {
		return this.baseapp;
	}

	/* (non-Javadoc)
	 * @see es.jclm.cs.rarasclm.entities.IBaseModel#getCache()
	 */
	@Override
	public DatosAuxiliaresCacheados getCache() {
		return this.cacheAuxiliares;
	}

	/* (non-Javadoc)
	 * @see es.jclm.cs.rarasclm.entities.IBaseModel#getMensaje()
	 */
	@Override
	public MensajeResultado getMensaje() {
		return this.mensaje;
	}

	/* (non-Javadoc)
	 * @see es.jclm.cs.rarasclm.entities.IBaseModel#setMensaje(es.jclm.cs.rarasclm.entities.MensajeResultado)
	 */
	@Override
	public void setMensaje(MensajeResultado mensaje) {
		this.mensaje = mensaje;
	}
	

}
