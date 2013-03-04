package com.validator.common.util;

import java.util.Arrays;

import com.validator.common.constants.Constants;
import com.validator.common.constants.PropertyKeys;

/**
 * CommonUtil is a class for common utilities methods related to the project
 * 
 * @author gaurav1935
 */
public class CommonUtil {

	/**
	 * Returns the file separator character of Operating System.
	 * 
	 * @return the file separator character of the underlying Operating System
	 */
	public static String getFileSeparator() {
		return System.getProperty(Constants.FILE_SEPARATOR.toString());
	}

	/**
	 * Returns the file extension of a given filename.
	 * 
	 * @param fileName
	 * @return returns file extension
	 */
	public static String getFileExtension(String fileName) {
		String extension = null;
		if (fileName != null && fileName.indexOf(".") >= 0) {
			extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		}
		return extension;
	}

	/**
	 * Returns the file extensions
	 * 
	 * @param fileExtension
	 * @return true if a file with given file extension is a recognized file
	 *         extension(is a file that needs to be processed)
	 */
	public static boolean isRecognizedFileExtension(String fileExtension) {
		String[] recognizedExtensions;
		String extensions = ValidatorProperties.getInstance().getProperty(
				PropertyKeys.RECOGNIZED_FILE_EXTENSIONS.toString());
		recognizedExtensions = extensions.split(Constants.PROPERTIES_SEPARATOR.toString());
		return Arrays.asList(recognizedExtensions).contains(fileExtension);
	}
}
