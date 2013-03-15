package com.validator.common.validators.entityvalidators;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.validator.common.operators.binary.logical.BinaryLogicalOperator;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class BinaryValidator<T> implements Validator<T> {

	@XmlElements({ @XmlElement(name = "atomicValidatorLHS", type = AtomicValidator.class),
			@XmlElement(name = "binaryValidatorLHS", type = BinaryValidator.class) })
	private Validator<T> validatorLHS;

	@XmlElements({ @XmlElement(name = "atomicValidatorRHS", type = AtomicValidator.class),
			@XmlElement(name = "binaryValidatorRHS", type = BinaryValidator.class) })
	private Validator<T> validatorRHS;

	@XmlAnyElement(lax = true)
	private BinaryLogicalOperator<Boolean> binaryLogicalOperator;

	public BinaryValidator() {
	}

	public BinaryValidator(Validator<T> validatorLHS, Validator<T> validatorRHS, BinaryLogicalOperator<Boolean> booleanOperator) {
		this.validatorLHS = validatorLHS;
		this.validatorRHS = validatorRHS;
		this.binaryLogicalOperator = booleanOperator;
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

	public BinaryLogicalOperator<Boolean> getOperator() {
		return binaryLogicalOperator;
	}

	public boolean validate(T t) {
		isLegal();

		return binaryLogicalOperator.operate(validatorLHS.validate(t), validatorRHS.validate(t));
	}

	private void isLegal() {
		if ((null == binaryLogicalOperator)) {
			throw new IllegalStateException("Operator cannot be null.");
		} else if (((null == validatorLHS) || (null == validatorRHS))) {
			throw new IllegalStateException("Atleast one validator must be set for a compound validator.");
		}
	}

	public void setOperator(BinaryLogicalOperator<Boolean> operator) {
		this.binaryLogicalOperator = operator;
	}

}
