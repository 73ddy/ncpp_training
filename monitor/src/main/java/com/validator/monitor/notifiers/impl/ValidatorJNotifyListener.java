package com.validator.monitor.notifiers.impl;

import java.io.File;

import net.contentobjects.jnotify.JNotifyListener;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.validator.common.util.CommonUtil;
import com.validator.common.util.FileUtil;
import com.validator.common.util.StringUtil;
import com.validator.monitor.rawentitystore.RawEntityStore;

@Singleton
public class ValidatorJNotifyListener implements JNotifyListener {
	
	private static Logger LOG = Logger.getLogger(ValidatorJNotifyListener.class);
	
	@Inject
	private RawEntityStore rawEntityStore;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.contentobjects.jnotify.JNotifyListener#fileCreated(int,
	 * java.lang.String, java.lang.String)
	 */
	public void fileCreated(int noOfFiles, String filePath, String fileName) {
		// TODO: to be removed sysouts
		System.out.println(noOfFiles);
		System.out.println(filePath);
		System.out.println(fileName);

		File newFile = FileUtil.getFile(filePath, fileName);
		String fileExtension = null;
		
		if (FileUtil.isValidFile(newFile)) {
			LOG.info(StringUtil.concatenateStrings("A new file named ", fileName, " has been created at path - ",
					filePath, "."));
			fileExtension = FileUtil.getFileExtension(fileName);
			try {
				if (CommonUtil.isRecognizedFileExtension(fileExtension)) {
					LOG.info(StringUtil.concatenateStrings("File: ", newFile.toString(), ", will be processed."));
					rawEntityStore.putRawFile(newFile);
				}
			} catch (InterruptedException iE) {
				LOG.error("Failed to put the file in the raw entity store. Marking the file unprocessed.", iE);
				try {
					FileUtil.renameFile(newFile, StringUtil.concatenateStrings(newFile.getName(), ".unprocessed"));
				} catch (IllegalAccessException e) {
					LOG.error("Failed to mark the file unprocessed.",e);
				}
			}
		}

	}

	/*private InputStream processFile(File newFile) throws FileNotFoundException, IllegalAccessException {
		InputStream inputStream = new FileInputStream(newFile);
		Employee employee = (Employee) xmlManager.unmarshal(new BufferedInputStream(inputStream));
		System.out.println("New employee has been found.");
		return inputStream;
	}*/

	public void fileDeleted(int arg0, String arg1, String arg2) {
		// IGNORE
	}

	public void fileModified(int arg0, String arg1, String arg2) {
		// IGNORE
	}

	public void fileRenamed(int arg0, String arg1, String arg2, String arg3) {
		// IGNORE
	}
}
