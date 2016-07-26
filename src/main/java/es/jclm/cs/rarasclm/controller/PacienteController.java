package es.jclm.cs.rarasclm.controller;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.Caso;
import es.jclm.cs.rarasclm.entities.EnfermedadRara;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie9mc;
import es.jclm.cs.rarasclm.entities.MensajeResultado;
import es.jclm.cs.rarasclm.entities.MensajeTipo;
import es.jclm.cs.rarasclm.entities.MergeResult;
import es.jclm.cs.rarasclm.entities.Municipios;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.entities.PacientesModelView;
import es.jclm.cs.rarasclm.entities.Provincias;
import es.jclm.cs.rarasclm.entities.UserRarasCLM;
import es.jclm.cs.rarasclm.service.EnfermedadRaraService;
import es.jclm.cs.rarasclm.service.LocalizacionesService;
import es.jclm.cs.rarasclm.service.PacienteService;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.util.MergeEntity;
import net.rossillo.spring.web.mvc.CacheControl;

@Controller
@RequestMapping("/pacientes")
@RarasClmItemModulo(caption="Pacientes",deno="Pacientes",modulo="pacientes",orden=2)
@RarasClmItemMenu(caption="Pacientes",deno="Pacientes",modulo="pacientes",orden=1)
public class PacienteController extends BaseController {
	
	@Autowired
	PacienteService servicio;
	
	@Autowired
	LocalizacionesService servicioLocalizaciones;
	
	@Autowired
	HttpServletRequest request;
	
	
	public static final String OBJETO_PACIENTE_SESION="paciente";
	public static final String OBJETO_CASO_SESION="caso";
	
	// Página inicial del módulo
	@RequestMapping(method = RequestMethod.GET)
	public String inicioEnvio(Model model) {
		if(request.getSession().getAttribute(OBJETO_PACIENTE_SESION)==null) {
			return "pacientes/index-pacientes";
		} else {
			Paciente p = (Paciente)request.getSession().getAttribute(OBJETO_PACIENTE_SESION);
			return String.format("redirect:/pacientes/edit/%d",p.getIdPaciente());
		}
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public String inicioVuelta(Model model, @ModelAttribute("pacientes") PacientesModelView pacientesMV,SessionStatus status) {
//		return "redirect:/pacientes/"; 
//	}
	
	@RequestMapping(value = "/nuevaBusqueda", method = RequestMethod.GET) 
	public String nuevoPaciente() {
		if(request.getSession().getAttribute(OBJETO_PACIENTE_SESION)!=null) {
			request.getSession().setAttribute(OBJETO_PACIENTE_SESION, null);
		}
		if(request.getSession().getAttribute(OBJETO_CASO_SESION)!=null) {
			request.getSession().setAttribute(OBJETO_CASO_SESION, null);
		}
		return "redirect:/pacientes";
	}
	
	
	///////////////////////////////////
	//MOSTRAR CASO EN JSON
	///////////////////////////////////
	@RequestMapping(value = "/json/{id}", method = RequestMethod.GET)
	public  @ResponseBody Paciente showJsonPaciente(@PathVariable String id) {
		Integer clave = Integer.valueOf(id);
		try {
			Paciente ret = servicio.Buscar(clave);
			return ret;
		} catch (ServiceRarasCLMException ex) {
			ex.printStackTrace();
			return null; //TO DO Mandar mensaje de error a la vista
		}
	}
	
	
	///////////////////////////////////
	//EDICIÓN DE UN PACIENTE SEND GET
	///////////////////////////////////
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String  editPaciente(@PathVariable String id, Model model) {
		Paciente paciente;
		Integer clave = Integer.valueOf(id);
		try {
			if(request.getSession().getAttribute(OBJETO_PACIENTE_SESION)==null) {
				paciente = servicio.Buscar(clave);
				request.getSession().setAttribute(OBJETO_PACIENTE_SESION,paciente);
			} else {
				paciente = (Paciente)request.getSession().getAttribute(OBJETO_PACIENTE_SESION);
				if(paciente.getIdPaciente()!=clave) {
					//Buscamos ya que se está haciendo una petición de un paciente distinto
					//al que tenemeos guardado en sesión
					paciente = servicio.Buscar(clave);
					request.getSession().setAttribute(OBJETO_PACIENTE_SESION,paciente);
				}
			} 
			model.addAttribute("paciente", paciente);
			List<Municipios> municipiosResidencia = new ArrayList<Municipios>();
			Municipios municipioDesconocido = new Municipios();
			municipioDesconocido.setMunicipio("99999");
			municipioDesconocido.setDeno("DESCONOCIDO");
			municipiosResidencia.add(municipioDesconocido);
			if(paciente.getMunicipioResidencia()!=null && paciente.getMunicipioResidencia().trim().length()==5) {
				municipiosResidencia.addAll(servicioLocalizaciones.getMunicipioDeProvincia(paciente.getMunicipioResidencia().substring(0, 2)));
			} else {
				municipiosResidencia.addAll(servicioLocalizaciones.getMunicipioDeProvincia("99"));
			}				
			model.addAttribute("municipiosProvinciaResidencia",municipiosResidencia);
		} catch (ServiceRarasCLMException ex) {
			ex.printStackTrace();
			return null; //TO DO Mandar mensaje de error a la vista
		}
		return "pacientes/forms/pacienteEdit";
	}
	
	
	///////////////////////////////////
	//EDICIÓN DE UN PACIENTE SEND POST
	///////////////////////////////////
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String  submitPaciente(@PathVariable String id, @ModelAttribute("paciente") Paciente paciente, Model model) {
		try {
			
			int pacienteId = Integer.parseInt(id);
			Paciente pacienteSinEditar = servicio.Buscar(pacienteId);
			UserRarasCLM user = (UserRarasCLM)model.asMap().get("userCLM");
			MergeResult<Paciente> pacienteMerge = new MergeEntity<Paciente>().merge(pacienteSinEditar,paciente);
			servicio.Actualizar(pacienteMerge.getMergeObject());
			
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("<p><b>ACTUALIZACIÓN CORRECTA</b></p>paciente %07d%n</p>\n",pacienteSinEditar.getIdPaciente()));

			MensajeResultado mensaje = new MensajeResultado();
			mensaje.setTipo(MensajeTipo.OK);
			mensaje.setMensaje(sb.toString());
			request.getSession().setAttribute("mensaje",mensaje);
			
			paciente = servicio.Buscar(pacienteId);
			request.getSession().setAttribute(OBJETO_PACIENTE_SESION,paciente);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			return null; //TO DO Mandar mensaje de error a la vista
		}
		
		return "redirect:/pacientes/edit/{id}";
	}
	
	
	

}
