package com.validator.common.util;

import java.io.File;

public class FileUtil {

	// Private constructor to discourage stop misuse
	private FileUtil() {
	}

	/**
	 * Marks a file as PROCESSED, so as to identify the processed and the raw
	 * files.
	 * 
	 * @param file
	 * @throws IllegalAccessException
	 */
	public static void markFileProcessed(File file) throws IllegalAccessException {
		// TODO: is file renamed
		if (file.canWrite()) {
			file.renameTo(new File(StringUtil.concatenateStrings(file.getAbsolutePath(), ".processed")));
		} else {
			throw new IllegalAccessException("Isufficient Rights for marking a file processed.");
		}
	}

	/**
	 * Checks if the file is a file which has to be processed.
	 * 
	 * @param file
	 * @return true- if the file is a file which has to be processed.
	 */
	public static boolean isValidFile(File file) {
		boolean isValidFile = false;
		if (file.isFile()) {
			isValidFile = true;
		}
		return isValidFile;
	}

	/**
	 * Returns the file object form its file path and filename.
	 * 
	 * @param filePath
	 * @param fileName
	 * @return a file object
	 */
	public static File getFile(String filePath, String fileName) {
		return new File(new StringBuilder(filePath).append(CommonUtil.getFileSeparator()).append(fileName).toString());
	}

	/**
	 * Returns the file extension associated with the file name.
	 * 
	 * @param fileName
	 * @return returns the file extension
	 */
	public static String getFileExtension(String fileName) {
		return CommonUtil.getFileExtension(fileName);
	}
}
