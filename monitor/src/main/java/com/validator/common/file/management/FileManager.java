package com.validator.common.file.management;

import java.io.File;

/**
 * The File Managers interface.
 * 
 * @author gaurav1935
 */
public interface FileManager {
	/**
	 * Marks a file as PROCESSED, so as to identify the processed and the raw
	 * files.
	 * 
	 * @param file
	 * @throws IllegalAccessException
	 */
	public void markFileProcessed(File file) throws IllegalAccessException;

	/**
	 * Checks if the file is a file which has to be processed.
	 * 
	 * @param file
	 * @return true- if the file is a file which has to be processed.
	 */
	public boolean isValidFile(File file);

	/**
	 * Returns the file object form its file path and filename.
	 * 
	 * @param filePath
	 * @param fileName
	 * @return a file object
	 */
	public File getFile(String filePath, String fileName);

	/**
	 * Returns the file extension associated with the file name.
	 * 
	 * @param fileName
	 * @return returns the file extension
	 */
	public String getFileExtension(String fileName);
}
