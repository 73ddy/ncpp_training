package com.validator.common.file.management;

import java.io.File;

import com.validator.common.util.CommonUtil;
import com.validator.common.util.StringUtil;

/**
 * File Manager implementation of the FileManager
 * 
 * @author gaurav1935
 */
public class FileManagerBean implements FileManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.validator.common.file.management.FileManager#markFileProcessed(java
	 * .io.File)
	 */
	public void markFileProcessed(File file) throws IllegalAccessException {
		// TODO: is file renamed
		boolean isFileRenamed = false;
		if (file.canWrite()) {
			isFileRenamed = file.renameTo(new File(StringUtil.concatenateStrings(file.getAbsolutePath(), ".processed")));
		} else {
			throw new IllegalAccessException("Isufficient Rights for marking a file processed.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.validator.common.file.management.FileManager#isValidFile(java.io.
	 * File)
	 */
	public boolean isValidFile(File file) {
		boolean isValidFile = false;
		if (file.isFile()) {
			isValidFile = true;
		}
		return isValidFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.validator.common.file.management.FileManager#getFile(java.lang.String
	 * , java.lang.String)
	 */
	public File getFile(String filePath, String fileName) {
		return new File(new StringBuilder(filePath).append(CommonUtil.getFileSeparator()).append(fileName).toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.validator.common.file.management.FileManager#getFileExtension(java
	 * .lang.String)
	 */
	public String getFileExtension(String fileName) {
		return CommonUtil.getFileExtension(fileName);
	}
}
