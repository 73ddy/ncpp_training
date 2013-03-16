package com.validator.executor.binding;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Guice Injector class for initializing the google guice bindings.
 * 
 * @author gaurav1935
 */
public class GuiceInjector {
	private static Injector injector;

	static {
		injector = Guice.createInjector(new MonitorValidatorModule());
	}

	public static Injector getInjector() {
		return injector;
	}

	private GuiceInjector() {

	}
}
