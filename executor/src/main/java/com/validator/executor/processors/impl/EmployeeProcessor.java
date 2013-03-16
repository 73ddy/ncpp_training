package com.validator.executor.processors.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.validator.common.util.FileUtil;
import com.validator.common.util.StringUtil;
import com.validator.common.xml.XmlManager;
import com.validator.executor.processors.Processor;
import com.validator.monitor.entities.Employee;

/**
 * Dummy Entity Processor for Employee entity.
 * 
 * @author gaurav1935
 */
public class EmployeeProcessor extends Processor {

	private static final Logger LOG = Logger.getLogger(EmployeeProcessor.class);

	@Inject
	private XmlManager xmlManager;

	// Unused? Will never be used.
	@SuppressWarnings("unused")
	private EmployeeProcessor() {

	}

	public EmployeeProcessor(File file) {
		this.file = file;
		try {
			this.entity = (Employee) xmlManager.unmarshal(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			LOG.error("The file provided for generating an object of employee doesn't exist.", e);
		}
	}

	@Override
	public void processEntity() {
		LOG.info(StringUtil.concatenateStrings("About to process the entity - ", this.entity.toString()));
		// valdiate
		postEntityFileProcessing();
		LOG.info(StringUtil.concatenateStrings("Successfully processed the entity - ", this.entity.toString()));
	}

	/**
	 * Post processing of the entity file.
	 * 
	 * @param T
	 *            entity
	 */
	private void postEntityFileProcessing() {
		try {
			FileUtil.renameFile(file, StringUtil.concatenateStrings(file.getAbsolutePath(), ".processed"));
		} catch (IllegalAccessException e) {
			LOG.error(StringUtil.concatenateStrings("Failed post processing - Insufficient rights on file ",
					file.getName(), ". Employee (name = ", ((Employee) entity).getName(), ", id = ",
					((Employee) entity).getId().toString(), ")"), e);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		processEntity();
	}
}
