package es.jclm.cs.rarasclm.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.jclm.cs.rarasclm.entities.DatosAuxiliaresCacheados;
import org.junit.Assert;

@ComponentScan(basePackages={"es.jclm.cs.rarasclm.dao"})
@ContextConfiguration(locations = "file:src/test/config/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Order(3)
public class AuxiliaresTest {


	
	
	public AuxiliaresTest() {
		System.out.println("Auxiliares TEST");
	}
	
	@Test
	public void Edad()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaNacimiento;
		try {
			fechaNacimiento = formatter.parse("19/06/1975");
			DatosAuxiliaresCacheados auxiliares = new DatosAuxiliaresCacheados();
			int edad = auxiliares.getEdad(fechaNacimiento);
			Assert.assertTrue("La edad tiene que ser positiva",edad>0);
		} catch (ParseException e) {
			
		}
	}
	
}
