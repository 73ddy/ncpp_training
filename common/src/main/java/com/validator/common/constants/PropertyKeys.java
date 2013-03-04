package com.validator.common.constants;

/**
 * All the property keys defined in the properties file.
 * 
 * @author gaurav1935
 */
public enum PropertyKeys {
	RECOGNIZED_FILE_EXTENSIONS("recognized.file.extensions"),
	DEFAULT_OBJECT_POOL_SIZE("default.object.pool.size");

	String name;

	PropertyKeys(String name) {
		this.name = name;
	}

	public String getKeyName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}