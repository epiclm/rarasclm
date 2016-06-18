package es.jclm.cs.rarasclm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MergeEntity<T> {
	
	public T merge(T oldPersist,T update) throws MergeEntityException{
			
		List<Method> setterMethodsOld = getSetterMethods(oldPersist);
		List<Method> getterMethodsUpd = getGetterMethods(update);
		Object valueUpd = null;
		Object valueOld = null;
		
		for(Method mg : getterMethodsUpd) {
			for(Method ms : setterMethodsOld) {
				if(mg.getName().substring(3).equals(ms.getName().substring(3))) {
				  try {  
					valueUpd = mg.invoke(update, (Object[])null);
					valueOld = mg.invoke(oldPersist, (Object[])null);
					if((mg.getReturnType() == Date.class || mg.getReturnType() == GregorianCalendar.class || valueUpd!=null) 
							&& (valueUpd==null || !valueUpd.equals(valueOld)))
						ms.invoke(oldPersist, valueUpd);
				} catch(Exception ex) {
					new MergeEntityException(String.format("error de \"merge\" casuado por %s", ex.getMessage()));
				}			
			}
		}
	}
	return oldPersist;
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
