package com.validator.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.validator.common.constants.Constants;
import com.validator.common.exceptions.PropertyNotFoundException;

/**
 * The class handles the ValidatorProperties provided in a given file.
 * 
 * @author gaurav1935
 */
public class ValidatorProperties {

	private static Logger LOG = Logger.getLogger(ValidatorProperties.class);
	private static ValidatorProperties instance = new ValidatorProperties();
	// The object for holding properties
	private static Properties properties;

	/**
	 * Constructor, also responsible for loading the properties file.
	 */
	private ValidatorProperties() {
		loadPoperties();
	}

	private void loadPoperties() {
		InputStream resourceFile = null;
		properties = new Properties();
		try {
			resourceFile = getClass().getResourceAsStream(Constants.PROPERTIES_FILE.toString());
			properties.load(resourceFile);
			LOG.info("Validator Properties are successfully loaded.");
		} catch (IOException e) {
			LOG.error("Failed to load Validator Property.", e);
		}
	}

	/**
	 * Returns the ValidatorPropertie instance
	 * 
	 * @return ValidatorPropertie instance
	 */
	public static ValidatorProperties getInstance() {
		return instance;
	}

	/**
	 * This method returns property value against the provided key.
	 * 
	 * @param {@link String} key
	 * @return String, the value corresponding to a given property name.
	 * @throws PropertyNotFoundException
	 *             - if the property is not found in the property file.
	 */
	public String getProperty(String key) throws PropertyNotFoundException {
		String value = null;
		if (null != properties.getProperty(key)) {
			value = properties.getProperty(key).trim();
		} else {
			String errorMsg = new StringBuilder(key).append(" property not found in property file.").toString();
			LOG.warn(errorMsg);
			throw new PropertyNotFoundException(key, errorMsg);
		}
		return value;
	}

	/**
	 * This method returns property values against the provided key. Use only if
	 * the value may have multiple values separated by the property separator,
	 * <code>Constants.PROPERTIES_SEPARATOR</code>.
	 * 
	 * @param {@link String} key
	 * @return String, values corresponding to a given property name.
	 * @throws PropertyNotFoundException
	 *             - if the property is not found in the property file.
	 */
	public String[] getProperties(String key) throws PropertyNotFoundException {
		String[] value = null;
		if (null != properties.getProperty(key)) {
			value = properties.getProperty(key).trim().split(Constants.PROPERTIES_SEPARATOR.toString());
		} else {
			String errorMsg = new StringBuilder(key).append(" property not found in property file.").toString();
			LOG.warn(errorMsg);
			throw new PropertyNotFoundException(key, errorMsg);
		}
		return value;
	}

	/**
	 * Returns the integral value of validator property. However, if the value
	 * is not integral, then returns the default value passed.
	 * 
	 * @param prop
	 * @param defaultValue
	 * @return Integral value of validator property. Returns the default value
	 *         if the integral value of property is not present.
	 */
	public int getIntProperty(String prop, int defaultValue) {
		String value = getProperty(prop);
		int returnValue = defaultValue;
		if (value != null) {
			try {
				returnValue = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				LOG.error("getIntProperty: Property value not an int. prop=" + prop + ",value=" + value, e);
			}
		}
		return returnValue;
	}

	/**
	 * Returns a boolean value of validator property. However, if the value is
	 * not boolean, then returns the default value passed.
	 * 
	 * @param String
	 *            prop
	 * @param boolean defaultValue
	 * @return boolean
	 */
	public boolean getBooleanProperty(String prop, boolean defaultValue) {
		String value = getProperty(prop);
		boolean returnValue = defaultValue;
		if (value != null) {
			try {
				returnValue = Boolean.getBoolean(value);
			} catch (NumberFormatException e) {
				LOG.error("getBooleanProperty: Property value not a boolean. prop=" + prop + ",value=" + value, e);
			}
		}
		return returnValue;

	}

}
