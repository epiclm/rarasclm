package es.jclm.cs.rarasclm.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.jclm.cs.rarasclm.entities.EnfermedadRara;

@ComponentScan(basePackages={"es.jclm.cs.rarasclm.dao"})
@ContextConfiguration(locations = "file:src/test/config/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class EnfermedadRaraDaoTest {
	
	@Autowired
	EnfermedadRaraDao dao;
	
	public EnfermedadRaraDaoTest() {
		System.out.println("EnfermedadDaoTest");
	}
	
	@Test
	public void EnfermedadRaraListar()
	{
		List<EnfermedadRara> l = dao.getAllEnfermedadesRaras();
		System.out.println(l.size());
	}
}
