package com.validator.common.operators.binary.arithmetic;

import javax.xml.bind.annotation.XmlRootElement;

import com.validator.common.validators.generalvalidators.ValidationHelper;

/**
 * Binary Greater Than Operator Object. It is an arithmetic operator and thus
 * implements BinaryArithmeticOperator.
 * 
 * @author gaurav1935
 * 
 * @param <T>
 *            The entity to be validated.
 */
@XmlRootElement
public class BinaryGreaterThanOperator<T> implements BinaryArithmeticOperator<T> {

	/* (non-Javadoc)
	 * @see com.validator.common.operators.Operator#operate(java.lang.Object, java.lang.Object)
	 */
	public boolean operate(T t1, T t2) {
		boolean isValid = true;
		try {
			ValidationHelper.validateValue(t1).against(t2)
					.usingValidator(ValidationHelper.isGreaterThan());
		} catch (IllegalStateException iSE) {
			isValid = false;
		}
		return isValid;
	}

}
