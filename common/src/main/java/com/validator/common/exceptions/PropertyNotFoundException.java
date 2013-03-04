package com.validator.common.exceptions;

/**
 * PropertyNotFoundException occurs whenever a property is not found the
 * properties file.
 * 
 * @author gaurav1935
 */
public class PropertyNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -584397368607859149L;
	String propertyName;

	public PropertyNotFoundException(String propertyName, String msg) {
		super(msg);
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
}
