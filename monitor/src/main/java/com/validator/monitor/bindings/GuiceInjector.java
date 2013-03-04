package com.validator.monitor.bindings;

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
		injector = Guice.createInjector(new ValidatorModule());
	}

	public static Injector getInjector() {
		return injector;
	}

	private GuiceInjector() {

	}
}
