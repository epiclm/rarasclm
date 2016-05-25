/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie10;



// TODO: Auto-generated Javadoc
/**
 * The Class EnfermedadCie10Validator.
 */
public class EnfermedadCie10Validator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return EnfermedadRaraCie10.class.isAssignableFrom(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		
	}

}
