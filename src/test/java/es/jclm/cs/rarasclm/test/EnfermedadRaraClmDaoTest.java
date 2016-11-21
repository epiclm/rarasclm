package es.jclm.cs.rarasclm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import es.jclm.cs.rarasclm.dao.EnfermedadRaraDao;
import es.jclm.cs.rarasclm.dao.NotFoundException;
import es.jclm.cs.rarasclm.dao.UnableToSaveException;
import es.jclm.cs.rarasclm.entities.EnfermedadRara;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "file:src/test/resources/root-context.xml")
@Order(3)
public class EnfermedadRaraClmDaoTest {
	
	@Autowired
	EnfermedadRaraDao dao;
	
	@Autowired
	protected SessionFactory sf;
	
	public EnfermedadRaraClmDaoTest() {
		System.out.println("EnfermedadDaoTest");
	}
	
	@Test
	public void EnfermedadRaraClm()
	{
		System.out.println("\n--------------------------------------------\n");
		System.out.println("|            BASE DE DATOS ACTIVA          |\n");
		System.out.println("--------------------------------------------\n");
		
		org.hibernate.SessionFactory sessionFactory=sf.getCurrentSession().getSessionFactory();
		ConnectionProvider cp=((SessionFactoryImpl)sessionFactory).getConnectionProvider();
		
		try {
			Connection conex = cp.getConnection();
			String sUrl = conex.getMetaData().getURL();
			System.out.println("\n");
			System.out.println(conex.getClass().getName());
			System.out.println(String.format("\n\n%s\n\n",sUrl));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<EnfermedadRara> l = dao.getAllEnfermedadesRaras();
		int tamanio = l.size();
		System.out.println(String.format("Encontradas %d enfermedades",tamanio));
		System.out.println("Añadiendo una enfermedad rara");
		
		try {
			dao.guardar(new EnfermedadRara("XXXXXXXXXX"));
		} catch (UnableToSaveException e) {
			e.printStackTrace();
		}
		
		l = dao.getAllEnfermedadesRaras();
		int tamanio2 = l.size();
		assertEquals(tamanio+1,tamanio2);
		
		try {
			EnfermedadRara enfBuscar = dao.buscar("XXXXXXXXXX");
			assertNotNull(enfBuscar);
			if(enfBuscar!=null)
				dao.eliminar(enfBuscar);
			l = dao.getAllEnfermedadesRaras();
			int tamanio3 = l.size();
			assertEquals(tamanio, tamanio3);
			
		} catch (NotFoundException | UnableToSaveException e) {
			e.printStackTrace();
		}
		
		
	}
}
