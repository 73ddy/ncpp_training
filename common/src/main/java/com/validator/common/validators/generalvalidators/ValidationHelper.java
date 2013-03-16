package com.validator.common.validators.generalvalidators;


/**
 * Validation class used for validation. The class is an implementation of the
 * famous Builder Pattern. In order to validate a param with a certain value.
 * Example- <br />
 * {@code
 *     Validation.checkThat(someString).as(paramName).is(Validation.isNotNull()).add(Validation.isNotEmpty());
 * }</br> This code validates if the string someString is not null and is not
 * empty(of lenght zero).
 * 
 * @author gaurav1935
 */
public final class ValidationHelper {

	private final static GeneralValidator NOT_NULL_VALIDATOR = new NotNullGeneralValidator();
	private final static GeneralValidator NOT_EMPTY_VALIDATOR = new NotEmptyGeneralValidator();
	private final static GeneralValidator EQUALS_VALIDATOR = new EqualsGeneralValidator();
	private final static GeneralValidator GREATER_THAN_VALIDATOR = new GreaterThanGeneralValidator();
	private final static GeneralValidator LESS_THAN_VALIDATOR = new LessThanGeneralValidator();
	private final static GeneralValidator LESS_THAN_EQUALS_TO_VALIDATOR = new LessThanEqualToGeneralValidator();
	private final static GeneralValidator GREATER_THAN_EQUALS_TO_VALIDATOR = new GreaterThanEqualToGeneralValidator();

	/**
	 * Making the constructor private to prevent any instantiation of the
	 * {@link ValidationHelper}
	 */
	private ValidationHelper() {
	}

	/**
	 * Throws IllegalArgumentException if anything other than not null String,
	 * Collection or Array is passed. Throws IllegalStateException if - <br />
	 * <html>
	 * <ul>
	 * <li>1. The value passed is string and is empty.</li>
	 * <li>2. The value passed is collection and is empty.</li>
	 * <li>3. The value passed is an array and either the array is of length
	 * zero or every object of array is null.</li>
	 * </ul>
	 * </html>
	 * 
	 * @return not null validator
	 */
	public static GeneralValidator isNotNull() {
		return NOT_NULL_VALIDATOR;
	}

	/**
	 * Returns a not empty {@link GeneralValidator}
	 * 
	 * @return NOT NULL VALIDATOR
	 */
	public static GeneralValidator isNotEmpty() {
		return NOT_EMPTY_VALIDATOR;
	}

	/**
	 * Returns a not empty {@link GeneralValidator}
	 * 
	 * @return EQUALS VALIDATOR
	 */
	public static GeneralValidator isEqual() {
		return EQUALS_VALIDATOR;
	}

	/**
	 * Returns a not empty {@link GeneralValidator}
	 * 
	 * @return GREATER THAN VALIDATOR
	 */
	public static GeneralValidator isGreaterThan() {
		return GREATER_THAN_VALIDATOR;
	}

	/**
	 * Returns a not empty {@link GeneralValidator}
	 * 
	 * @return GREATER THAN EQUALS TO VALIDATOR
	 */
	public static GeneralValidator isGreaterThanEqualsTo() {
		return GREATER_THAN_EQUALS_TO_VALIDATOR;
	}

	/**
	 * Returns a not empty {@link GeneralValidator}
	 * 
	 * @return LESS THAN VALIDATOR
	 */
	public static GeneralValidator isLessThan() {
		return LESS_THAN_VALIDATOR;
	}

	/**
	 * Returns a not empty {@link GeneralValidator}
	 * 
	 * @return LESS THAN EQUALS TO VALIDATOR
	 */
	public static GeneralValidator isLessEqualsToThan() {
		return LESS_THAN_EQUALS_TO_VALIDATOR;
	}

	/**
	 * Creates a new builder for the given value.
	 * 
	 * @param value
	 * @return {@link Builder}
	 */
	public static Builder validateValue(final Object paramValue) {
		Builder builder = new Builder(paramValue);
		return builder;
	}

	/**
	 * Builder class for Validation
	 * 
	 * @author gaurav1935
	 */
	public static class Builder {
		// The value to be validated
		Object value;

		// The param name for the value to be validated
		Object paramValue;

		/**
		 * Constructor must be always called with the value to be validated.
		 * 
		 * @param {@link Object}
		 */
		public Builder(final Object paramValue) {
			this.value = null;
			this.paramValue = paramValue;
		}

		/**
		 * Sets the parameter name for the value to be validated
		 * 
		 * @param -param name for value to be validated
		 * @return {@link Builder}
		 */
		public Builder against(final Object value) {
			this.value = value;
			return this;
		}

		/**
		 * Validates the value against the provided {@link GeneralValidator} by
		 * running the {@link GeneralValidator#validate(String, Object)} method
		 * 
		 * @param validator
		 * @return {@link Builder}
		 * @throws IllegalArgumentException
		 * @throws IllegalStateException
		 */
		public Constraint usingValidator(final GeneralValidator validator) throws IllegalArgumentException,
				IllegalStateException {
			validator.validate(this.paramValue, this.value);
			return new Constraint(this);
		}

		/**
		 * Returns the value to be validated
		 * 
		 * @return {@link Object}
		 */
		public Object getValue() {
			return this.value;
		}

		/**
		 * Returns the param name of the parameter to be validated
		 * 
		 * @return {@link String}
		 */
		public Object getParamValue() {
			return this.paramValue;
		}
	}

	/**
	 * Each validation returns a Constraint. And each constraint can be added to
	 * a reference of a constraint.
	 * 
	 * @author gaurav1935
	 */
	public static class Constraint {
		Builder builder = null;

		/**
		 * There is no use of a constraint without a builder, which feeds a
		 * constraint the param value and param name
		 * 
		 * @param builder
		 */
		public Constraint(final Builder builder) {
			this.builder = builder;
		}

		/**
		 * This method is responsible for validating the value against one
		 * constraint. This returns an object of Constraint class.
		 * 
		 * @param {@link GeneralValidator}
		 * @return
		 */
		public Constraint add(final GeneralValidator validator) {
			validator.validate(builder.getParamValue(), builder.getValue());
			return this;
		}
	}
}