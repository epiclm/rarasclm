package es.jclm.cs.rarasclm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.CasoDao;
import es.jclm.cs.rarasclm.entities.Caso;

@Service
public class CasoService extends BaseCRUDService<Caso, String>{
	
	private CasoDao dao;
	
	//Se inyecta aquí el dao porque hay que pasarselo a 
	//la clase Base BaseCRUDService
	@Autowired
	private CasoService(CasoDao dao)
	{
		this.baseDao = dao;
		this.dao = dao;
	}
}
