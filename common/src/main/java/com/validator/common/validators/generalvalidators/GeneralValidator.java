package com.validator.common.validators.generalvalidators;

/**
 * General Validators must implement this interface.
 * 
 * @author gaurav1935
 */
public interface GeneralValidator {

	/**
	 * Validates the parameter value against a given value.
	 * 
	 * @param paramValue
	 * @param value
	 */
	public void validate(Object paramValue, Object value);

}
