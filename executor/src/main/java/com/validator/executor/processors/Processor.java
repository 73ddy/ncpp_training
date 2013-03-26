package com.validator.executor.processors;

import java.io.File;

import com.validator.monitor.entities.ValidableEntitiesList;
import com.validator.monitor.entities.ValidableEntity;

/**
 * Processor represents the processors which will process the entities.
 * 
 * @author gaurav1935
 * 
 * @param <T>
 *            A validable entity
 */
/**
 * @author gaurav1935
 * 
 */
public abstract class Processor implements Runnable {

	// The entities to be processed
	protected ValidableEntitiesList entityList;
	// Entity file
	protected File file;

	/**
	 * The business logic, how the entity is to be processed.
	 */
	public abstract void processEntities();

	/**
	 * The business logic for marking an entity file invalid.
	 * 
	 * @param validableEntity
	 */
	public abstract void markEntityInvalid(ValidableEntity validableEntity);

	/**
	 * Returns the entity to be processed.
	 * 
	 * @return {@link ValidableEntities}
	 */
	public ValidableEntitiesList getEntityList() {
		return entityList;
	}
}
