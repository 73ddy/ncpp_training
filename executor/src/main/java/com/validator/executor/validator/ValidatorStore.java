package com.validator.executor.validator;

import com.google.inject.Singleton;
import com.validator.common.validators.entityvalidators.Validator;

@Singleton
public class ValidatorStore {
	@SuppressWarnings("rawtypes")
	Validator validator = null;

	@SuppressWarnings("rawtypes")
	public Validator getValidator() {
		return validator;
	}

	@SuppressWarnings("rawtypes")
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
}
