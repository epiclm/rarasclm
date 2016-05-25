/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.listeners;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mchange.v2.c3p0.ConnectionCustomizer;


// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving conexionDB events. The class that is
 * interested in processing a conexionDB event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addConexionDBListener<code> method. When the conexionDB
 * event occurs, that object's appropriate method is invoked.
 *
 * @see ConexionDBEvent
 */
public class ConexionDBListener implements ConnectionCustomizer {

	/** The log. */
	static Log log = LogFactory.getLog(DataContextRarasClmAppListener.class.getName());
	
	
	public ConexionDBListener() {
		log.info("Instanciando DB Listener");
	}
	
	/* (non-Javadoc)
	 * @see com.mchange.v2.c3p0.ConnectionCustomizer#onAcquire(java.sql.Connection, java.lang.String)
	 */
	public void onAcquire(Connection connection, String cs) throws Exception {
		
		
	}

	/* (non-Javadoc)
	 * @see com.mchange.v2.c3p0.ConnectionCustomizer#onCheckIn(java.sql.Connection, java.lang.String)
	 */
	public void onCheckIn(Connection connection, String cs) throws Exception {
	
		
	}

	/* (non-Javadoc)
	 * @see com.mchange.v2.c3p0.ConnectionCustomizer#onCheckOut(java.sql.Connection, java.lang.String)
	 */
	public void onCheckOut(Connection connection, String cs) throws Exception {
		
		
	}

	/* (non-Javadoc)
	 * @see com.mchange.v2.c3p0.ConnectionCustomizer#onDestroy(java.sql.Connection, java.lang.String)
	 */
	public void onDestroy(Connection connection, String cs) throws Exception {
		
		
	}


}
