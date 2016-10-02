package es.jclm.cs.rarasclm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.BusquedaModelView;
import es.jclm.cs.rarasclm.entities.Caso;
import es.jclm.cs.rarasclm.entities.DatosAuxiliaresCacheados;
import es.jclm.cs.rarasclm.entities.MensajeResultado;
import es.jclm.cs.rarasclm.entities.MensajeTipo;
import es.jclm.cs.rarasclm.service.BusquedaService;
import es.jclm.cs.rarasclm.service.CasoService;
import es.jclm.cs.rarasclm.service.LocalizacionesService;
import es.jclm.cs.rarasclm.service.PacienteService;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;

@Controller
@RequestMapping("/busqueda")
@RarasClmItemModulo(caption="Búsqueda",deno="Búsqueda",modulo="busqueda",orden=1)
@RarasClmItemMenu(caption="Búsqueda",deno="Búsqueda",modulo="busqueda",orden=1)
public class BusquedaController extends BaseController {
	
	
	@Autowired
	LocalizacionesService servicioLocalizaciones;
	
	@Autowired
	BusquedaService busquedaService;
	
	@Autowired
	CasoService casoService;
	
	@Autowired
	PacienteService pacienteService;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired 
	DatosAuxiliaresCacheados cache;
	
	public static final String OBJETO_PACIENTE_SESION="paciente";
	public static final String OBJETO_CASO_SESION="caso";
	public static final String OBJETO_BUSQUEDA_SESION="busqueda";
	public static final String OBJETO_MENSAJE_SESION = "mensaje";

	///////////////////////////////////
	// BÚSQUEDA DE UN CASO SEND GET
	///////////////////////////////////
	@RequestMapping(method = RequestMethod.GET)
	public String busqueda(Model model) {
		
		BusquedaModelView busquedaModel;
		
		if(request.getSession().getAttribute(OBJETO_BUSQUEDA_SESION)==null)
			busquedaModel = new BusquedaModelView();
		else
			busquedaModel = (BusquedaModelView)request.getSession().getAttribute(OBJETO_BUSQUEDA_SESION);
		
		
		model.addAttribute(OBJETO_BUSQUEDA_SESION, busquedaModel);
		
		if(busquedaModel.getMunicipio().length()==5 && busquedaModel.getMunicipio().equals("99999"))
			model.addAttribute("municipiosProvinciaResidencia",servicioLocalizaciones.getMunicipioDeProvincia(busquedaModel.getMunicipio().substring(0, 2)));
		
		return "busqueda/index-busqueda";
	}
	
	
	/////////////////////////////////////
	// BÚSQUEDA DE UN CASO SUBMIT POST
	/////////////////////////////////////
	@RequestMapping(method = RequestMethod.POST)
	public String busquedaSubmit(Model model, @ModelAttribute(OBJETO_BUSQUEDA_SESION) BusquedaModelView busquedaModel) {
				
		long numCasos = busquedaService.buscaCasosNumResultados(busquedaModel);
		
		busquedaModel.setNumResultados(numCasos);
		
		if((int)numCasos>cache.getNumMaxResultadosBusqueda()) {
			MensajeResultado mensaje = new MensajeResultado();
			mensaje.setMensaje("La consulta responde con demasiados resultados. limite más la búsqueda");
			mensaje.setTipo(MensajeTipo.ERROR);
			request.getSession().setAttribute(OBJETO_MENSAJE_SESION,mensaje);
			busquedaModel.setCasos(null);
		} else {
			busquedaModel.setCasos(busquedaService.buscaCasos(busquedaModel));
			if(busquedaModel.getMunicipio().length()==5 && busquedaModel.getMunicipio().equals("99999"))
				model.addAttribute("municipiosProvinciaResidencia",servicioLocalizaciones.getMunicipioDeProvincia(busquedaModel.getMunicipio().substring(0, 2)));
		}
		
		model.addAttribute(OBJETO_BUSQUEDA_SESION, busquedaModel); //??????????
		request.getSession().setAttribute(OBJETO_BUSQUEDA_SESION,busquedaModel);

		return "redirect:busqueda";
	}
	
	
	
	@RequestMapping(value = "/ircaso/{id}", method = RequestMethod.GET)
	public String irCaso(@PathVariable String id) {
		try {
			Caso c = casoService.Buscar(id);
			request.getSession().setAttribute(OBJETO_CASO_SESION, c);
			request.getSession().setAttribute(OBJETO_PACIENTE_SESION,c.getPaciente());
		} catch (ServiceRarasCLMException e) {
			e.printStackTrace();
		}
		return "redirect:/casos";
	}
	
	@RequestMapping(value="/borrar")
	public String borrarBusqueda() {
		request.getSession().setAttribute(OBJETO_BUSQUEDA_SESION, null);
		return "redirect:/busqueda";
	}
	
	
}
