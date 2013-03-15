package com.validator.monitor.notifiers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import net.contentobjects.jnotify.JNotifyListener;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.validator.common.constants.Errors;
import com.validator.common.exceptions.PropertyNotFoundException;
import com.validator.common.exceptions.XmlTransformationException;
import com.validator.common.util.CommonUtil;
import com.validator.common.util.FileUtil;
import com.validator.common.util.StringUtil;
import com.validator.common.xml.XmlManager;
import com.validator.monitor.entities.Employee;

@Singleton
public class Listener implements JNotifyListener {
	private static Logger LOG = Logger.getLogger(Listener.class);

	@Inject
	XmlManager xmlManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.contentobjects.jnotify.JNotifyListener#fileCreated(int,
	 * java.lang.String, java.lang.String)
	 */
	public void fileCreated(int noOfFiles, String filePath, String fileName) {
		System.out.println(noOfFiles);
		System.out.println(filePath);
		System.out.println(fileName);

		File newFile = FileUtil.getFile(filePath, fileName);
		InputStream inputStream = null;

		if (FileUtil.isValidFile(newFile)) {
			LOG.info(StringUtil.concatenateStrings("A new file named ", fileName, " has been created at path - ",
					filePath, "."));
			try {
				if (CommonUtil.isRecognizedFileExtension(FileUtil.getFileExtension(fileName))) {
					inputStream = processFile(newFile);
				}
			} catch (PropertyNotFoundException pNFE) {
				LOG.error(Errors.RECOGNIZED_EXTENSIONS_NOT_DEFINED, pNFE);
			} catch (FileNotFoundException e) {
				LOG.error(Errors.FILE_NOT_FOUND, e);
			} catch (XmlTransformationException xmlTE) {
				LOG.error(Errors.FAILED_TO_UNMARSHAL_ENTITY, xmlTE);
			} catch (IllegalAccessException iAE) {
				LOG.error(iAE.getMessage(), iAE);
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					// eating up the exception.
				}
			}
		}

	}

	private InputStream processFile(File newFile) throws FileNotFoundException, IllegalAccessException {
		InputStream inputStream;
		inputStream = new FileInputStream(newFile);
		Employee employee = (Employee) xmlManager.unmarshal(new BufferedInputStream(inputStream));
		System.out.println("New employee has been found.");
		FileUtil.markFileProcessed(newFile);
		return inputStream;
	}

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
