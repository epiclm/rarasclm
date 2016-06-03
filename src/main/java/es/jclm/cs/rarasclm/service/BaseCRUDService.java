package es.jclm.cs.rarasclm.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.jclm.cs.rarasclm.dao.BaseEntityDao;

public class BaseCRUDService<Entity, K extends Serializable> {

	protected BaseEntityDao<Entity,K> baseDao;
	
	private static final Logger log = LoggerFactory.getLogger(BaseEntityDao.class);

	protected BaseEntityDao<Entity, K> getBaseDao() {
		return baseDao;
	}

	protected void setBaseDao(BaseEntityDao<Entity, K> baseDao) {
		this.baseDao = baseDao;
	} 
	
	public Entity Buscar(K clave) throws ServiceRarasCLMException {
		try {
			return this.baseDao.buscar(clave);
		} catch (Exception ex) {
			String m = String.format("Error al buscar la entidad con id:%s",clave.toString());
			log.error(m, ex);
			ex.printStackTrace();
			throw new ServiceRarasCLMException(m);
		}
	}
	
	public void Actualizar(Entity entidad) throws ServiceRarasCLMException
	{
		try {
			baseDao.actualizar(entidad);
		} catch(Exception ex) {
			String m = String.format("Error al buscar la entidad: %s",entidad.toString());
			log.error(m, ex);
			ex.printStackTrace();
			throw new ServiceRarasCLMException(m);
		}
	}
	
	public void Guardar(Entity entidad) throws ServiceRarasCLMException
	{
		try {
			baseDao.guardar(entidad);
		} catch(Exception ex) {
			String m = String.format("Error al grabar la entidad: %s",entidad.toString());
			log.error(m, ex);
			ex.printStackTrace();
			throw new ServiceRarasCLMException(m);
		}
	}
	
	public void Borrar(Entity entidad) throws ServiceRarasCLMException
	{
		try {
			baseDao.eliminar(entidad);
		} catch(Exception ex) {
			String m = String.format("Error al borrar la entidad: %s",entidad.toString());
			log.error(m, ex);
			ex.printStackTrace();
			throw new ServiceRarasCLMException(m);
		}
	}
	
	
}
