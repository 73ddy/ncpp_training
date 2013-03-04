package com.validator.common.validators.generalvalidators;

import java.util.Date;

/**
 * General validator for validating that a value is equal to the param value.
 * 
 * @author gaurav1935
 */
public class GreaterThanGeneralValidator implements GeneralValidator {

	public void validate(Object paramValue, Object value) {
		if (paramValue instanceof String) {
			isGreaterThanString((String) paramValue, value);
		} else if (paramValue instanceof Integer) {
			isGreaterThanInteger((Integer) paramValue, value);
		} else if (paramValue instanceof Boolean) {
			throw new IllegalArgumentException();
		} else if (paramValue instanceof Date) {
			isGreaterThanDate((Date) paramValue, value);
		}

	}

	private void isGreaterThanDate(Date param, Object value) {
		if (value instanceof Date) {
			if (!param.after((Date) value)) {
				throw new IllegalStateException("Given Date does not lie after the supplied date.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isGreaterThanInteger(Integer param, Object value) {
		if (value instanceof Integer) {
			if (!(param > (Integer) value)) {
				throw new IllegalStateException("Integer is not greater than supplied value.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isGreaterThanString(String param, Object value) {
		if (value instanceof String) {
			if (param.compareTo((String) value) < 0) {
				throw new IllegalStateException("String is not greater than supplied value.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
}
