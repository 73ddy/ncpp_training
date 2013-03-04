package com.validator.common.operators;

/**
 * Generic interface for the operators used in validating an entity.
 * 
 * @author gaurav1935
 * 
 * @param <T>
 *            The entity to be validated.
 */
public interface Operator<T> {
	/**
	 * The logic of the operation to be performed goes here.
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 */
	boolean operate(T t1, T t2);
}
