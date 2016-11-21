/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.CasoRevisionUsuario;
import es.jclm.cs.rarasclm.entities.MensajeResultado;
import es.jclm.cs.rarasclm.entities.MensajeTipo;
import es.jclm.cs.rarasclm.entities.UserRarasClm;
import es.jclm.cs.rarasclm.service.CasoRevisionService;
import es.jclm.cs.rarasclm.util.DatosAuxiliaresCacheados;


/**
 * The Class IndexController.
 */
@Controller
@RequestMapping("/")
@RarasClmItemMenu(caption="Inicio",deno="Inicio",modulo="inicio",orden=1)
@RarasClmItemModulo(caption="Inicio",deno="Inicio",modulo="inicio",orden=0)
@SessionAttributes("enfrara")
public class IndexController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	private static final String OBJETO_REVISIONES_POR_HACER_SESION = "revisados_porhacer";
	private static final String REVISADO_CASOS_SESION = "revisados_casos";
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CasoRevisionService casoRevisionService;
	
	@Autowired
	private DatosAuxiliaresCacheados cache;
	
		@SuppressWarnings("unchecked")
		@RequestMapping("/")
		private String index(Model model)
		{
			UserRarasClm user = (UserRarasClm)model.asMap().get("userCLM");
			String idUsuario = user.getUsername();
			if(request.getSession().getAttribute(REVISADO_CASOS_SESION)==null) {
				request.getSession().setAttribute(REVISADO_CASOS_SESION, new Boolean(true));
			}
			try {
				if(request.getSession().getAttribute(REVISADO_CASOS_SESION)==null) {
					request.getSession().setAttribute(REVISADO_CASOS_SESION, new Boolean(false));
				}
				boolean revisadoCasosDesdeLaUltimaVez = ((Boolean) request.getSession().getAttribute(REVISADO_CASOS_SESION)).booleanValue();
				List<CasoRevisionUsuario> casosRevisados=null;
				//Si no se ha editado ningún caso entonces buscamos de 
				//sesión la lista de revisiones
				if(revisadoCasosDesdeLaUltimaVez || request.getSession().getAttribute(OBJETO_REVISIONES_POR_HACER_SESION)==null ) {
					casosRevisados = casoRevisionService.getRevisionesPorHacer(idUsuario, cache.getNumMaxRevisiones(), 1);
					request.getSession().setAttribute(OBJETO_REVISIONES_POR_HACER_SESION,casosRevisados);
					request.getSession().setAttribute(REVISADO_CASOS_SESION, new Boolean(false));
					
				} else if(request.getSession().getAttribute(OBJETO_REVISIONES_POR_HACER_SESION)!=null) {
					casosRevisados = (List<CasoRevisionUsuario>)request.getSession().getAttribute(OBJETO_REVISIONES_POR_HACER_SESION);
				}
				model.addAttribute("revisionesPorHacer", casosRevisados);
			} catch(Exception ex) {
				MensajeResultado mensaje = new MensajeResultado();
				mensaje.setMensaje(ex.getMessage());
				mensaje.setTipo(MensajeTipo.ERROR);
				log.error(ex.getMessage(),ex);
			}
			
			
			return "index";
		}
	
}
