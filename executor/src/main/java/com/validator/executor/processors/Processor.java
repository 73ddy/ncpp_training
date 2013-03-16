package com.validator.executor.processors;

import java.io.File;

import com.validator.monitor.entities.ValidableEntities;

/**
 * Processor represents the processors which will process the entities.
 * 
 * @author gaurav1935
 * 
 * @param <T>
 *            A validable entity
 */
public abstract class Processor implements Runnable {

	// The entity to be processed
	protected ValidableEntities entity;
	// Entity file
	protected File file;

	/**
	 * The business logic, how the entity is to be processed.
	 */
	public abstract void processEntity();
}
