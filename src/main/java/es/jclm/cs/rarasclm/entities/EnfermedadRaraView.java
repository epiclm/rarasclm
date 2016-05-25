package es.jclm.cs.rarasclm.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class EnfermedadRaraView {
	

	private EnfermedadRara enfRara;
	private List<EnfermedadRaraHasEnfermedadRaraOrphanet> enfermedadRaraHasEnfermedadRaraOrphanets;
    private List<EnfermedadRaraHasEnfermedadRaraCie9mc> enfermedadRaraHasEnfermedadRaraCie9mcs;
    private List<EnfermedadRaraHasEnfermedadRaraOmin> enfermedadRaraHasEnfermedadRaraOmins;
    private List<EnfermedadRaraHasEnfermedadRaraCie10> enfermedadRaraHasEnfermedadRaraCie10s;
    private ArrayList<EnfermedadRaraHasEnfermedadRaraSnomed> enfermedadRaraHasEnfermedadRaraSnomeds;

	public EnfermedadRaraView()
    {
    	this.enfRara = new EnfermedadRara();
    	this.enfermedadRaraHasEnfermedadRaraCie10s = new ArrayList<EnfermedadRaraHasEnfermedadRaraCie10>(0);
		this.enfermedadRaraHasEnfermedadRaraCie9mcs = new ArrayList<EnfermedadRaraHasEnfermedadRaraCie9mc>(0);
		this.enfermedadRaraHasEnfermedadRaraOmins = new ArrayList<EnfermedadRaraHasEnfermedadRaraOmin>(0);
		this.enfermedadRaraHasEnfermedadRaraOrphanets = new ArrayList<EnfermedadRaraHasEnfermedadRaraOrphanet>(0);
		this.enfermedadRaraHasEnfermedadRaraSnomeds = new ArrayList<EnfermedadRaraHasEnfermedadRaraSnomed>(0);
    	
    	
    }
    
	public EnfermedadRaraView(EnfermedadRara enf)
	{
		this.enfermedadRaraHasEnfermedadRaraCie10s = new ArrayList<EnfermedadRaraHasEnfermedadRaraCie10>(enf.getEnfermedadRaraHasEnfermedadRaraCie10s());
		this.enfermedadRaraHasEnfermedadRaraCie9mcs = new ArrayList<EnfermedadRaraHasEnfermedadRaraCie9mc>(enf.getEnfermedadRaraHasEnfermedadRaraCie9mcs());
		this.enfermedadRaraHasEnfermedadRaraOmins = new ArrayList<EnfermedadRaraHasEnfermedadRaraOmin>(enf.getEnfermedadRaraHasEnfermedadRaraOmins());
		this.enfermedadRaraHasEnfermedadRaraOrphanets = new ArrayList<EnfermedadRaraHasEnfermedadRaraOrphanet>(enf.getEnfermedadRaraHasEnfermedadRaraOrphanets());
		this.enfermedadRaraHasEnfermedadRaraSnomeds = new ArrayList<EnfermedadRaraHasEnfermedadRaraSnomed>(enf.getEnfermedadRaraHasEnfermedadRaraSnomeds());
		this.enfRara = enf;
	}
	
    
	public void setEnfRara(EnfermedadRara enfRara) {
		this.enfermedadRaraHasEnfermedadRaraCie10s = new ArrayList<EnfermedadRaraHasEnfermedadRaraCie10>(enfRara.getEnfermedadRaraHasEnfermedadRaraCie10s());
		this.enfermedadRaraHasEnfermedadRaraCie9mcs = new ArrayList<EnfermedadRaraHasEnfermedadRaraCie9mc>(enfRara.getEnfermedadRaraHasEnfermedadRaraCie9mcs());
		this.enfermedadRaraHasEnfermedadRaraOmins = new ArrayList<EnfermedadRaraHasEnfermedadRaraOmin>(enfRara.getEnfermedadRaraHasEnfermedadRaraOmins());
		this.enfermedadRaraHasEnfermedadRaraOrphanets = new ArrayList<EnfermedadRaraHasEnfermedadRaraOrphanet>(enfRara.getEnfermedadRaraHasEnfermedadRaraOrphanets());
		this.enfermedadRaraHasEnfermedadRaraSnomeds = new ArrayList<EnfermedadRaraHasEnfermedadRaraSnomed>(enfRara.getEnfermedadRaraHasEnfermedadRaraSnomeds());
		this.enfRara = enfRara;
	}


	
    public EnfermedadRara getEnfRara() {
    	this.enfRara.setEnfermedadRaraHasEnfermedadRaraCie10s(new HashSet<EnfermedadRaraHasEnfermedadRaraCie10>(this.enfermedadRaraHasEnfermedadRaraCie10s));
    	this.enfRara.setEnfermedadRaraHasEnfermedadRaraCie9mcs(new HashSet<EnfermedadRaraHasEnfermedadRaraCie9mc>(this.enfermedadRaraHasEnfermedadRaraCie9mcs));
    	this.enfRara.setEnfermedadRaraHasEnfermedadRaraOmins(new HashSet<EnfermedadRaraHasEnfermedadRaraOmin>(this.enfermedadRaraHasEnfermedadRaraOmins));
    	this.enfRara.setEnfermedadRaraHasEnfermedadRaraOrphanets(new HashSet<EnfermedadRaraHasEnfermedadRaraOrphanet>(this.enfermedadRaraHasEnfermedadRaraOrphanets));
    	this.enfRara.setEnfermedadRaraHasEnfermedadRaraSnomeds(new HashSet<EnfermedadRaraHasEnfermedadRaraSnomed>(this.enfermedadRaraHasEnfermedadRaraSnomeds));
		return enfRara;
	}

	public String getEnfermedadRaraId() {
		return enfRara.getEnfermedadRaraId();
	}

	public void setEnfermedadRaraId(String enfermedadRaraId) {
		this.enfRara.setEnfermedadRaraId(enfermedadRaraId);
	}

	public EnfermedadRenal getEnfermedadRenal() {
		return this.enfRara.getEnfermedadRenal();
	}

	public void setEnfermedadRenal(EnfermedadRenal enfermedadRenal) {
		this.enfRara.setEnfermedadRenal(enfermedadRenal);
	}

	public String getLiteral() {
		return this.enfRara.getLiteral();
	}

	public void setLiteral(String literal) {
		this.enfRara.setLiteral(literal);
	}

	public String getOtroCod() {
		return this.enfRara.getOtroCod();
	}

	public void setOtroCod(String otroCod) {
		this.enfRara.setOtroCod(otroCod);
	}

	public String getDenoOtro() {
		return this.enfRara.getDenoOtro();
	}

	public void setDenoOtro(String denoOtro) {
		this.enfRara.setDenoOtro(denoOtro);
	}

	public String getDescripcion() {
		return this.enfRara.getDescripcion();
	}

	public void setDescripcion(String descripcion) {
		this.enfRara.setDescripcion(descripcion);
	}

	public String getUrl() {
		return this.enfRara.getUrl();
	}

	public void setUrl(String url) {
		this.enfRara.setUrl(url);
	}

	public Date getUltimaActualizacion() {
		return this.enfRara.getUltimaActualizacion();
	}

	public void setUltimaActualizacion(Date ultimaActualizacion) {
		this.enfRara.setUltimaActualizacion(ultimaActualizacion);
	}

	public String getNotas() {
		return this.enfRara.getNotas();
	}

	public void setNotas(String notas) {
		this.enfRara.setNotas(notas);
	}

	public List<EnfermedadRaraHasEnfermedadRaraOrphanet> getEnfermedadRaraHasEnfermedadRaraOrphanets() {
		return enfermedadRaraHasEnfermedadRaraOrphanets;
	}

	public void setEnfermedadRaraHasEnfermedadRaraOrphanets(
			List<EnfermedadRaraHasEnfermedadRaraOrphanet> enfermedadRaraHasEnfermedadRaraOrphanets) {
		this.enfermedadRaraHasEnfermedadRaraOrphanets = enfermedadRaraHasEnfermedadRaraOrphanets;
	}

	public List<EnfermedadRaraHasEnfermedadRaraCie9mc> getEnfermedadRaraHasEnfermedadRaraCie9mcs() {
		
		Collections.sort(enfermedadRaraHasEnfermedadRaraCie9mcs, new Comparator<EnfermedadRaraHasEnfermedadRaraCie9mc>() {
			public int compare(EnfermedadRaraHasEnfermedadRaraCie9mc o1, EnfermedadRaraHasEnfermedadRaraCie9mc o2) {
				return o1.getId().getCie9Id().compareTo(o2.getId().getCie9Id());
			}
		});
		return enfermedadRaraHasEnfermedadRaraCie9mcs;
		
	}

	public void setEnfermedadRaraHasEnfermedadRaraCie9mcs(
			List<EnfermedadRaraHasEnfermedadRaraCie9mc> enfermedadRaraHasEnfermedadRaraCie9mcs) {
		this.enfermedadRaraHasEnfermedadRaraCie9mcs = enfermedadRaraHasEnfermedadRaraCie9mcs;
	}

	public List<EnfermedadRaraHasEnfermedadRaraOmin> getEnfermedadRaraHasEnfermedadRaraOmins() {
		return enfermedadRaraHasEnfermedadRaraOmins;
	}

	public void setEnfermedadRaraHasEnfermedadRaraOmins(
			List<EnfermedadRaraHasEnfermedadRaraOmin> enfermedadRaraHasEnfermedadRaraOmins) {
		this.enfermedadRaraHasEnfermedadRaraOmins = enfermedadRaraHasEnfermedadRaraOmins;
	}

	public List<EnfermedadRaraHasEnfermedadRaraCie10> getEnfermedadRaraHasEnfermedadRaraCie10s() {
		return enfermedadRaraHasEnfermedadRaraCie10s;
	}

	public void setEnfermedadRaraHasEnfermedadRaraCie10s(
			List<EnfermedadRaraHasEnfermedadRaraCie10> enfermedadRaraHasEnfermedadRaraCie10s) {
		this.enfermedadRaraHasEnfermedadRaraCie10s = enfermedadRaraHasEnfermedadRaraCie10s;
	}

	public ArrayList<EnfermedadRaraHasEnfermedadRaraSnomed> getEnfermedadRaraHasEnfermedadRaraSnomeds() {
		return enfermedadRaraHasEnfermedadRaraSnomeds;
	}

	public void setEnfermedadRaraHasEnfermedadRaraSnomeds(
			ArrayList<EnfermedadRaraHasEnfermedadRaraSnomed> enfermedadRaraHasEnfermedadRaraSnomeds) {
		this.enfermedadRaraHasEnfermedadRaraSnomeds = enfermedadRaraHasEnfermedadRaraSnomeds;
	}
	
}
