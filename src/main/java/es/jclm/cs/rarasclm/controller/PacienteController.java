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
import es.jclm.cs.rarasclm.entities.EnfermedadRara;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie9mc;
import es.jclm.cs.rarasclm.entities.Municipios;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.entities.PacientesModelView;
import es.jclm.cs.rarasclm.entities.Provincias;
import es.jclm.cs.rarasclm.entities.UserRarasCLM;
import es.jclm.cs.rarasclm.service.EnfermedadRaraService;
import es.jclm.cs.rarasclm.service.LocalizacionesService;
import es.jclm.cs.rarasclm.service.PacienteService;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import net.rossillo.spring.web.mvc.CacheControl;

@Controller
@RequestMapping("/pacientes")
@RarasClmItemModulo(caption="Pacientes",deno="Pacientes",modulo="pacientes",orden=2)
@RarasClmItemMenu(caption="Pacientes",deno="Pacientes",modulo="pacientes",orden=1)
@SessionAttributes("pacientes")
public class PacienteController extends BaseController {
	
	@Autowired
	PacienteService servicio;
	
	@Autowired
	LocalizacionesService servicioLocalizaciones;
	
	@Autowired
	HttpServletRequest request;
	
	
	
	
	// Página inicial del módulo
	@RequestMapping(method = RequestMethod.GET)
	public String inicioEnvio(Model model) {
		PacientesModelView pacientesMV=null;
		if(request.getSession().getAttribute("pacientesMV")==null) {	
			pacientesMV = new PacientesModelView();
			pacientesMV.setNumPaginas(-1);
			pacientesMV.setNumRegistrosPorPagina(-1);
			pacientesMV.setNumTotalRegistros(-1);
			pacientesMV.setBusquedaApellido1("");
			pacientesMV.setBusquedaApellido2("");
			pacientesMV.setBusquedaNombre("");
			pacientesMV.setBusquedaCIP("");
			pacientesMV.setBusquedaMunicipio("99999");
		} else {
			pacientesMV=(PacientesModelView)request.getSession().getAttribute("pacientesMV");
		}
		if(pacientesMV.getBusquedaMunicipio().length()==5 && pacientesMV.getBusquedaMunicipio().equals("99999"))
			model.addAttribute("municipiosProvinciaResidencia",servicioLocalizaciones.getMunicipioDeProvincia(pacientesMV.getBusquedaMunicipio().substring(0, 2)));
		model.addAttribute("pacientes", pacientesMV);
		getRoute().setId("");
		return "pacientes/index-pacientes";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String inicioVuelta(Model model, 
			@ModelAttribute("pacientes") PacientesModelView pacientesMV,
			SessionStatus status) {

		status.setComplete();
		model.addAttribute("pacientes", pacientesMV);
		pacientesMV.setPacientes(servicio.buscaPacientes(pacientesMV));
		request.getSession().setAttribute("pacientesMV", pacientesMV);
		getRoute().setId("");
		return "redirect:/pacientes/"; //Evita nueva consulta a BD al volver atrás con el navegador
									   //o actualizar.
	}
	
	
	@RequestMapping(value = "/show/json/{id}", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String  editPaciente(@PathVariable String id, Model model) {
		Integer clave = Integer.valueOf(id);
		try {
			Paciente paciente = servicio.Buscar(clave);
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
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String  submitPaciente(@PathVariable String id, Model model) {
		
		return "redirect:/pacientes/edit/{id}";
	}
	
	
	

}
