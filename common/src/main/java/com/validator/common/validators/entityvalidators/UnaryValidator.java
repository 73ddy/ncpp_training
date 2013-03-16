package com.validator.common.validators.entityvalidators;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.validator.common.operators.Operator;

/**
 * UnaryValidator is for unary validations where a unary operator is applied on
 * a Validator.
 * 
 * @author gaurav1935
 * 
 * @param <T>
 *            the entity type to be validated.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class UnaryValidator<T> implements Validator<T> {

	@XmlElements({ @XmlElement(name = "atomicValidator", type = AtomicValidator.class),
			@XmlElement(name = "binaryValidator", type = BinaryValidator.class) })
	private Validator<T> validator;
	@XmlAnyElement(lax = true)
	private Operator<Boolean> operator;

	public UnaryValidator() {
	}

	@SuppressWarnings("unchecked")
	public UnaryValidator(Validator<T> validator, @SuppressWarnings("rawtypes") Operator booleanOperator) {
		this.validator = validator;
		this.operator = booleanOperator;
	}

	public Validator<T> getValidator() {
		return validator;
	}

	public void setValidator(Validator<T> validator) {
		this.validator = validator;
	}

	public boolean validate(T t) {
		isLegal();
		return operator.operate(validator.validate(t), true);
	}

	private void isLegal() {
		if ((null == operator) || (null == validator)) {
			throw new IllegalStateException("Illegal Unary Operator.");
		}
	}

	public Operator<Boolean> getOperator() {
		return operator;
	}

	public void setOperator(Operator<Boolean> operator) {
		this.operator = operator;
	}
}
