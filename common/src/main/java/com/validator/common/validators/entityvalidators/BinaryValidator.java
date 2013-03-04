package com.validator.common.validators.entityvalidators;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.validator.common.operators.Operator;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class BinaryValidator<T> implements Validator<T> {

	@XmlElements({
			@XmlElement(name = "atomicValidatorLHS", type = AtomicValidator.class),
			@XmlElement(name = "binaryValidatorLHS", type = BinaryValidator.class)})
	private Validator<T> validatorLHS;
	
	@XmlElements({
			@XmlElement(name = "atomicValidatorRHS", type = AtomicValidator.class),
			@XmlElement(name = "binaryValidatorRHS", type = BinaryValidator.class)})
	private Validator<T> validatorRHS;

	@XmlAnyElement(lax = true)
	private Operator<Boolean> operator;

	public BinaryValidator() {
	}

	public BinaryValidator(Validator<T> validatorLHS,
			Validator<T> validatorRHS, Operator booleanOperator) {
		this.validatorLHS = validatorLHS;
		this.validatorRHS = validatorRHS;
		this.operator = booleanOperator;
	}

	public Validator<T> getValidatorLHS() {
		return validatorLHS;
	}

	public void setValidatorLHS(Validator<T> validatorLHS) {
		this.validatorLHS = validatorLHS;
	}

	public Validator<T> getValidatorRHS() {
		return validatorRHS;
	}

	public void setValidatorRHS(Validator<T> validatorRHS) {
		this.validatorRHS = validatorRHS;
	}

	public Operator<Boolean> getOperator() {
		return operator;
	}

	public boolean validate(T t) {
		boolean validator;
		isLegal();

		return operator.operate(validatorLHS.validate(t),
				validatorRHS.validate(t));
	}

	private void isLegal() {
		if ((null == operator)) {
			throw new IllegalStateException("Operator cannot be null.");
		} else if (((null == validatorLHS) || (null == validatorRHS))) {
			throw new IllegalStateException(
					"Atleast one validator must be set for a compound validator.");
		}
	}

	public void setOperator(Operator<Boolean> operator) {
		this.operator = operator;
	}

}
