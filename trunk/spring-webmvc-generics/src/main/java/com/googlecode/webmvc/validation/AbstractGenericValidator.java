package com.googlecode.webmvc.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.googlecode.webmvc.web.servlet.generics.util.GenericsUtil;

/**
 * <p>
 *  Generic implementation of spring's
 *  {@link org.springframework.validation.Validator}.
 * </p>
 *
 * <p>
 *  Features added to this Validator not present in the one
 *  provided by the spring framework are:
 *  <ul>
 *      <li>The supported class is automatically determined</li>
 *  </ul>
 * </p>
 * 
 * @param <T>
 */
public abstract class AbstractGenericValidator<T> 
    implements Validator {

    private Class<?> clazz =  null;
    
    /**
     * Creates the AbstractGenericValidator.
     */
    public AbstractGenericValidator() {
        clazz = GenericsUtil.getTypeVariableClassByName(
            this.getClass(), AbstractGenericValidator.class, "T", true);
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public final boolean supports(Class clazz) {
        return this.clazz.isAssignableFrom(clazz);
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public final void validate(Object target, Errors errors) {
    	try {
    		doValidate((T)target, errors);
    	} catch(Exception e) {
    		throw new RuntimeException(e);
    	}
    }
    
    /**
     * Generic version of {@link Validator#validate(Object, Errors)}.
     * @param target the object to validate
     * @param errors the errors
     */
    protected abstract void doValidate(T target, Errors errors)
    	throws Exception;

}
