package es.jclm.cs.rarasclm.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BasesDiagnosticoSpainRDR  {

	public static List<BaseDiagnostico> get() {
		ArrayList<BaseDiagnostico> ret = new ArrayList<BaseDiagnostico>();
		//1.-Existencia de “evidencia” objetiva de enfermedad (pruebas genéticas, bioquímicas, de imagen, etc.
		BaseDiagnostico bDiag_1 = new BaseDiagnostico();
		bDiag_1.setBaseDTCO(1);
		bDiag_1.setDeno("Existencia de evidencia objetiva de enfermedad (pruebas genéticas, bioquímicas, de imagen...)");
		BaseDiagnostico bDiag_2 = new BaseDiagnostico();
		bDiag_2.setBaseDTCO(2);
		bDiag_2.setDeno("Verificación del diagnóstico en Historia Clínica");
		BaseDiagnostico bDiag_3 = new BaseDiagnostico();
		bDiag_3.setBaseDTCO(3);
		bDiag_3.setDeno("Primera vez que consta en CMBD como C1");
		BaseDiagnostico bDiag_4 = new BaseDiagnostico();
		bDiag_4.setBaseDTCO(4);
		bDiag_4.setDeno("Primera vez que consta en CMBD como C2 o sucesivos");
		BaseDiagnostico bDiag_5 = new BaseDiagnostico();
		bDiag_5.setBaseDTCO(5);
		bDiag_5.setDeno("Otras fuentes de información que no incluyan en sí mismas la validación del diagnóstico");
		BaseDiagnostico bDiag_6 = new BaseDiagnostico();
		bDiag_6.setBaseDTCO(6);
		bDiag_6.setDeno("Diagnóstico en la HC de atención primaria u hospital obtenido por carga masiva de datos, no revisado");
		BaseDiagnostico bDiag_7 = new BaseDiagnostico();
		bDiag_7.setBaseDTCO(7);
		bDiag_7.setDeno("Se ha validado pero no se ha alcanzado una determinación final.");
		BaseDiagnostico bDiag_8 = new BaseDiagnostico();
		bDiag_8.setBaseDTCO(8);
		bDiag_8.setDeno("Tras la validación se está seguro de que no es una enfermedad rara.");
		BaseDiagnostico bDiag_9 = new BaseDiagnostico();
		bDiag_9.setBaseDTCO(9);
		bDiag_9.setDeno("DESCONOCIDO.");
		ret.add(bDiag_1);
		ret.add(bDiag_2);
		ret.add(bDiag_3);
		ret.add(bDiag_4);
		ret.add(bDiag_5);
		ret.add(bDiag_6);
		ret.add(bDiag_7);
		ret.add(bDiag_8);
		ret.add(bDiag_9);
		return ret;
	}
	
	public static BaseDiagnostico get(int baseDiag) {
		Iterator<BaseDiagnostico> it = get().iterator();
		while(it.hasNext()) {
			BaseDiagnostico bDiag = it.next();
			if(bDiag.getBaseDTCO()==baseDiag)
				return bDiag;
		}
		BaseDiagnostico bDiag_9 = new BaseDiagnostico();
		bDiag_9.setBaseDTCO(9);
		bDiag_9.setDeno("DESCONOCIDO.");
		return bDiag_9;
	}
	
}
