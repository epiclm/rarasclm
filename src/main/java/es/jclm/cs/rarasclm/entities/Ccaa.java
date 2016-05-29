package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:18:02 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Ccaa generated by hbm2java
 */
@Entity
@Table(name = "ccaa", catalog = "RARASCLM")
public class Ccaa implements java.io.Serializable {

	private String ccaa;
	private String deno;
	private Set<Provincias> provinciases = new HashSet<Provincias>(0);

	public Ccaa() {
	}

	public Ccaa(String ccaa) {
		this.ccaa = ccaa;
	}

	public Ccaa(String ccaa, String deno, Set<Provincias> provinciases) {
		this.ccaa = ccaa;
		this.deno = deno;
		this.provinciases = provinciases;
	}

	@Id

	@Column(name = "ccaa", unique = true, nullable = false, length = 2)
	public String getCcaa() {
		return this.ccaa;
	}

	public void setCcaa(String ccaa) {
		this.ccaa = ccaa;
	}

	@Column(name = "deno", length = 70)
	public String getDeno() {
		return this.deno;
	}

	public void setDeno(String deno) {
		this.deno = deno;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ccaa")
	public Set<Provincias> getProvinciases() {
		return this.provinciases;
	}

	public void setProvinciases(Set<Provincias> provinciases) {
		this.provinciases = provinciases;
	}

}
