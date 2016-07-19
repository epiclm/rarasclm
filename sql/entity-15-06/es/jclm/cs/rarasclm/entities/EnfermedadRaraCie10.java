package es.jclm.cs.rarasclm.entities;
// Generated 15-jun-2016 6:48:58 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EnfermedadRaraCie10 generated by hbm2java
 */
@Entity
@Table(name = "enfermedad_rara_cie10", catalog = "rarasclm")
public class EnfermedadRaraCie10 implements java.io.Serializable {

	private String cie10Id;
	private String literal;
	private String url;
	private String notas;
	private Set<EnfermedadRaraHasEnfermedadRaraCie10> enfermedadRaraHasEnfermedadRaraCie10s = new HashSet<EnfermedadRaraHasEnfermedadRaraCie10>(
			0);

	public EnfermedadRaraCie10() {
	}

	public EnfermedadRaraCie10(String cie10Id) {
		this.cie10Id = cie10Id;
	}

	public EnfermedadRaraCie10(String cie10Id, String literal, String url, String notas,
			Set<EnfermedadRaraHasEnfermedadRaraCie10> enfermedadRaraHasEnfermedadRaraCie10s) {
		this.cie10Id = cie10Id;
		this.literal = literal;
		this.url = url;
		this.notas = notas;
		this.enfermedadRaraHasEnfermedadRaraCie10s = enfermedadRaraHasEnfermedadRaraCie10s;
	}

	@Id

	@Column(name = "cie10_id", unique = true, nullable = false, length = 5)
	public String getCie10Id() {
		return this.cie10Id;
	}

	public void setCie10Id(String cie10Id) {
		this.cie10Id = cie10Id;
	}

	@Column(name = "literal")
	public String getLiteral() {
		return this.literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "notas")
	public String getNotas() {
		return this.notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enfermedadRaraCie10")
	public Set<EnfermedadRaraHasEnfermedadRaraCie10> getEnfermedadRaraHasEnfermedadRaraCie10s() {
		return this.enfermedadRaraHasEnfermedadRaraCie10s;
	}

	public void setEnfermedadRaraHasEnfermedadRaraCie10s(
			Set<EnfermedadRaraHasEnfermedadRaraCie10> enfermedadRaraHasEnfermedadRaraCie10s) {
		this.enfermedadRaraHasEnfermedadRaraCie10s = enfermedadRaraHasEnfermedadRaraCie10s;
	}

}
