package com.validator.executor.processors.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import com.validator.common.util.FileUtil;
import com.validator.common.util.StringUtil;
import com.validator.executor.processors.Processor;
import com.validator.monitor.entities.Employee;

/**
 * Dummy Entity Processor for Employee entity.
 * 
 * @author gaurav1935
 */
public class EmployeeProcessor<T> extends Processor<Employee> {

	private static final Logger LOG = Logger.getLogger(EmployeeProcessor.class);

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
		LOG.info(StringUtil.concatenateStrings("About to process the entity - ", this.getEntity().toString()));
		// valdiate
		postEntityFileProcessing();
		LOG.info(StringUtil.concatenateStrings("Successfully processed the entity - ", this.getEntity().toString()));
	}

	@Override
	public void setEntity(Employee entity) {
		this.entity = entity;
	}

	@Override
	public Employee getEntity() {
		return entity;
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
			LOG.error(
					StringUtil.concatenateStrings("Failed post processing - Insufficient rights on file ", file
							.getName(), ". Employee (name = ", entity.getName(), ", id = ", entity.getId().toString(),
							")"), e);
		}
	}

	public void run() {
		processEntity();
	}
}
