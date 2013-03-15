package com.validator.common.validators.generalvalidators;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import com.validator.common.operators.binary.arithmetic.BinaryEqualsOperator;
import com.validator.common.operators.binary.arithmetic.BinaryGreaterThanOperator;
import com.validator.common.operators.binary.logical.BinaryLogicalAndOperator;
import com.validator.common.operators.unary.logical.UnaryLogicalNotOperator;
import com.validator.common.validators.entityvalidators.AtomicValidator;
import com.validator.common.validators.entityvalidators.BinaryValidator;

public class Main_ToBeRemoved {

	/**
	 * @param args
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		//marshal();
		unmarshal();
	}

	private static void unmarshal() throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(AtomicValidator.class, UnaryLogicalNotOperator.class,
				BinaryLogicalAndOperator.class, BinaryEqualsOperator.class, BinaryValidator.class);
		System.out.println("Output from our XML File: ");
		Unmarshaller um = context.createUnmarshaller();
		BinaryValidator binValidator = (BinaryValidator) um.unmarshal(new FileReader("sampleinput1.xml"));
		Employee_ToBeRemoved emp = new Employee_ToBeRemoved();
		emp.setId(3);
		emp.setName("gaurav");
		//emp.setDateOfJoining(new Date());
		System.out.println(binValidator.validate(emp));
	}

	private static void marshal() throws JAXBException, PropertyException {
		/*AtomicValidator<Employee_ToBeRemoved> validator1 = new AtomicValidator<Employee_ToBeRemoved>();
		validator1.setField("id");
		validator1.setValue("3");
		validator1.setOperator(new UnaryLogicalNotOperator<Boolean>());

		AtomicValidator<Employee_ToBeRemoved> validator2 = new AtomicValidator<Employee_ToBeRemoved>();
		validator2.setField("id");
		validator2.setValue("3");
		validator2.setOperator(new BinaryGreaterThanOperator<Employee_ToBeRemoved>());

		BinaryValidator<Employee_ToBeRemoved> validator3 = new BinaryValidator<Employee_ToBeRemoved>(validator1,
				validator2, new BinaryEqualsOperator<Employee_ToBeRemoved>());

		BinaryValidator<Employee_ToBeRemoved> binaryValidator = new BinaryValidator<Employee_ToBeRemoved>(validator3,
				validator2, new BinaryLogicalAndOperator<Boolean>());

		JAXBContext context = JAXBContext.newInstance(AtomicValidator.class, BinaryValidator.class,
				UnaryLogicalNotOperator.class, BinaryLogicalAndOperator.class, BinaryGreaterThanOperator.class, BinaryEqualsOperator.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		m.marshal(binaryValidator, System.out);*/

	}

}
