package com.validator.common.validators.generalvalidators;

import java.util.Collection;

/**
 * General validator for validating that a value is not empty.
 * 
 * @author gaurav1935
 */
public class NotEmptyGeneralValidator implements GeneralValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.poc.designpatterns.builderpattern.Validator#validate(java.lang
	 * .String, java.lang.Object)
	 */
	public void validate(final Object paramValue, final Object value) throws IllegalArgumentException,
			IllegalStateException {
		if ((null == paramValue)
				|| (!(paramValue instanceof String) && !(paramValue instanceof Collection) && !(paramValue.getClass()
						.isArray()))) {
			throw new IllegalArgumentException("Only not null Strings, Collection or Arrays are supported.");
		}

		if ((paramValue instanceof String)) {
			if (((String) paramValue).length() == 0) {
				throw new IllegalStateException("Empty string found when expecting: non-empty string.");
			}
		} else if (paramValue instanceof Collection) {
			if (((Collection<?>) paramValue).size() == 0) {
				throw new IllegalStateException("Empty collection found when expecting: non-empty collection.");
			}
		} else if (paramValue.getClass().isArray()) {
			boolean isEmpty = true;
			for (Object obj : ((Object[]) paramValue)) {
				if (null != obj) {
					isEmpty = false;
					break;
				}
			}
			if (isEmpty) {
				throw new IllegalStateException("Empty array found when expecting: non-empty array.");
			}
		}
	}
}
