package com.validator.common.util;

/**
 * StringUtil is a class for string utility methods.
 * 
 * @author gaurav1935
 */
public class StringUtil {

	/**
	 * Returns a concatenated string consisting of the strings passed. Returns
	 * null, if null is passed.
	 * 
	 * @param strValues
	 *            - the strings which have to be concatenated.
	 * 
	 * @return Concatenated string. Returns null if input is null.
	 */
	public static String concatenateStrings(String... strValues) {
		String retValue = null;
		if (null != strValues) {
			StringBuilder sbValue = new StringBuilder();
			for (String strValue : strValues) {
				sbValue.append(strValue);
			}
			retValue = sbValue.toString();
		}
		return retValue;
	}

}
