package es.jclm.cs.rarasclm.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.jclm.cs.rarasclm.entities.IBaseModel;
import es.jclm.cs.rarasclm.entities.MensajeResultado;
import es.jclm.cs.rarasclm.entities.MensajeTipo;
import es.jclm.cs.rarasclm.entities.UserRarasClm;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.service.UsuarioService;

@Controller
@RequestMapping("/regenerapass")
public class RegeneraPasswordController extends BaseController{
	
	@Autowired
	HttpServletRequest request;
	
//	@Autowired
//	HttpServletResponse response;
	
	@Autowired
	UsuarioService usuarioService;
	
	private static final Logger log = LoggerFactory.getLogger(RegeneraPasswordController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String busquedaGet(Model model) {
		MensajeResultado mensaje = new MensajeResultado();
		UserRarasClm userClm = (UserRarasClm)request.getSession().getAttribute("userCLM");
		if(userClm.getGenerar()) {
			mensaje.setMensaje("Tiene que cambiar su contraseña personal para habilitar la cuenta.");
			mensaje.setTipo(MensajeTipo.WARNING);
			IBaseModel baseModel = (IBaseModel)model.asMap().get("baseModel");
			baseModel.setMensaje(mensaje);
		}
		//request.getSession().setAttribute("mensaje",mensaje);
		return "regenera-password";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String busquedaPost(Model model) {
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		UserRarasClm userClm = (UserRarasClm) request.getSession().getAttribute("userCLM");
		try {
			if (password1.equals(password2)) {
				UserRarasClm userClmPersistencia = usuarioService.Buscar(userClm.getUsername());
				userClmPersistencia.setPassword(BCrypt.hashpw(password1, BCrypt.gensalt()));
				userClmPersistencia.setGenerar(false);
				usuarioService.Actualizar(userClmPersistencia);
				request.getSession().setAttribute("userCLM", userClmPersistencia);
				return "redirect:/";
			}
		} catch (ServiceRarasCLMException ex) {
			MensajeResultado mensaje = new MensajeResultado();
			mensaje.setMensaje(ex.getMessage());
			mensaje.setTipo(MensajeTipo.ERROR);
			request.getSession().setAttribute("mensaje", mensaje);
			log.error(ex.getMessage());
		}
		return "redirect:/regenera-password";
	}
	
}
