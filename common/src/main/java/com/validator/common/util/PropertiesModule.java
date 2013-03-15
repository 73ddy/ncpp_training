package com.validator.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.validator.common.constants.Constants;

/**
 * The class handles the ValidatorProperties provided in a given file.
 * 
 * @author gaurav1935
 */
public class PropertiesModule implements Module {

	private static Logger LOG = Logger.getLogger(PropertiesModule.class);
	// The object for holding properties
	private static Properties properties = new Properties();

	private void loadProperties() {
		InputStream is = new Object() {
		}.getClass().getEnclosingClass().getResourceAsStream(Constants.PROPERTIES_FILE.toString());
		try {
			properties.load(is);
			LOG.info("Validator Properties are successfully loaded.");
		} catch (IOException e) {
			LOG.error("Failed to load Validator Property.", e);
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException dontCare) {
				}
			}
		}
	}

	/**
	 * This method returns property value against the provided key.
	 * 
	 * @param {@link String} key
	 * @return the value corresponding to a given property name.
	 * @throws PropertyNotFoundException
	 *             - if the property is not found in the property file.
	 */
	/*
	 * public static String getProperty(String key) throws
	 * PropertyNotFoundException { String value = null; if (null !=
	 * properties.getProperty(key)) { value =
	 * properties.getProperty(key).trim(); } else { String errorMsg = new
	 * StringBuilder
	 * (key).append(" property not found in property file.").toString();
	 * LOG.warn(errorMsg); throw new PropertyNotFoundException(key, errorMsg); }
	 * return value; }
	 */

	/**
	 * Returns the integral value of validator property. However, if the value
	 * is not integral, then returns the default value passed.
	 * 
	 * @param prop
	 * @param defaultValue
	 * @return Integral value of validator property. Returns the default value
	 *         if the integral value of property is not present.
	 */
	/*
	 * public static int getIntProperty(String prop, int defaultValue) { String
	 * value = getProperty(prop); int returnValue = 0; if (value != null) { try
	 * { returnValue = Integer.parseInt(value); } catch (NumberFormatException
	 * e) { LOG.error("getIntProperty: Property value not an int. prop=" + prop
	 * + ",value=" + value, e); returnValue = defaultValue; } } return
	 * returnValue; }
	 */

	@Override
	public void configure(Binder binder) {
		loadProperties();
		Names.bindProperties(binder, properties);
	}
}
