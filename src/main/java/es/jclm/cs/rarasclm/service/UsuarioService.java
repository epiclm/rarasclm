package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.NotFoundException;
import es.jclm.cs.rarasclm.dao.UnableToSaveException;
import es.jclm.cs.rarasclm.dao.UserRarasCLMDao;
import es.jclm.cs.rarasclm.entities.RolRarasClm;
import es.jclm.cs.rarasclm.entities.UserRarasClm;

@Service
public class UsuarioService extends BaseCRUDService<UserRarasClm,String>{
	
	private UserRarasCLMDao userDao;	
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	//Se inyecta aquí el dao porque hay que pasarselo a 
	//la clase Base BaseCRUDService
	@Autowired
	private UsuarioService(UserRarasCLMDao dao)
	{
		this.baseDao = dao;
		this.userDao= dao;
	}

	public List<UserRarasClm> getAll() {
		return userDao.getAll();
	}
	
	
	public boolean userExists(String username) {
		UserRarasClm user=null;
		try {
			user = userDao.buscar(username);
		} catch (NotFoundException ex) {
			log.error(ex.getMessage());
		}
		return user!=null;
	}
	
	
	public void creaUsuario(UserRarasClm user) throws ServiceRarasCLMException {
		try {
			RolRarasClm rolUser = new RolRarasClm();
			rolUser.setId(1);
			rolUser.setDeno("ROL_USER");
			user.getRolRarasClms().add(rolUser);
			if(user.getEnabled()) {
				user.setNumIntentos(3);
			} else {
				user.setNumIntentos(0);
			}
			userDao.guardar(user);
			if (user.isAdmin()) {
				hazAdmin(user.getUsername());
			}
		} catch (UnableToSaveException ex) {
			log.error(ex.getMessage());
			String mensaje = String.format("El usuario %s no se puede crear debido a %s", user.getUsername(),
					ex.getMessage());
			throw new ServiceRarasCLMException(mensaje);
		}
	}
	
	
	public UserRarasClm hazAdmin(String username) throws ServiceRarasCLMException {
		UserRarasClm usuario=null;
		try {
			usuario = userDao.buscar(username);
			RolRarasClm rolUser = null;
			RolRarasClm rolAdmin = null;
			for(RolRarasClm rol : usuario.getRolRarasClms()) {
				if(rol.getId()==0) {
					rolAdmin = rol;
				}
				if(rol.getId()==1) {
					rolUser = rol;
				}
			}
			if(rolAdmin==null) {
				rolAdmin = new RolRarasClm();
				rolAdmin.setId(0);
				rolAdmin.setDeno("ROL_ADMIN");
				usuario.getRolRarasClms().add(rolAdmin);
			}
			if(rolUser==null) {
				rolUser = new RolRarasClm();
				rolUser.setId(1);
				rolUser.setDeno("ROL_USER");
				usuario.getRolRarasClms().add(rolUser);
			}
			userDao.actualizar(usuario);
		} catch (NotFoundException e) {
			String mensaje = String.format("El usuario %s no se encuentra",username);
			log.error(mensaje);
			throw new ServiceRarasCLMException(mensaje);
		} catch (UnableToSaveException e) {
			String mensaje = String.format("El usuario %s no se puede actualizar",username);
			log.error(mensaje);
		}
		return usuario;
	}
	
	
	public UserRarasClm quitaAdmin(String username) throws ServiceRarasCLMException {
		UserRarasClm usuario=null;
		try {
			usuario = userDao.buscar(username);
			RolRarasClm rolAdmin = null;
			for(RolRarasClm rol : usuario.getRolRarasClms()) {
				if(rol.getId()==0) {
					rolAdmin = rol;
				}
			}
			if(rolAdmin!=null) {
				usuario.getRolRarasClms().remove(rolAdmin);
			} 
			userDao.actualizar(usuario);
		} catch (NotFoundException e) {
			String mensaje = String.format("El usuario %s no se encuentra",username);
			log.error(mensaje);
			throw new ServiceRarasCLMException(mensaje);
		} catch (UnableToSaveException e) {
			String mensaje = String.format("El usuario %s no se puede actualizar",username);
			log.error(mensaje);
		}
		return usuario;
	}
	
	
	public UserRarasClm activaUsuario(String username) throws ServiceRarasCLMException {
		UserRarasClm usuario=null;
		try {
			usuario = userDao.buscar(username);
			usuario.setEnabled(true);
			usuario.setNumIntentos(3);
			userDao.actualizar(usuario);
		} catch (NotFoundException e) {
			String mensaje = String.format("El usuario %s no se encuentra",username);
			log.error(mensaje);
			throw new ServiceRarasCLMException(mensaje);
		} catch (UnableToSaveException e) {
			String mensaje = String.format("El usuario %s no se puede actualizar",username);
			log.error(mensaje);
		}
		return usuario;
	}
	
	
	public UserRarasClm desactivaUsuario(String username) throws ServiceRarasCLMException {
		UserRarasClm usuario=null;
		try {
			usuario = userDao.buscar(username);
			usuario.setEnabled(false);
			usuario.setNumIntentos(0);
			userDao.actualizar(usuario);
		} catch (NotFoundException e) {
			String mensaje = String.format("El usuario %s no se encuentra",username);
			log.error(mensaje);
			throw new ServiceRarasCLMException(mensaje);
		} catch (UnableToSaveException e) {
			String mensaje = String.format("El usuario %s no se puede actualizar",username);
			log.error(mensaje);
		}
		return usuario;
	}


	
}
