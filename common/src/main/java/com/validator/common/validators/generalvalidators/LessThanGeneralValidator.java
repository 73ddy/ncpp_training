package com.validator.common.validators.generalvalidators;

import java.util.Date;

/**
 * General validator for validating that a value is less than the param value.
 * 
 * @author gaurav1935
 */
public class LessThanGeneralValidator implements GeneralValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.validator.common.validation.GeneralValidator#validate(java.lang.Object
	 * , java.lang.Object)
	 */
	public void validate(Object param, Object value) {
		if (param instanceof String) {
			isLessThanString((String) param, value);
		} else if (param instanceof Integer) {
			isLessThanInteger((Integer) param, value);
		} else if (param instanceof Boolean) {
			throw new IllegalArgumentException();
		} else if (param instanceof Date) {
			isLessThanDate((Date) param, value);
		}

	}

	private void isLessThanDate(Date param, Object value) {
		if (value instanceof Date) {
			if (!param.after((Date) value)) {
				throw new IllegalStateException("Given Date does not lie after the supplied date.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isLessThanInteger(Integer param, Object value) {
		if (value instanceof Integer) {
			if (!(param < (Integer) value)) {
				throw new IllegalStateException("Integer is not greater than supplied value.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isLessThanString(String param, Object value) {
		if (value instanceof String) {
			if (param.compareTo((String) value) > 0) {
				throw new IllegalStateException("String is not greater than supplied value.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
}
