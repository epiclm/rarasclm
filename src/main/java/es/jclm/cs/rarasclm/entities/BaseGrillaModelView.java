package es.jclm.cs.rarasclm.entities;

import java.util.List;

public class BaseGrillaModelView<T> {
	private int numRegistrosPorPagina;
	private int numPaginas;
	private int numTotalRegistros;
	private int numPagina;
	private List<T> registros;
	private List<JqGridColModel> colModel;
	private String url;

		
	public int getNumRegistrosPorPagina() {
		return numRegistrosPorPagina;
	}
	public void setNumRegistrosPorPagina(int numRegistrosPorPagina) {
		this.numRegistrosPorPagina = numRegistrosPorPagina;
	}
	public int getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas(int numeroPaginas) {
		this.numPaginas = numeroPaginas;
	}
	public int getNumPagina() {
		return numPagina;
	}
	public void setNumPagina(int numPagina) {
		this.numPagina = numPagina;
	}
	public int getNumTotalRegistros() {
		return numTotalRegistros;
	}
	public void setNumTotalRegistros(int numTotalRegistros) {
		this.numTotalRegistros = numTotalRegistros;
	}
	public List<T> getRegistros() {
		return registros;
	}
	public void setRegistros(List<T> registros) {
		this.registros = registros;
	}
	public List<JqGridColModel> getColModel() {
		return colModel;
	}
	public void setColModel(List<JqGridColModel> colModel) {
		this.colModel = colModel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
