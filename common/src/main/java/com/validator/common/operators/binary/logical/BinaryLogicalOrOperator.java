package com.validator.common.operators.binary.logical;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Binary Logical Or Operator Object. It is a logical operator and thus
 * implements BinaryLogicalOperator.
 * 
 * @author gaurav1935
 * 
 * @param <T>
 *            The entity to be validated.
 */
@XmlRootElement
public class BinaryLogicalOrOperator<Boolean> implements BinaryLogicalOperator<Boolean> {

	/* (non-Javadoc)
	 * @see com.validator.common.operators.Operator#operate(java.lang.Object, java.lang.Object)
	 */
	public boolean operate(Boolean boolean1, Boolean boolean2) {
		boolean b1 = (java.lang.Boolean) boolean1;
		boolean b2 = (java.lang.Boolean) boolean2;
		return b1 || b2;
	}

}
