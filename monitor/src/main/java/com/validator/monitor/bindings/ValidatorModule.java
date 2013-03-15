package com.validator.monitor.bindings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.validator.common.xml.XmlManager;
import com.validator.monitor.entities.Employee;
import com.validator.monitor.notifiers.FileOperationMask;
import com.validator.monitor.notifiers.Notifier;
import com.validator.monitor.notifiers.impl.JNotifier;
import com.validator.monitor.notifiers.impl.JNotifierFileOperationMask;
import com.validator.monitor.notifiers.impl.ValidatorJNotifyListener;
import com.validator.monitor.watchers.Watchers;

/**
 * Google Guice module for bindings and initialization of objects.
 * 
 * @author gaurav1935
 */
public class ValidatorModule implements Module {

	public void configure(Binder binder) {
		// Singleton Implementations
		binder.bind(ValidatorJNotifyListener.class).asEagerSingleton();
		binder.bind(Watchers.class).asEagerSingleton();
		binder.bind(JNotifier.class).asEagerSingleton();
		binder.bind(XmlManager.class).asEagerSingleton();

		// Interface Implementations
		binder.bind(Notifier.class).to(JNotifier.class);
		binder.bind(FileOperationMask.class).to(JNotifierFileOperationMask.class);
	}

	@Provides
	@Named("requestContext")
	public JAXBContext getRequestContext() throws JAXBException {
		return JAXBContext.newInstance(Employee.class);
	}

	@Provides
	@Named("responseContext")
	public JAXBContext getResponseContext() throws JAXBException {
		return JAXBContext.newInstance(Employee.class);
	}
}
