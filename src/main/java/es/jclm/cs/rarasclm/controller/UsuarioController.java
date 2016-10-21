package es.jclm.cs.rarasclm.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.jclm.cs.rarasclm.entities.AccionResultado;
import es.jclm.cs.rarasclm.entities.CasoRevisionUsuarioId;
import es.jclm.cs.rarasclm.entities.UserRarasClm;
import es.jclm.cs.rarasclm.service.CasoRevisionService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);	
	private static final String REVISADO_CASOS_SESION = "revisados_casos";
	
	@Autowired
	private CasoRevisionService revisionService;

	@Autowired
	HttpServletRequest request;
	
	@RequestMapping(method=RequestMethod.GET)
	public String  muestraUsuarioActual(Model model) {
		return "usuario/index-usuario";
	}
	
	@RequestMapping(value="/addrevision/{idCaso}",method = RequestMethod.POST)
	public  @ResponseBody AccionResultado addRevisionCaso(@PathVariable String idCaso, Model model) {
		UserRarasClm user = (UserRarasClm)model.asMap().get("userCLM");
		AccionResultado resultado = (AccionResultado)model.asMap().get("resultado");
		CasoRevisionUsuarioId casoRevisionId;
		try {
			if(revisionService.isCasoRevisionSet(idCaso, user.getUsername())) {
				resultado.setWarning(true);
				resultado.setMensaje(String.format("Ya existe el caso %s para revisión", idCaso));
			} else {
				casoRevisionId = revisionService.addCasoRevision(idCaso, user.getUsername());
				resultado.setSuccess(true);
				//Se establece el objeto de sesión REVISADO_CASOS_SESION a true para que se
				//refresque la lista de casos a revisar.
				request.getSession().setAttribute(REVISADO_CASOS_SESION, true);
				resultado.setMensaje(String.format("Se ha añadido el caso %s para la revisión", casoRevisionId.getCaso()));
			}
		} catch(Exception ex) {
			resultado.setError(true);
			resultado.setMensaje(ex.getMessage());
			log.error(ex.getMessage(),ex);
		}
		return resultado;
	}
	
	@RequestMapping(value="/deleterevision/{idCaso}",method = RequestMethod.POST)
	public  @ResponseBody AccionResultado deleteRevisionCaso(@PathVariable String idCaso, Model model) {
		UserRarasClm user = (UserRarasClm)model.asMap().get("userCLM");
		AccionResultado resultado = (AccionResultado)model.asMap().get("resultado");
		try {
			if(revisionService.isCasoRevisionSet(idCaso, user.getUsername())) {
				int numRevision = revisionService.getNumUltimaRevision(idCaso, user.getUsername());
				revisionService.deleteCasoRevision(idCaso, user.getUsername(), numRevision);
				resultado.setSuccess(true);
				resultado.setMensaje(String.format("Se ha borrado la revisión del caso %s correctamente",idCaso));
				//Se establece el objeto de sesión REVISADO_CASOS_SESION a true para que se
				//refresque la lista de casos a revisar.
				request.getSession().setAttribute(REVISADO_CASOS_SESION, true);
			} else {
				
			}
		} catch(Exception ex) {
			resultado.setError(true);
			resultado.setMensaje(ex.getMessage());
			log.error(ex.getMessage(),ex);
		}
		return resultado;
	}
	
	@RequestMapping(value="/setrevisado/{idCaso}/{num}",method = RequestMethod.POST)
	public  @ResponseBody AccionResultado setRevisionCaso(@PathVariable String idCaso, @PathVariable int num, Model model) {
		UserRarasClm user = (UserRarasClm)model.asMap().get("userCLM");
		AccionResultado resultado = (AccionResultado)model.asMap().get("resultado");
		try {
			revisionService.setRevidadoCaso(idCaso, user.getUsername(), num );
		} catch(Exception ex) {
			resultado.setError(true);
			resultado.setMensaje(ex.getMessage());
			log.error(ex.getMessage(),ex);
		}
		return resultado;
	}



}
