package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.HospitalDao;
import es.jclm.cs.rarasclm.entities.DatosAuxiliaresCacheados;
import es.jclm.cs.rarasclm.entities.Hospital;

@Service
public class HospitalService extends BaseCRUDService<Hospital,String>{
	
	
	private static final Logger log = LoggerFactory.getLogger( HospitalService.class);
	
	private HospitalDao dao;
	
	@Autowired 
	private DatosAuxiliaresCacheados datosCache;
	
	//Se inyecta aquí el dao porque hay que pasarselo a 
	//la clase Base BaseCRUDService
	@Autowired
	private HospitalService(HospitalDao dao)
	{
		this.baseDao = dao;
		this.dao = dao;
	}
	
	public List<Hospital> getHospitales() {
		if(datosCache!=null && datosCache.getHospitales()!=null && datosCache.getHospitales().size()>0)
			return datosCache.getHospitales();
		else
			return getHospitales(false);
	}
	
	
	public List<Hospital> getHospitales(boolean cache) {
		if(!cache) {
			try {
				return dao.getHospitales();
			} catch(Exception ex) {
				log.error(ex.getMessage(),ex);
				return null;
			}
		} else {
			return datosCache.getHospitales();
		}
	}
	

}
