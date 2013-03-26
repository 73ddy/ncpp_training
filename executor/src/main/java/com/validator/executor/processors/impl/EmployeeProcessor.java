package com.validator.executor.processors.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import com.validator.common.constants.PropertyKeys;
import com.validator.common.exceptions.XmlTransformationException;
import com.validator.common.util.CommonUtil;
import com.validator.common.util.FileUtil;
import com.validator.common.util.StringUtil;
import com.validator.common.util.ValidatorProperties;
import com.validator.executor.binding.GuiceInjector;
import com.validator.executor.processors.Processor;
import com.validator.executor.validator.ValidatorStore;
import com.validator.monitor.entities.Employee;
import com.validator.monitor.entities.ValidableEntity;
import com.validator.monitor.entities.ValidableEntitiesList;
import com.validator.monitor.xml.XmlManager;

/**
 * Dummy Entity Processor for Employee entity.
 * 
 * @author gaurav1935
 */
public class EmployeeProcessor extends Processor {

	private static final Logger LOG = Logger.getLogger(EmployeeProcessor.class);

	private XmlManager xmlManager;
	private ValidatorStore validatorStore;

	// Unused? Will never be used.
	@SuppressWarnings("unused")
	private EmployeeProcessor() {
	}

	public EmployeeProcessor(File file) {
		xmlManager = GuiceInjector.getInjector().getInstance(XmlManager.class);
		validatorStore = GuiceInjector.getInjector().getInstance(ValidatorStore.class);

		this.file = file;
		try {
			this.entityList = (ValidableEntitiesList) xmlManager.unmarshal(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			LOG.error("The file provided for generating an object of employee doesn't exist.", e);
		} catch (XmlTransformationException xmlTE) {
			LOG.error(StringUtil.concatenateStrings("Failed to unmarshal given entity file - ", file.getName()), xmlTE);
			markEntityInvalid();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void processEntities() {
		for (ValidableEntity employee : this.entityList.getEntityList()) {
			Employee employeeObject = (Employee) employee;
			LOG.info(StringUtil.concatenateStrings("About to process the entity - ", employeeObject.toString()));
			if (validatorStore.getValidator().validate(employeeObject)) {
				try {
					postEntityFileProcessing(employeeObject);
					LOG.info(StringUtil.concatenateStrings("Successfully processed the entity - ",
							employeeObject.toString()));
				} catch (IOException e) {
					LOG.error(StringUtil.concatenateStrings("Failed to process the entity - ",
							employeeObject.toString()));
				}
			} else {
				markEntityInvalid(employeeObject);
			}
		}
	}

	/**
	 * Mark the entities file invalid.
	 */
	private void markEntityInvalid() {
		String newFileName;
		try {
			newFileName = StringUtil.concatenateStrings(file.getAbsolutePath(), ValidatorProperties.getInstance()
					.getProperty(PropertyKeys.INVALID_FILE_NAME_SUFFIX.toString()));
			FileUtil.renameFile(file, newFileName);
			LOG.info(StringUtil.concatenateStrings("The entity file - ", this.file.getName(), " was marked invalid."));
		} catch (IllegalAccessException e) {
			LOG.error(StringUtil.concatenateStrings(
					"Failed to mark entity file invalid - Insufficient rights on file ", file.getName()), e);
		}
	}

	/**
	 * Post processing of the entity file.
	 * 
	 * @param T
	 *            entity
	 * @throws IOException
	 */
	private void postEntityFileProcessing(Employee employee) throws IOException {
		StringWriter stringWriter = null;
		String newFileName;
		try {
			stringWriter = new StringWriter();
			xmlManager.marshal(employee, stringWriter);

			newFileName = StringUtil.concatenateStrings(employee.getEntityFileName(), ValidatorProperties.getInstance()
					.getProperty(PropertyKeys.PROCESSED_FILE_NAME_SUFFIX.toString()));
			FileUtil.createFile(newFileName, file.getPath(), stringWriter.toString());
		} catch (IOException e) {
			LOG.error(StringUtil.concatenateStrings("Failed to write to file - ", file.getPath(),
					CommonUtil.getFileSeparator(), employee.getEntityFileName(), ", content - ",
					((stringWriter != null) ? stringWriter.toString() : "")), e);
			throw e;
		}
	}

	@Override
	public void markEntityInvalid(ValidableEntity validableEntity) {
		Employee employee = (Employee) validableEntity;
		StringWriter stringWriter = null;
		try {
			stringWriter = new StringWriter();
			xmlManager.marshal(employee, stringWriter);
			FileUtil.createFile(StringUtil.concatenateStrings(employee.getEntityFileName(), ValidatorProperties
					.getInstance().getProperty(PropertyKeys.INVALID_FILE_NAME_SUFFIX.toString())), file.getPath(),
					stringWriter.toString());
		} catch (IOException e) {
			LOG.error(StringUtil.concatenateStrings("Failed to write to file - ", file.getPath(),
					CommonUtil.getFileSeparator(), employee.getEntityFileName(), ", content - ",
					((stringWriter != null) ? stringWriter.toString() : "")), e);
		}
	}

	@Override
	public void run() {
		processEntities();
	}
}
