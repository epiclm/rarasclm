package es.jclm.cs.rarasclm.entities;
// Generated 15-jun-2016 6:48:58 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Provincias generated by hbm2java
 */
@Entity
@Table(name = "provincias", catalog = "RARASCLM")
public class Provincias implements java.io.Serializable {

	private String provincia;
	private Ccaa ccaa;
	private String deno;
	private Set<Municipios> municipioses = new HashSet<Municipios>(0);

	public Provincias() {
	}

	public Provincias(String provincia) {
		this.provincia = provincia;
	}

	public Provincias(String provincia, Ccaa ccaa, String deno, Set<Municipios> municipioses) {
		this.provincia = provincia;
		this.ccaa = ccaa;
		this.deno = deno;
		this.municipioses = municipioses;
	}

	@Id

	@Column(name = "provincia", unique = true, nullable = false, length = 2)
	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ccaa")
	public Ccaa getCcaa() {
		return this.ccaa;
	}

	public void setCcaa(Ccaa ccaa) {
		this.ccaa = ccaa;
	}

	@Column(name = "deno", length = 70)
	public String getDeno() {
		return this.deno;
	}

	public void setDeno(String deno) {
		this.deno = deno;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "provincias")
	public Set<Municipios> getMunicipioses() {
		return this.municipioses;
	}

	public void setMunicipioses(Set<Municipios> municipioses) {
		this.municipioses = municipioses;
	}

}
