package com.validator.common.validators.generalvalidators;

import java.util.Date;

/**
 * General validator for validating that a value is equal to the param value.
 * 
 * @author gaurav1935
 */
public class GreaterThanEqualToGeneralValidator implements GeneralValidator {

	public void validate(Object param, Object value) {
		if (param instanceof String) {
			isGreaterThanEqualsToString((String) param, value);
		} else if (param instanceof Integer) {
			isGreaterThanEqualsToInteger((Integer) param, value);
		} else if (param instanceof Boolean) {
			throw new IllegalArgumentException();
		} else if (param instanceof Date) {
			isGreaterThanEqualsToDate((Date) param, value);
		}

	}

	private void isGreaterThanEqualsToDate(Date param, Object value) {
		if (value instanceof Date) {
			if (!(param.after((Date) value) || param.equals(value))) {
				throw new IllegalStateException("Given Date does not lie after the supplied date.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isGreaterThanEqualsToInteger(Integer param, Object value) {
		if (value instanceof Integer) {
			if (!(param >= (Integer) value)) {
				throw new IllegalStateException("Integer is not greater than supplied value.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isGreaterThanEqualsToString(String param, Object value) {
		if (value instanceof String) {
			if (param.compareTo((String) value) <= 0) {
				throw new IllegalStateException("String is not greater than supplied value.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
}
