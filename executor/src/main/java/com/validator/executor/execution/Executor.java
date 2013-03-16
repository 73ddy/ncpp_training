package com.validator.executor.execution;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.google.inject.Injector;
import com.validator.common.constants.PropertyKeys;
import com.validator.common.exceptions.PropertyNotFoundException;
import com.validator.common.util.ValidatorProperties;
import com.validator.common.validators.entityvalidators.Validator;
import com.validator.executor.binding.GuiceInjector;
import com.validator.executor.threadexecutor.RawEntityConsumer;
import com.validator.monitor.notifiers.FileOperationMask;
import com.validator.monitor.watchers.Watchers;
import com.validator.monitor.xml.XmlManager;

/**
 * The executor class containing the main method.
 * 
 * @author gaurav1935
 */
public class Executor {

	private static Logger LOG = Logger.getLogger(Executor.class);
	private static XmlManager xmlManager;
	private static Injector injector = GuiceInjector.getInjector();
	@SuppressWarnings("rawtypes")
	private static Validator validator;
	private static FileOperationMask fileOperationMask;
	private static Watchers watchers;
	private static ValidatorProperties validatorProperties = ValidatorProperties.getInstance();

	public static void main(String[] args) throws PropertyNotFoundException, IOException, InterruptedException {
		// Initialize the beans to be used
		injectBeans();

		// Unmarshal validation rules from xml
		initializeValidator();

		// Add watcher and start listening for changes in the target folders
		loadWatchersFromConfigurationFile();

		// Run the raw entity consumer
		runEntityProcessorThread();

		while (true) {
			Thread.sleep(100000);
		}
	}

	/**
	 * Runs the Entity Processor Logic.
	 */
	private static void runEntityProcessorThread() {
		Thread rawEntityConsumerThread = new Thread(new RawEntityConsumer());
		rawEntityConsumerThread.start();
	}

	/**
	 * Fetches watchers information from the configuration file and initializes
	 * the watchers
	 * 
	 * @throws IOException
	 */
	private static void loadWatchersFromConfigurationFile() throws IOException {
		String[] foldersToBeWatched;
		boolean watchSubtree;
		foldersToBeWatched = validatorProperties.getProperties(PropertyKeys.FOLDERS_TO_BE_WATCHED.toString());
		watchSubtree = validatorProperties.getBooleanProperty(PropertyKeys.WATCH_SUBTREE.toString(), false);
		watchers = injector.getInstance(Watchers.class);
		for (String folderToBeWatched : foldersToBeWatched) {
			watchers.addWatcher(folderToBeWatched.trim(), fileOperationMask.getFileCreatedMask(), watchSubtree);
		}
		System.out.println("Total watchers - " + Watchers.getRegisteredWatchers().size());
	}

	/**
	 * Initializes the validator object, which will be used for validating the
	 * entities.
	 * 
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	private static void initializeValidator() throws FileNotFoundException {
		String validatorFilePath = validatorProperties.getProperty(PropertyKeys.VALIDATOR_FILE.toString());
		validator = (Validator) xmlManager.unmarshal(new BufferedInputStream(new FileInputStream(validatorFilePath)));
		LOG.info("Validator successfully extracted from validator file.");
		System.out.println(validator);
	}

	/**
	 * Inject the required Google Guice Beans
	 */
	private static void injectBeans() {
		xmlManager = injector.getInstance(XmlManager.class);
		fileOperationMask = injector.getInstance(FileOperationMask.class);
	}
}
