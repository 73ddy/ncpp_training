package com.validator.executor.binding;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.validator.common.operators.binary.arithmetic.BinaryEqualsOperator;
import com.validator.common.operators.binary.arithmetic.BinaryGreaterThanOperator;
import com.validator.common.operators.binary.logical.BinaryLogicalAndOperator;
import com.validator.common.operators.binary.logical.BinaryLogicalOrOperator;
import com.validator.common.operators.unary.logical.UnaryLogicalNotOperator;
import com.validator.common.validators.entityvalidators.AtomicValidator;
import com.validator.common.validators.entityvalidators.UnaryValidator;
import com.validator.executor.threadexecutor.PoolExecutor;
import com.validator.monitor.entities.Employee;
import com.validator.monitor.notifiers.FileOperationMask;
import com.validator.monitor.notifiers.Notifier;
import com.validator.monitor.notifiers.impl.JNotifier;
import com.validator.monitor.notifiers.impl.JNotifierFileOperationMask;
import com.validator.monitor.notifiers.impl.ValidatorJNotifyListener;
import com.validator.monitor.watchers.Watchers;
import com.validator.monitor.xml.XmlManager;

/**
 * Google Guice module for bindings and initialization of objects.
 * 
 * @author gaurav1935
 */
public class ExecutorValidatorModule implements Module {

	// Array of classes to be bound with JAXB context
	/*@SuppressWarnings("rawtypes")
	Class[] clazzezToBeBound = { BinaryEqualsOperator.class, BinaryGreaterThanOperator.class,
			BinaryLogicalAndOperator.class, BinaryLogicalOrOperator.class, UnaryLogicalNotOperator.class,
			AtomicValidator.class, UnaryValidator.class, Employee.class };*/

	public void configure(Binder binder) {
		// Singleton Implementations
		/*binder.bind(ValidatorJNotifyListener.class).asEagerSingleton();
		binder.bind(Watchers.class).asEagerSingleton();
		binder.bind(JNotifier.class).asEagerSingleton();
		binder.bind(XmlManager.class).asEagerSingleton();*/
		binder.bind(PoolExecutor.class).asEagerSingleton();
		

		// Interface Implementations
		/*binder.bind(Notifier.class).to(JNotifier.class);
		binder.bind(FileOperationMask.class).to(JNotifierFileOperationMask.class);*/
	}

	/*@Provides
	@Named("marshalContext")
	public JAXBContext getMarshalContext() throws JAXBException {
		return JAXBContext.newInstance(clazzezToBeBound);
	}

	@Provides
	@Named("unmarshalContext")
	public JAXBContext getUnmarshalContext() throws JAXBException {
		return JAXBContext.newInstance(clazzezToBeBound);
	}*/
}
