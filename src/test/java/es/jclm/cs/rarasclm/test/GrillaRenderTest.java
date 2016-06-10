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

import es.jclm.cs.rarasclm.entities.BaseGrillaModelView;
import es.jclm.cs.rarasclm.entities.UserRarasCLM;
import es.jclm.cs.rarasclm.util.RenderJqGrid;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/test/config/spring-app.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Order(1)
public class GrillaRenderTest {
	private List<UserRarasCLM> listaUsuarios= new ArrayList<UserRarasCLM>(); 
	
	@Test
	public final void A_creaLista()
	{

	}
	
	@Test
	public final void B_RenderizaJS()
	{
		UserRarasCLM user1= new UserRarasCLM();
		user1.setApellido01("Java");
		user1.setApellido02("Obsoleto");
		user1.setNombre("Lenguaje");
		user1.setNombre("javakk");
		UserRarasCLM user2= new UserRarasCLM();
		user1.setApellido01("Java");
		user1.setApellido02("Oracle");
		user1.setNombre("LaRuina");
		user1.setNombre("OracleCompraNoDesarrolla");
		listaUsuarios.add(user1);
		listaUsuarios.add(user2);
		BaseGrillaModelView<UserRarasCLM> baseGrillaModel = new BaseGrillaModelView<UserRarasCLM>();
		baseGrillaModel.setNumRegistrosPorPagina(2);
		baseGrillaModel.setNumTotalRegistros(2);
		baseGrillaModel.setRegistros(listaUsuarios);
		RenderJqGrid<UserRarasCLM> render = new RenderJqGrid<>("#pruJqGrilla", baseGrillaModel,"pruebaGrilla");
		List<String> colNames = new ArrayList<>();
		//colNames.add("Apellido1")
		//System.out.println(render.getJsGrid(, widths, key));
	}
}
