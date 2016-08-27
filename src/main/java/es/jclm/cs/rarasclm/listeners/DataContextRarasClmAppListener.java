/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.listeners;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import es.jclm.cs.rarasclm.entities.DatosAuxiliaresCacheados;
import es.jclm.cs.rarasclm.entities.EnfermedadCie10;
import es.jclm.cs.rarasclm.entities.EnfermedadCie9mc;
import es.jclm.cs.rarasclm.entities.EnfermedadCodigoLiteral;
import es.jclm.cs.rarasclm.entities.EnfermedadRara;
import es.jclm.cs.rarasclm.entities.IBaseModelView;
import es.jclm.cs.rarasclm.entities.Municipio;
import es.jclm.cs.rarasclm.entities.Provincia;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie10Service;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie9mcService;
import es.jclm.cs.rarasclm.service.EnfermedadRaraService;
import es.jclm.cs.rarasclm.service.HospitalService;
import es.jclm.cs.rarasclm.service.LocalizacionesService;


// TODO: Auto-generated Javadoc
public class DataContextRarasClmAppListener implements ApplicationListener<ContextRefreshedEvent> {

	static Log log = LogFactory.getLog(DataContextRarasClmAppListener.class.getName());
	static String ARCHIVO_PROPIEDADES = "rarasclm.properties";
	
	@Autowired
	private DatosAuxiliaresCacheados cache;
	
	@Autowired
	EnfermedadRaraCie9mcService cie9Service;
	
	@Autowired
	EnfermedadRaraCie10Service cie10Service;
	
	@Autowired
	EnfermedadRaraService raraService;
	
	@Autowired 
	LocalizacionesService localizacionesService;
	
	@Autowired
	HospitalService hospitalService;
	
	@Autowired
	IBaseModelView baseModel;
	

	public void onApplicationEvent(ContextRefreshedEvent evt) {
	
		log.info("Generando la estructura de módulos de la aplicación rarasCLM");
		try {
			log.info("Cargando entidades auxiliares de la aplicación de RarasCLM");
			cache.setCie9mcs(cie9Service.getAllEnfermedadesRarasCie9mc(false));
			
			cache.setCodLiteralesCie9mc(getCodigoLiteralCie9mc(cache.getCie9mcs()));
			log.info("Cargada entidades auxiliares CIE9MC");
			
			cache.setCie10s(cie10Service.getAllEnfermedadesRarasCie10(false));
			cache.setCodLiteralesCie10(getCodigoLiteralCie10(cache.getCie10s()));
			log.info("Cargada entidades auxiliares CIE10");
			
			cache.setEnfRaras(raraService.getAllEnfermedadesRaras(false));
			cache.setCodLiteralesEnfRara(getCodigoLiteralEnfRara(cache.getEnfRaras()));
			log.info("Cargada entidades auxiliares Enfermedades Raras");
			
			cache.setProvincias(localizacionesService.getProvincias(false));
			cache.setProvinciasCLM(localizacionesService.getProvinciasCLM(false));
			log.info("Cargada entidades auxiliares Provincias");
			
			cache.setMunicipios(localizacionesService.getMunicipios(false));
			HashMap<String, List<Municipio>> hMunicipios;
			hMunicipios = new HashMap<String, List<Municipio>>(52);
			for(Provincia p : cache.getProvincias()) {
				hMunicipios.put(p.getProvincia(),p.getMunicipios());
			}
			
			cache.setMunicipiosMapProvincia(hMunicipios);
			log.info("Cargada entidades auxiliares Municipios");
			cache.setHospitales(hospitalService.getHospitales(false));
			log.info("Cargada entidades auxiliares Hospitales");
			
			//Carga el archivo de propiedades
			InputStream inputStream;
			inputStream = getClass().getClassLoader().getResourceAsStream(ARCHIVO_PROPIEDADES);
			
			if (inputStream != null) {
				cache.getPropiedades().load(inputStream);
			} else {
				throw new FileNotFoundException("El archivo '" + ARCHIVO_PROPIEDADES + "' no se encuentra");
			}
			
			log.info("Leidos archivo de propiedades");
			cache.setNumMaxResultadosBusqueda(300);
			if(cache.getPropiedades().getProperty("rarasclm.max_resultados_busqueda")!=null)
				cache.setNumMaxResultadosBusqueda(Integer.parseInt(cache.getPropiedades().getProperty("rarasclm.max_resultados_busqueda")));
			cache.setNumMaxRevisiones(100);
			if(cache.getPropiedades().getProperty("rarasclm.max_num_revisiones")!=null)
				cache.setNumMaxRevisiones(Integer.parseInt(cache.getPropiedades().getProperty("rarasclm.max_num_revisiones")));
		}
		catch(Exception ex) {
			log.error(ex.getMessage(),ex);
		}
		
	}
	
	//java ugly no linq :-(
	private List<EnfermedadCodigoLiteral> getCodigoLiteralCie9mc(List<EnfermedadCie9mc> enfermedadesCie9mc) {
		List<EnfermedadCodigoLiteral> ret = new ArrayList<EnfermedadCodigoLiteral>();
		for (EnfermedadCie9mc cie9 : enfermedadesCie9mc) {
			EnfermedadCodigoLiteral enfCodLiteral = new EnfermedadCodigoLiteral();
			enfCodLiteral.setCod(cie9.getCie9Id());
			enfCodLiteral.setLiteral(cie9.getLiteral());
			ret.add(enfCodLiteral);
		}
		return ret;
	}

	//java ugly no linq :-(
	private List<EnfermedadCodigoLiteral> getCodigoLiteralCie10(List<EnfermedadCie10> enfermedadesCie10) {
		List<EnfermedadCodigoLiteral> ret = new ArrayList<EnfermedadCodigoLiteral>();
		for (EnfermedadCie10 cie10 : enfermedadesCie10) {
			EnfermedadCodigoLiteral enfCodLiteral = new EnfermedadCodigoLiteral();
			enfCodLiteral.setCod(cie10.getCie10Id());
			enfCodLiteral.setLiteral(cie10.getLiteral());
			ret.add(enfCodLiteral);
		}
		return ret;
	}
	
	//java ugly no linq :-(
	private List<EnfermedadCodigoLiteral> getCodigoLiteralEnfRara(List<EnfermedadRara> enfermedadesRara) {
		List<EnfermedadCodigoLiteral> ret = new ArrayList<EnfermedadCodigoLiteral>();
		for (EnfermedadRara enfRara : enfermedadesRara) {
			EnfermedadCodigoLiteral enfCodLiteral = new EnfermedadCodigoLiteral();
			enfCodLiteral.setCod(enfRara.getEnfermedadRaraId());
			enfCodLiteral.setLiteral(enfRara.getLiteral());
			ret.add(enfCodLiteral);
		}
		return ret;
	}

	
	
}
