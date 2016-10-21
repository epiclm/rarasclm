package es.jclm.cs.rarasclm.controller;

import java.io.Console;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.Caso;
import es.jclm.cs.rarasclm.entities.MensajeResultado;
import es.jclm.cs.rarasclm.entities.MensajeTipo;
import es.jclm.cs.rarasclm.entities.UserRarasClm;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.service.UsuarioService;
import es.jclm.cs.rarasclm.util.MergeEntity;

@Controller
@RequestMapping("/admin/usuarios")
@RarasClmItemMenu(caption="Usuarios",deno="Usuarios",modulo="admin",orden=2)
public class AdminUsuariosController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(AdminUsuariosController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private HttpServletRequest request;
	
	@ModelAttribute("allUsuarios")
	public List<UserRarasClm> populateAllRaras() {
		List<UserRarasClm> usuarios = usuarioService.getAll();
		return usuarios;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String busqueda(Model model) {	
		return "admin/usuarios/index-usuario";
	}
	
	@RequestMapping(value="/activa/{usuario}", method = RequestMethod.POST)
	public @ResponseBody UserRarasClm activaUsuario(@PathVariable String usuario) {
		MensajeResultado mensaje = new MensajeResultado();
		try {
			UserRarasClm user = usuarioService.activaUsuario(usuario);
			mensaje.setMensaje(String.format("%s Su estado ahora ACTIVADO",usuario));
			mensaje.setTipo(MensajeTipo.OK);
			request.getSession().setAttribute("mensaje",mensaje);
			return user;
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(),ex);
		}
		return null;
	}
	
	@RequestMapping(value="/desactiva/{usuario}", method = RequestMethod.POST)
	public @ResponseBody UserRarasClm desactivaUsuario(@PathVariable String usuario) {
		MensajeResultado mensaje = new MensajeResultado();
		try {
			UserRarasClm user = usuarioService.desactivaUsuario(usuario);
			mensaje.setMensaje(String.format("%s Su estado ahora DESACTIVADO",usuario));
			mensaje.setTipo(MensajeTipo.OK);
			request.getSession().setAttribute("mensaje",mensaje);
			return user;
		} catch (ServiceRarasCLMException ex) {
			mensaje.setMensaje(String.format("Error %s",ex.getMessage()));
			mensaje.setTipo(MensajeTipo.ERROR);
			request.getSession().setAttribute("mensaje",mensaje);
			log.error(ex.getMessage(),ex);
		}
		return null;
	}
	
	@RequestMapping(value="/hazadmin/{usuario}", method = RequestMethod.POST)
	public @ResponseBody UserRarasClm hazAdmin(@PathVariable String usuario) {
		MensajeResultado mensaje = new MensajeResultado();
		try {
			UserRarasClm user = usuarioService.hazAdmin(usuario);
			mensaje.setMensaje(String.format("El usuario %s es administrador",usuario));
			mensaje.setTipo(MensajeTipo.OK);
			request.getSession().setAttribute("mensaje",mensaje);
			return user;
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(),ex);
			mensaje.setTipo(MensajeTipo.ERROR);
			mensaje.setMensaje(ex.getMessage());
			request.getSession().setAttribute("mensaje",mensaje);
		}
		return null;
	}
	
	@RequestMapping(value="/quitaadmin/{usuario}", method = RequestMethod.POST)
	public @ResponseBody UserRarasClm quitaAdmin(@PathVariable String usuario) {
		MensajeResultado mensaje = new MensajeResultado();
		try {
			UserRarasClm user = usuarioService.quitaAdmin(usuario);
			mensaje.setMensaje(String.format("Ahora el usuario %s solamente tiene permisos de usuario",usuario));
			mensaje.setTipo(MensajeTipo.OK);
			request.getSession().setAttribute("mensaje",mensaje);
			return user;
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(),ex);
			mensaje.setMensaje(ex.getMessage());
			mensaje.setTipo(MensajeTipo.ERROR);
			request.getSession().setAttribute("mensaje",mensaje);
		}
		return null;
	}
	
	@RequestMapping(value="/edit/{username}", method = RequestMethod.GET)
	public String editUsuarioGet(@PathVariable String username, Model model) {
		try {
			UserRarasClm usuario = usuarioService.Buscar(username);
			model.addAttribute("usuario",usuario);
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
		}
		return "admin/usuarios/edit-usuario";
	}
	
	@RequestMapping(value="/edit/{username}", method = RequestMethod.POST)
	public String  editUsuarioPost(@PathVariable String username, @ModelAttribute("usuario") UserRarasClm usuario, Model model) {
		try {
			UserRarasClm usuarioPersist = usuarioService.Buscar(usuario.getUsername());
			usuarioPersist = new MergeEntity<UserRarasClm>().merge(usuarioPersist, usuario).getMergeObject();
			//Evitar la inconsistencia numIntentos>0 y no habilitado
			if(!usuarioPersist.getEnabled()) {
				usuarioPersist.setNumIntentos(0);
			}
			usuarioService.Actualizar(usuarioPersist);
			MensajeResultado mensaje = new MensajeResultado();
			mensaje.setMensaje(String.format("El usuario %s se ha actualizado correctamente",usuarioPersist.getUsername()));
			mensaje.setTipo(MensajeTipo.OK);
			request.getSession().setAttribute("mensaje",mensaje);
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			MensajeResultado mensaje = new MensajeResultado();
			mensaje.setTipo(MensajeTipo.ERROR);
			mensaje.setMensaje(ex.getMessage());
			request.getSession().setAttribute("mensaje",mensaje);
		}
		return "redirect:/admin/usuarios/edit/"+username;
	}
	
}