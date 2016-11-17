/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.jclm.cs.rarasclm.entities.AccionResultado;
import es.jclm.cs.rarasclm.entities.IBaseModel;
import es.jclm.cs.rarasclm.entities.MensajeResultado;
import es.jclm.cs.rarasclm.entities.RouteParameters;
import es.jclm.cs.rarasclm.entities.UserRarasClm;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.service.UsuarioService;
import es.jclm.cs.rarasclm.util.RarasClmConstantes;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseController.
 */
public class BaseController {
	
	
	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	/** The base. */
	@Autowired
	public IBaseModel base;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired 
	private UsuarioService usuarioService;
	
//	@InitBinder
//	//Necasario para analizar las fechas de input date en html5
//	public void initBinder(WebDataBinder binder) {
//		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		    dateFormat.setLenient(false);
//		    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//	}
//		
	/**
	 * Gets the base model.
	 *
	 * @return the base model
	 */
	@ModelAttribute("baseModel")
	public IBaseModel getBaseModel(HttpServletRequest request) {
		//para enviar la baseApp o contextPath a la vista
		String baseApp = request.getContextPath().replace("/", "");
		MensajeResultado mensaje = (MensajeResultado)request.getSession().getAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION);
		base.setMensaje(mensaje);
		base.setBaseapp(baseApp);
		return base;
	}
		
	@ModelAttribute("userCLM")
	private UserRarasClm getUserCLM() {
		UserRarasClm user = (UserRarasClm)request.getSession().getAttribute("userCLM");
		return user;
	}
	
	@ModelAttribute("resultado")
	public AccionResultado getResultado() {
		AccionResultado ret = new AccionResultado();
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
		
		//Obtiene la routa mediante las anotaciones del controlador
		RouteParameters ret = new RouteParameters();
		String servletPath = request.getServletPath();
		
		String[] route = servletPath.split("/");
		
		
		if(route.length>1 && route[1]!=null && route[1].equals("admin")) {
			ret.setVisibilidad("admin");
			if(route.length>2 && route[2]!=null)
				ret.setModulo(route[2]);
			if(route.length>3 && route[3]!=null)
				ret.setEntidad(route[3]);
			if(route.length>4 && route[4]!=null)
				ret.setAccion(route[4]);
			if(route.length>5 && route[5]!=null)
				ret.setId(route[5]);
			if(route.length>6 && route[6]!=null)
				ret.setId(route[6]);
			if(route.length>7 && route[7]!=null)
				ret.setId2(route[7]);	
		}	else {
				if(route.length>1 && route[1]!=null)
					ret.setModulo(route[1]);
				else
					ret.setModulo("inicio");
			ret.setVisibilidad("usuario");
			if(route.length>2 && route[2]!=null)
				ret.setEntidad(route[2]);
			if(route.length>3 && route[3]!=null)
				ret.setAccion(route[3]);
			if(route.length>4 && route[4]!=null)
				ret.setId(route[4]);
			if(route.length>5 && route[5]!=null)
				ret.setId(route[5]);
			if(route.length>6 && route[6]!=null)
				ret.setId(route[6]);
		}
		
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("/");
		String baseApp = request.getContextPath().replace("/", "");
		base.setBaseapp(baseApp);
		ret.setBaseUrl(String.format("/%s%s",base.getBaseApp(),sb.toString()));
		
		return ret;
					
	}
	
	@RequestMapping(value = "/logout")
	public String logout() {
		UserRarasClm userClm = getUserCLM();
		userClm.setUltimoAcceso(new Date());
		try {
			if(!userClm.getGenerar())
				usuarioService.Actualizar(userClm);
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(),ex);
		}
		return "redirect:j_spring_security_logout";
	}
	
	
	@RequestMapping(value = "/resetMensaje", method = RequestMethod.POST)
	public @ResponseBody String resetMensaje() {
		try {
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION, null);
			return "ok";
		} catch(Exception ex) {
			return "ko";
		}
	}
	
}
