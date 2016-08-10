package es.jclm.cs.rarasclm.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.jclm.cs.rarasclm.entities.UserRarasClm;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/config/spring-app.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Order(2)
public class GrillaRenderTest {
	private List<UserRarasClm> listaUsuarios= new ArrayList<UserRarasClm>(); 
	
	@Test
	public final void A_creaLista()
	{

	}
	
	@Test
	public final void B_RenderizaJS()
	{
		
	}
}
