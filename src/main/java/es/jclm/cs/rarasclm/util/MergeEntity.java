package es.jclm.cs.rarasclm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

import es.jclm.cs.rarasclm.entities.FieldChange;
import es.jclm.cs.rarasclm.entities.MergeResult;

public class MergeEntity<T> {
	
	public MergeResult<T> merge(T oldPersist,T update) throws MergeEntityException {
			
		List<Method> setterMethodsOld = getSetterMethods(oldPersist);
		List<Method> getterMethodsUpd = getGetterMethods(update);
		Object valueUpd = null;
		Object valueOld = null;
		
		MergeResult<T> result = new MergeResult<T>();
		ArrayList<FieldChange> listaCambios = new ArrayList<FieldChange>();
		result.setFieldsChange(listaCambios);
		
		for(Method mg : getterMethodsUpd) {
			for(Method ms : setterMethodsOld) {
				if(mg.getName().substring(3).equals(ms.getName().substring(3))) {
				  try {  
					valueUpd = mg.invoke(update, (Object[])null);
					valueOld = mg.invoke(oldPersist, (Object[])null);
					if((mg.getReturnType() == Date.class || mg.getReturnType() == GregorianCalendar.class || valueUpd!=null) 
							&& (valueUpd==null || !valueUpd.equals(valueOld))
							&& (valueUpd!=null || valueOld!=null)) {
						ms.invoke(oldPersist, valueUpd);
						FieldChange cambio = new FieldChange();
						cambio.setCannonicalClassName(mg.getReturnType().getCanonicalName());
						cambio.setFieldName(mg.getName().substring(3));
						if(mg.getAnnotation(Column.class)!=null) {
							cambio.setTableName(mg.getAnnotation(Column.class).name());
						}
						if(oldPersist.getClass().getAnnotation(Table.class)!=null) {
							cambio.setFieldDBname(oldPersist.getClass().getAnnotation(Table.class).name());
						}
						cambio.setSerializeOldValue(valueOld.toString());
						cambio.setSerializeNewValue(valueUpd.toString());
						result.getFieldsChange().add(cambio);
					}					
					result.setMergeObject(oldPersist);
				} catch(Exception ex) {
					new MergeEntityException(String.format("error de \"merge\" casuado por %s", ex.getMessage()));
				}			
			}
		}
	}
	return result;
}
	
	//java ugly fields method confusion
	private List<Method> getGetterMethods(T o) {
		
		ArrayList<Method> ret = new ArrayList<Method>();
		Class<?> clazz = o.getClass();
		
		Method[] metodos = clazz.getMethods();	
		Field[] campos = clazz.getDeclaredFields();
		
		for(Field c : campos) {
			for(Method m : metodos) {
				if(("get"+c.getName().substring(0,1).toUpperCase()+c.getName().substring(1)).equals(m.getName())) {
					ret.add(m);
				}
			}
		}
		return ret;
	}
	
	//java ugly fields method confusion
	private List<Method> getSetterMethods(T o) {
		
		ArrayList<Method> ret = new ArrayList<Method>();
		Class<?> clazz = o.getClass();
		
		Method[] metodos = clazz.getMethods();	
		Field[] campos = clazz.getDeclaredFields();
		
		for(Field c : campos) {
			for(Method m : metodos) {
				if(("set"+c.getName().substring(0,1).toUpperCase()+c.getName().substring(1)).equals(m.getName())) {
						ret.add(m);
				}
			}
		}
		return ret;
	}
	
}
