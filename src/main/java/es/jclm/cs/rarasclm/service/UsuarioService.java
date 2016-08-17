package es.jclm.cs.rarasclm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.CasoDao;
import es.jclm.cs.rarasclm.dao.UserRarasCLMDao;
import es.jclm.cs.rarasclm.entities.UserRarasClm;

@Service
public class UsuarioService extends BaseCRUDService<UserRarasClm,String>{
	

	private UserRarasCLMDao userDao;	
	
	//Se inyecta aquí el dao porque hay que pasarselo a 
	//la clase Base BaseCRUDService
	@Autowired
	private UsuarioService(UserRarasCLMDao dao)
	{
		this.baseDao = dao;
		this.userDao= dao;
	}
	
}
