package com.validator.executor.execution;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.google.inject.Injector;
import com.validator.common.constants.PropertyKeys;
import com.validator.common.exceptions.PropertyNotFoundException;
import com.validator.common.util.ValidatorProperties;
import com.validator.common.validators.entityvalidators.Validator;
import com.validator.common.xml.XmlManager;
import com.validator.monitor.bindings.GuiceInjector;
import com.validator.monitor.notifiers.FileOperationMask;
import com.validator.monitor.watchers.Watchers;

/**
 * The executor class containing the main method.
 * 
 * @author gaurav1935
 */
public class Executor {

	private static Logger LOG = Logger.getLogger(Executor.class);

	private static XmlManager xmlManager;

	private static Injector injector;

	@SuppressWarnings("rawtypes")
	private static Validator validator;

	private static FileOperationMask fileOperationMask;
	private static Watchers watchers;
	
	private static ValidatorProperties validatorProperties = ValidatorProperties.getInstance();
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws PropertyNotFoundException, IOException, InterruptedException {
		String validatorFilePath = null;
		String[] foldersToBeWatched = null;
		boolean watchSubtree = false;
		
		// Initialize the guice injector and inject beans
		injector = GuiceInjector.getInjector();
		LOG.info("Guice Injector was successfully set.");
		xmlManager = injector.getInstance(XmlManager.class);

		// Unmarshal validation rules from xml
		validatorFilePath = validatorProperties.getProperty(
				PropertyKeys.VALIDATOR_FILE.toString());
		InputStream inputStream = new FileInputStream(validatorFilePath);
		validator = (Validator) xmlManager.unmarshal(new BufferedInputStream(inputStream));
		LOG.info("Validator successfully extracted from validator file.");
		System.out.println(validator);
		
		// Add watcher
		foldersToBeWatched = validatorProperties.getProperties(PropertyKeys.FOLDERS_TO_BE_WATCHED.toString());
		watchSubtree = validatorProperties.getBooleanProperty(PropertyKeys.WATCH_SUBTREE.toString(), false);
		fileOperationMask = injector.getInstance(FileOperationMask.class);
		watchers = injector.getInstance(Watchers.class);
		for (String folderToBeWatched: foldersToBeWatched) {
		    watchers.addWatcher(folderToBeWatched.trim(), fileOperationMask.getFileCreatedMask(),watchSubtree);
		}
		System.out.println(watchers.getRegisteredWatchers().size());
		
		while (true) {
			Thread.sleep(100000);
		}
		
	}
}
