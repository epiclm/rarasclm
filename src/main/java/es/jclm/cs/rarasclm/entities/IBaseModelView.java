package es.jclm.cs.rarasclm.entities;

public interface IBaseModelView {

	String getBaseApp();
	MenuModel getMenuModel();
	DatosAuxiliaresCacheados getCache();
	MensajeResultado getMensaje();
	void setMensaje(MensajeResultado mensaje);
}