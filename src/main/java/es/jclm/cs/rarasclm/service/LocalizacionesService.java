package es.jclm.cs.rarasclm.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.MunicipiosDao;
import es.jclm.cs.rarasclm.dao.ProvinciasDao;
import es.jclm.cs.rarasclm.entities.DatosAuxiliaresCacheados;
import es.jclm.cs.rarasclm.entities.Municipios;
import es.jclm.cs.rarasclm.entities.Provincias;

@Service
public class LocalizacionesService {
	
	@Autowired
	MunicipiosDao municipiosDao;
	
	@Autowired
	ProvinciasDao provinciasDao;
	
	@Autowired 
	DatosAuxiliaresCacheados datosCache;
	
	private static final Logger log = LoggerFactory.getLogger(LocalizacionesService.class);
	
	
	public List<Municipios> getMunicipios() {
		if(datosCache.getMunicipios().size()>0)
			return datosCache.getMunicipios(); 
		else
			return municipiosDao.getMunicipios();
	}
	
	public List<Municipios> getMunicipios(boolean cache) {
		if(cache)
			return datosCache.getMunicipios(); 
		else
			return municipiosDao.getMunicipios();
	}
	
	public List<Provincias> getProvincias(boolean cache) {
		if(cache)
			return datosCache.getProvincias(); 
		else
			return provinciasDao.getProvincias();
	}
	
	public List<Provincias> getProvinciasCLM(boolean cache) {
		List<Provincias> provincias = getProvincias(cache);
		List<Provincias> ret = new ArrayList<Provincias>();
		Provincias desconocida = new Provincias();
		desconocida.setDeno("DESCONOCIDA");
		desconocida.setProvincia("99");
		ret.add(desconocida);
		for(Provincias p : provincias) {
			if(p.getCcaa().getCcaa().equals("08"))
				ret.add(p);
		}
		return ret;
	}
	
	public List<Municipios> getMunicipioDeProvincia(String idProvincia) {
		if(datosCache.getMunicipiosMapProvincia().size()>0) {
			return datosCache.getMunicipiosMapProvincia().get(idProvincia);
		} else {
			return provinciasDao.getProvincia(idProvincia).getMunicipioses();
		}			
	}
	

}
