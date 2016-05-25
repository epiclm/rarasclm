/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import es.jclm.cs.rarasclm.entities.EnfermedadRara;



// TODO: Auto-generated Javadoc
/**
 * The Class EnfermedadRaraValidator.
 */
public class EnfermedadRaraValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return EnfermedadRara.class.isAssignableFrom(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		
	}

}
