package es.jclm.cs.rarasclm.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.core.annotation.Order;

import es.jclm.cs.rarasclm.entities.MergeResult;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.util.MergeEntity;
import es.jclm.cs.rarasclm.util.MergeEntityException;


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
			//p1 <--- p2
			MergeResult<Paciente> o = new MergeEntity<Paciente>().merge(p1, p2);
			String sResultadoApellido02 = o.getMergeObject().getApellido02();
			assertEquals(sResultadoApellido02,p1.getApellido02());
		} catch (MergeEntityException e) {
			e.printStackTrace();
		}
		
	}	
}
