package com.validator.monitor.entities;

/**
 * All the entities which can be validated using the validator rules, must
 * extend ValidableEntities.
 * 
 * @author gaurav1935
 */
abstract public class ValidableEntity {

	/**
	 * Returns the name of the file which is to be saved once the file has been
	 * processed. This does not include the file name.
	 * 
	 * @return
	 */
	public abstract String getEntityFileName();
}
