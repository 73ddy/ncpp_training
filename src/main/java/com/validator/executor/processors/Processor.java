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
public abstract class Processor<T extends ValidableEntities> {

	// The entity to be processed
	protected T entity;
	// Entity file
	protected File file;

	/**
	 * The business logic, how the entity is to be processed.
	 */
	public abstract void processEntity();

	/**
	 * Set the entity to be processed.
	 * 
	 * @param T
	 *            entity
	 */
	public abstract void setEntity(T entity);

	/**
	 * Post processing of the entity file.
	 * 
	 * @param T
	 *            entity
	 */
	public abstract void postEntityFileProcessing();

	/**
	 * Returns the entity to be processed.
	 * 
	 * @return T entity
	 */
	public abstract T getEntity();

	/**
	 * Returns the file to be processed.
	 * 
	 * @return File file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets the file to be marked processed.
	 * 
	 * @param File
	 *            file
	 */
	public void setFile(File file) {
		this.file = file;
	}

}
