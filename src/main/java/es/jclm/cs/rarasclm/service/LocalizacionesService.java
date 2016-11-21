package es.jclm.cs.rarasclm.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.MunicipiosDao;
import es.jclm.cs.rarasclm.dao.ProvinciasDao;
import es.jclm.cs.rarasclm.entities.Municipio;
import es.jclm.cs.rarasclm.entities.Provincia;
import es.jclm.cs.rarasclm.util.DatosAuxiliaresCacheados;


@Service
public class LocalizacionesService {
	
	@Autowired
	MunicipiosDao municipiosDao;
	
	@Autowired
	ProvinciasDao provinciasDao;
	
	@Autowired 
	DatosAuxiliaresCacheados datosCache;
	
	private static final Logger log = LoggerFactory.getLogger(LocalizacionesService.class);
	
	
	public List<Municipio> getMunicipios() {
		if(datosCache.getMunicipios().size()>0)
			return datosCache.getMunicipios(); 
		else
			return municipiosDao.getMunicipios();
	}
	
	public List<Municipio> getMunicipios(boolean cache) {
		if(cache)
			return datosCache.getMunicipios(); 
		else
			return municipiosDao.getMunicipios();
	}
	
	public List<Provincia> getProvincias(boolean cache) {
		if(cache)
			return datosCache.getProvincias(); 
		else
			return provinciasDao.getProvincias();
	}
	
	public List<Provincia> getProvinciasCLM(boolean cache) {
		List<Provincia> provincias = getProvincias(cache);
		List<Provincia> ret = new ArrayList<Provincia>();
		Provincia desconocida = new Provincia();
		desconocida.setDeno("DESCONOCIDA");
		desconocida.setProvincia("99");
		ret.add(desconocida);
		for(Provincia p : provincias) {
			if(p.getCcaa().getCcaa().equals("08"))
				ret.add(p);
		}
		return ret;
	}
	
	public List<Municipio> getMunicipioDeProvincia(String idProvincia) {
		if(datosCache.getMunicipiosMapProvincia().size()>0) {
			return datosCache.getMunicipiosMapProvincia().get(idProvincia);
		} else {
			return (List<Municipio>) provinciasDao.getProvincia(idProvincia).getMunicipios();
		}			
	}
	

}
