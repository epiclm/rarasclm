package es.jclm.cs.rarasclm.entities;

public class FieldChange {

	private String fieldName;
	private String cannonicalClassName;
	private String serializeOldValue;
	private String serializeNewValue;
	private String tableName;
	private String fieldDBname;
	
	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getCannonicalClassName() {
		return cannonicalClassName;
	}
	
	public void setCannonicalClassName(String cannonicalClassName) {
		this.cannonicalClassName = cannonicalClassName;
	}
	
	public String getSerializeOldValue() {
		return serializeOldValue;
	}
	
	public void setSerializeOldValue(String serializeOldValue) {
		this.serializeOldValue = serializeOldValue;
	}
	
	public String getSerializeNewValue() {
		return serializeNewValue;
	}
	
	public void setSerializeNewValue(String serializeNewValue) {
		this.serializeNewValue = serializeNewValue;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getFieldDBname() {
		return fieldDBname;
	}
	
	public void setFieldDBname(String fieldDBname) {
		this.fieldDBname = fieldDBname;
	}
	
	
}
