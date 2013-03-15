package com.validator.common.util;

import java.io.File;

public class FileUtil {

	// Private constructor to discourage stop misuse
	private FileUtil() {
	}

	/**
	 * Rename the file. files.
	 * 
	 * @param File
	 *            , file
	 * @throws IllegalAccessException
	 */
	public static void renameFile(File file, String newFileName) throws IllegalAccessException {
		if (null == file) {
			throw new IllegalArgumentException("File passed cannot be null.");
		}
		if (file.canWrite()) {
			file.renameTo(new File(StringUtil.concatenateStrings(newFileName)));
		} else {
			throw new IllegalAccessException("Isufficient Rights for marking a file processed.");
		}
	}

	/**
	 * Checks if the file is a file which has to be processed.
	 * 
	 * @param File
	 *            , file
	 * @return true- if the file is a file which has to be processed.
	 */
	public static boolean isValidFile(File file) {
		boolean isValidFile = false;
		if ((null != file) && file.isFile()) {
			isValidFile = true;
		}
		return isValidFile;
	}

	/**
	 * Returns the file object form its file path and filename.
	 * 
	 * @param String
	 *            , filePath
	 * @param String
	 *            , fileName
	 * @return File, file object
	 */
	public static File getFile(String filePath, String fileName) {
		File file = null;
		if ((null != fileName) && (null != fileName) && (!fileName.isEmpty()) && (!fileName.isEmpty())) {
			file = new File(new StringBuilder(filePath).append(CommonUtil.getFileSeparator()).append(fileName)
					.toString());
		}
		return file;
	}

	/**
	 * Returns the file extension associated with the file name.
	 * 
	 * @param String
	 *            , fileName
	 * @return String, the file extension
	 */
	public static String getFileExtension(String fileName) {
		if (null == fileName) {
			throw new IllegalArgumentException();
		}
		return CommonUtil.getFileExtension(fileName);
	}
}
