package com.validator.common.validators.generalvalidators;

import java.util.Date;

/**
 * General validator for validating that a value is greater than the param value.
 * 
 * @author gaurav1935
 */
public class EqualsGeneralValidator implements GeneralValidator {

	public void validate(final Object paramValue, final Object value) throws IllegalArgumentException,
			IllegalStateException {
		if (paramValue instanceof String) {
			isEqualToString(paramValue, value);
		} else if (paramValue instanceof Integer) {
			isEqualToInteger(paramValue, value);
		} else if (paramValue instanceof Boolean) {
			isEqualToBoolean(paramValue, value);
		} else if (paramValue instanceof Date) {
			isEqualToDate(paramValue, value);
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isEqualToDate(final Object param, final Object value) {
		if (value instanceof Date) {
			if (!((Date) param).equals((Date) value)) {
				throw new IllegalStateException("Dates are not equal.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isEqualToBoolean(final Object param, final Object value) {
		if (value instanceof Boolean) {
			if (!((Boolean) value).equals((Boolean) param)) {
				throw new IllegalStateException("Booleans are not equal.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isEqualToInteger(final Object param, final Object value) {
		if (value instanceof Integer) {
			if (!((Integer) value).equals(((Integer) param))) {
				throw new IllegalStateException("Integers are not equal.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isEqualToString(final Object param, final Object value) {
		if (value instanceof String) {
			if (!((String) param).equals(((String) value))) {
				throw new IllegalStateException("Strings are not equal.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
}
