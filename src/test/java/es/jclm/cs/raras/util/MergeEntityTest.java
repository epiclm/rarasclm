package es.jclm.cs.raras.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.util.MergeEntity;
import es.jclm.cs.rarasclm.util.MergeEntityException;



@ComponentScan(basePackages={"es.jclm.cs.rarasclm.dao"})
@ContextConfiguration(locations = "file:src/test/config/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Order(1)
public class MergeEntityTest {
	
	@Test
	public void test01() {
		Paciente p1 = new Paciente();
		Paciente p2 = new Paciente();
		
		p1.setApellido01("ORTEGA");
		p1.setNombre("RICARDO");
		
		p2.setApellido01("ORTEGA");
		p2.setNombre("RICARDO");
		p2.setApellido02("GALIANA");
		
		try {
			Paciente o = new MergeEntity<Paciente>().merge(p1, p2);
			String sResultado = o.getApellido02();
		} catch (MergeEntityException e) {
			e.printStackTrace();
		}
		
	}	
}
