package com.validator.common.validators.entityvalidators;

/**
 * All entity validators must implement this Validator interface.
 * 
 * @author gaurav1935
 * 
 * @param <T>
 *            The entity type to be validated.
 */
public interface Validator<T> extends javax.xml.bind.Element {
	/**
	 * Returns true if the given entity is a valid entity as per the validation
	 * rules.
	 * 
	 * @param t
	 *            Entity to be validated
	 * @return true if the entity is valid as per the validation rules.
	 */
	public boolean validate(T t);
}
