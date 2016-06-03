package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.entities.DatosAuxiliaresCacheados;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie10;
import es.jclm.cs.rarasclm.entities.Municipios;

@Service
public class MunicipiosService {
	
	@Autowired
	DatosAuxiliaresCacheados datosCache;
	
	
	private List<Municipios> getMunicipios(boolean cache) {
		if(cache) {
			return datosCache.getMunicipios();
		}
		return null;
	}
	
	

	private List<Municipios> getMuncipiosByProvincia(String provincia) {
		return null;
	}
	
}
