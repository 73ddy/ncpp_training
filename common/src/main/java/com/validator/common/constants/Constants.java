package com.validator.common.constants;

/**
 * Common constants used in the project.
 * 
 * @author gaurav1935
 */
public enum Constants {
	PROPERTIES_SEPARATOR(","), FILE_SEPARATOR("file.separator"), PROPERTIES_FILE(
			"/com/validator/resources/validator.properties");

	String value;

	Constants(String name) {
		this.value = name;
	}

	public String getKeyName() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
