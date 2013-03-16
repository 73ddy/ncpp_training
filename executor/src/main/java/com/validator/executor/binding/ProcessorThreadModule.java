package com.validator.executor.binding;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.validator.monitor.xml.XmlManager;

/**
 * Google Guice module for bindings and initialization of objects.
 * 
 * @author gaurav1935
 */
public class ProcessorThreadModule extends AbstractModule {

	@Override
	protected void configure() {
		binder().bind(XmlManager.class).asEagerSingleton();
		
	}

}
