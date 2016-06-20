package es.jclm.cs.rarasclm.entities;

import java.util.List;

public class MergeResult<T> {
	
	private T mergeObject;
	private List<FieldChange> fieldsChange;
	
	public T getMergeObject() {
		return mergeObject;
	}
	public void setMergeObject(T mergeObject) {
		this.mergeObject = mergeObject;
	}
	public List<FieldChange> getFieldsChange() {
		return fieldsChange;
	}
	public void setFieldsChange(List<FieldChange> fieldsChange) {
		this.fieldsChange = fieldsChange;
	}
	
	
	
}
