package com.validator.executor.processors.impl;

import org.apache.log4j.Logger;

import com.validator.common.util.FileUtil;
import com.validator.common.util.StringUtil;
import com.validator.executor.processors.Processor;
import com.validator.monitor.entities.Employee;

/**
 * Dummy Entity Processor
 * 
 * @author gaurav1935
 */
public class EmployeeProcessor<T> extends Processor<Employee> {

	private static final Logger LOG = Logger.getLogger(EmployeeProcessor.class);

	@Override
	public void processEntity() {
	}

	@Override
	public void setEntity(Employee entity) {
		this.entity = entity;
	}

	@Override
	public Employee getEntity() {
		return entity;
	}

	@Override
	public void postEntityFileProcessing() {
		try {
			FileUtil.renameFile(file, StringUtil.concatenateStrings(file.getAbsolutePath(), ".processed"));
		} catch (IllegalAccessException e) {
			LOG.error(StringUtil.concatenateStrings("Insufficient rights on file ", file.getName(), ". Employee (name = ",
					entity.getName(), ", id = ", entity.getId().toString(), ")"), e);
		}
	}

}
