package es.jclm.cs.rarasclm.entities;

public class EstadisticaModelCasosBaseDTCO {
	
	private BaseDiagnostico baseDiagnostico;
	private long numCasos;
	
	public EstadisticaModelCasosBaseDTCO(Byte baseDTCO,long numCasos) {
		this.baseDiagnostico = BasesDiagnosticoSpainRDR.get(Integer.valueOf(baseDTCO));
		this.numCasos = numCasos;
	}
	
	
	public BaseDiagnostico getBaseDiagnostico() {
		return baseDiagnostico;
	}
	
	public void setBaseDiagnostico(BaseDiagnostico baseDiagnostico) {
		this.baseDiagnostico = baseDiagnostico;
	}
	
	public long getNumCasos() {
		return numCasos;
	}
	
	public void setNumCasos(long numCasos) {
		this.numCasos = numCasos;
	}
	
}
