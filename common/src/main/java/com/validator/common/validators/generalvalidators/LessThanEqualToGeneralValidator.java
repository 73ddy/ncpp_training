package com.validator.common.validators.generalvalidators;

import java.util.Date;

/**
 * General validator for validating that a value is less than or equal to the
 * param value.
 * 
 * @author gaurav1935
 */
public class LessThanEqualToGeneralValidator implements GeneralValidator {

	public void validate(Object param, Object value) {
		if (param instanceof String) {
			isLessThanEqualsToString((String) param, value);
		} else if (param instanceof Integer) {
			isLessThanEqualsToInteger((Integer) param, value);
		} else if (param instanceof Boolean) {
			throw new IllegalArgumentException();
		} else if (param instanceof Date) {
			isLessThanEqualsToDate((Date) param, value);
		}

	}

	private void isLessThanEqualsToDate(Date param, Object value) {
		if (value instanceof Date) {
			if (!(param.before((Date) value) || param.equals((Date) value))) {
				throw new IllegalStateException("Given Date does not lie after the supplied date.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isLessThanEqualsToInteger(Integer param, Object value) {
		if (value instanceof Integer) {
			if (!(param <= (Integer) value)) {
				throw new IllegalStateException("Integer is not greater than supplied value.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void isLessThanEqualsToString(String param, Object value) {
		if (value instanceof String) {
			if (param.compareTo((String) value) >= 0) {
				throw new IllegalStateException("String is not greater than supplied value.");
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
}
