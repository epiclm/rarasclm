package es.jclm.cs.rarasclm.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.jclm.cs.rarasclm.entities.Municipio;
import es.jclm.cs.rarasclm.service.LocalizacionesService;



@Controller
@RequestMapping("/localizaciones")
public class LocalizacionesController {
	
	private static final Logger log = LoggerFactory.getLogger(LocalizacionesController.class);
	
	@Autowired
	private LocalizacionesService service;
	
	@RequestMapping(value = "/municipios/deprovincia/{id}", method = RequestMethod.GET)
	public  @ResponseBody List<Municipio> getMunicipiosDeProvincia(@PathVariable String id) {
		try {
			List<Municipio> ret = service.getMunicipioDeProvincia(id);
			return ret;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("Error en el controlador de localizacioes al obtener municipios por provincia",ex);
			return null; //TO DO Mandar mensaje de error a la vista
		}
	}
}
