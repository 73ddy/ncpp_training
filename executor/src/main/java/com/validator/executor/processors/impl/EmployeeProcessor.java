package com.validator.executor.processors.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import com.validator.common.constants.PropertyKeys;
import com.validator.common.exceptions.XmlTransformationException;
import com.validator.common.util.FileUtil;
import com.validator.common.util.StringUtil;
import com.validator.common.util.ValidatorProperties;
import com.validator.executor.binding.GuiceInjector;
import com.validator.executor.processors.Processor;
import com.validator.executor.validator.ValidatorStore;
import com.validator.monitor.entities.Employee;
import com.validator.monitor.xml.XmlManager;

/**
 * Dummy Entity Processor for Employee entity.
 * 
 * @author gaurav1935
 */
public class EmployeeProcessor extends Processor {

	private static final Logger LOG = Logger.getLogger(EmployeeProcessor.class);

	private XmlManager xmlManager;
	private ValidatorProperties validatorProperties;
	private ValidatorStore validatorStore;
	
	// Unused? Will never be used.
	@SuppressWarnings("unused")
	private EmployeeProcessor() {
	}

	public EmployeeProcessor(File file) {
		xmlManager = GuiceInjector.getInjector().getInstance(XmlManager.class);
		validatorStore = GuiceInjector.getInjector().getInstance(ValidatorStore.class);
		validatorProperties = ValidatorProperties.getInstance();
		
		this.file = file;
		try {
			this.entity = (Employee) xmlManager.unmarshal(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			LOG.error("The file provided for generating an object of employee doesn't exist.", e);
		} catch (XmlTransformationException xmlTE) {
			LOG.error(StringUtil.concatenateStrings("Failed to unmarshal given entity file - ", file.getName()), xmlTE);
			markEntityInvalid();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void processEntity() {
		LOG.info(StringUtil.concatenateStrings("About to process the entity - ", this.entity.toString()));
		if (validatorStore.getValidator().validate(this.entity)) {
			postEntityFileProcessing();
			LOG.info(StringUtil.concatenateStrings("Successfully processed the entity - ", this.entity.toString()));
		} else {
			markEntityInvalid();
		}
	}

	/**
	 * Post processing of the entity file.
	 * 
	 * @param T
	 *            entity
	 */
	private void postEntityFileProcessing() {
		try {
			FileUtil.renameFile(
					file,
					StringUtil.concatenateStrings(file.getAbsolutePath(),
							validatorProperties.getProperty(PropertyKeys.PROCESSED_FILE_NAME_SUFFIX.toString())));
		} catch (IllegalAccessException e) {
			LOG.error(StringUtil.concatenateStrings("Failed post processing - Insufficient rights on file ",
					file.getName(), ". Employee (name = ", ((Employee) entity).getName(), ", id = ",
					((Employee) entity).getId().toString(), ")"), e);
		}
	}

	@Override
	public void markEntityInvalid() {
		try {
			FileUtil.renameFile(
					file,
					StringUtil.concatenateStrings(file.getAbsolutePath(),
							validatorProperties.getProperty(PropertyKeys.INVALID_FILE_NAME_SUFFIX.toString())));
			LOG.info(StringUtil.concatenateStrings("The entity file - ", this.file.getName(), " was marked invalid."));
		} catch (IllegalAccessException e) {
			LOG.error(StringUtil.concatenateStrings(
					"Failed to mark entity file invalid - Insufficient rights on file ", file.getName()), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		processEntity();
	}
}
