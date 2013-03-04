package com.validator.common.operators.unary.logical;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Unary Not Operator Object. It is a unary operator and thus
 * implements UnaryLogicalOperator.
 * 
 * @author gaurav1935
 * 
 * @param <T>
 *            The entity to be validated.
 */
@XmlRootElement
public class UnaryLogicalNotOperator<Boolean> implements UnaryLogicalOperator<Boolean> {

	/* (non-Javadoc)
	 * @see com.validator.common.operators.Operator#operate(java.lang.Object, java.lang.Object)
	 */
	public boolean operate(Boolean boolean1, Boolean boolean2) {
		boolean b1 = (java.lang.Boolean) boolean1;
		return !b1;
	}

}
