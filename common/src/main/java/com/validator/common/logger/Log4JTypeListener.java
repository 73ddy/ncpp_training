package com.validator.common.logger;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

public class Log4JTypeListener implements TypeListener {
	public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
		for (Class<?> c = typeLiteral.getRawType(); c != Object.class; c = c.getSuperclass()) {
			for (Field field : c.getDeclaredFields()) {
				if (field.getType() == Logger.class && field.isAnnotationPresent(InjectLogger.class)) {
					typeEncounter.register(new Log4JMembersInjector<T>(field));
				}
			}
		}
	}
}