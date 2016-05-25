/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

// TODO: Auto-generated Javadoc
/**
 * The Class HibernateAwareObjectMapper.
 */
//Necesario para serializar a json
public class HibernateAwareObjectMapper extends ObjectMapper {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7850730654033287296L;

	/**
	 * Instantiates a new hibernate aware object mapper.
	 */
	public HibernateAwareObjectMapper(){
	      registerModule(new Hibernate4Module());
	  }
}
