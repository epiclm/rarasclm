package es.jclm.cs.rarasclm.entities;

public interface IBaseModel {

	String getBaseapp();

	void setBaseapp(String baseapp);

	MenuModel getMenuModel();

	String getBaseApp();

	DatosAuxiliaresCacheados getCache();

	MensajeResultado getMensaje();

	void setMensaje(MensajeResultado mensaje);

}