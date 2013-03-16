package com.validator.monitor.exceptions;

import java.io.IOException;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import com.google.inject.Injector;
import com.validator.monitor.entities.Employee;
import com.validator.monitor.notifiers.FileOperationMask;
import com.validator.monitor.watchers.Watchers;

public class Main {
	static Injector injector;

	public static void main(String[] args) throws InterruptedException, IOException, PropertyException, JAXBException {

		String path = "F:\\TargetFolder";// System.getProperty("user.dir");
		boolean watchSubtree = false;
		//injector = GuiceInjector.getInjector();
		
		FileOperationMask fileOperationMask = injector.getInstance(FileOperationMask.class);
		
		Watchers watchers = injector.getInstance(Watchers.class);
		watchers.addWatcher(path, fileOperationMask.getFileCreatedMask(),watchSubtree);
		System.out.println(watchers.getRegisteredWatchers().size());
		while (true) {
			Thread.sleep(100000);
		}
		

		//marshal();
	}

	private static void marshal() throws JAXBException, PropertyException {
		/*
		 * AtomicValidator<Employee> validator1 = new
		 * AtomicValidator<Employee>(); validator1.setField("field1");
		 * validator1.setValue("3"); validator1.setOperator(new
		 * UnaryLogicalNotOperator<Boolean>());
		 * 
		 * AtomicValidator<Employee> validator2 = new
		 * AtomicValidator<Employee>(); validator2.setField("field2");
		 * validator2.setValue("4"); validator2.setOperator(new
		 * UnaryLogicalNotOperator<Boolean>());
		 * 
		 * BinaryValidator<Employee> binaryValidator = new
		 * BinaryValidator<Employee>(validator1, validator2, new
		 * BinaryLogicalAndOperator<Boolean>());
		 */

		Employee employee = new Employee();
		employee.setId(2);
		employee.setName("gaurav");
		employee.setDateOfJoining(new Date());

		JAXBContext context = JAXBContext.newInstance(Employee.class/*
																	 * ,
																	 * Validator
																	 * .class,
																	 * BinaryValidator
																	 * .class,
																	 * AtomicValidator
																	 * .class,
																	 * BinaryLogicalAndOperator
																	 * .class,
																	 * UnaryLogicalNotOperator
																	 * .class
																	 */);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		m.marshal(employee, System.out);
	}

}
