package com.validator.common.validators.generalvalidators;

/**
 * General validator for validating that a value is not null.
 * 
 * @author gaurav1935
 */
public class NotNullGeneralValidator implements GeneralValidator {

	/* (non-Javadoc)
	 * @see com.validator.common.validation.GeneralValidator#validate(java.lang.Object, java.lang.Object)
	 */
	public void validate(final Object paramValue, final Object value) throws IllegalArgumentException,
			IllegalStateException {
		if (null == paramValue) {
			throw new IllegalStateException("Null value found when expecting not null value.");
		}
	}
}
