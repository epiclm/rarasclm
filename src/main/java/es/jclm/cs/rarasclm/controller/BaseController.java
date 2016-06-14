/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jclm.cs.rarasclm.entities.AccionRespuesta;
import es.jclm.cs.rarasclm.entities.BaseModelViewReflex;
import es.jclm.cs.rarasclm.entities.IBaseModelView;
import es.jclm.cs.rarasclm.entities.RouteParameters;
import es.jclm.cs.rarasclm.entities.UserRarasCLM;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseController.
 */
public class BaseController {


	/** The base. */
	@Autowired
	public BaseModelViewReflex base;
	
	@Autowired
	private HttpServletRequest request;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	
	/**
	 * Gets the base model.
	 *
	 * @return the base model
	 */
	@ModelAttribute("baseModel")
	public BaseModelViewReflex getBaseModel(HttpServletRequest request) {
		//para enviar la baseApp o contextPath a la vista
		String baseApp = request.getContextPath().replace("/", "");
		base.setBaseapp(baseApp);
		return base;
	}
	
	
	@ModelAttribute("userCLM")
	private UserRarasCLM getUserCLM() {
		UserRarasCLM user = (UserRarasCLM)request.getSession().getAttribute("userCLM");
		return user;
	}
	
	@ModelAttribute("resultado")
	public AccionRespuesta getResultado() {
		AccionRespuesta ret = new AccionRespuesta();
		return ret;
	}
	
	//ruta url {modulo}/{entidad}/{accion}/{ID|json}/{ID2?}	
	//ID es el id entidad principal
	//ID2 es el id de la entidad secundaria a actualizar o asociaar a la primaria
	
	/**
	 * Gets the route.
	 *
	 * @return the route
	 */
	@ModelAttribute("route")
	public RouteParameters getRoute() {
		RouteParameters ret = new RouteParameters();
		Annotation[] anotaciones = this.getClass().getDeclaredAnnotations();
		for(Annotation a : anotaciones)
		{
			if(a.annotationType()==RequestMapping.class)
			{
				//obtengo el path de la url mediante el valor de la anotación RequestMapping del
				//contralador que gestiona la petición (Alternativa inyectar el request)
				String[] path =((org.springframework.web.bind.annotation.RequestMapping)a).value();
				String[] route = path[0].split("/");
				if(route.length>1 && route[1]!=null)
					ret.setModulo(route[1]);
				if(route.length>2 && route[2]!=null)
					ret.setEntidad(route[2]);
				if(route.length>3 && route[3]!=null)
					ret.setAccion(route[3]);
				if(route.length>4 && route[4]!=null)
					ret.setId(route[4]);
			}
		} 
		StringBuilder sb = new StringBuilder();
		if(ret.getModulo()!=null)
		{
			sb.append("/");
			sb.append(ret.getModulo());
		}
		if(ret.getEntidad()!=null)
		{
			sb.append("/");
			sb.append(ret.getEntidad());
		}
		sb.append("/");
		String baseApp = request.getContextPath().replace("/", "");
		base.setBaseapp(baseApp);
		ret.setBaseUrl(String.format("/%s%s",base.getBaseApp(),sb.toString()));
		return ret;
	}
	
}
