/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.jclm.cs.rarasclm.entities.EnfermedadCie9mc;

// TODO: Auto-generated Javadoc
/**
 * The Class EnfermedadCie9mcValidator.
 */
public class EnfermedadCie9mcValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return EnfermedadCie9mc.class.isAssignableFrom(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		
	}

}
