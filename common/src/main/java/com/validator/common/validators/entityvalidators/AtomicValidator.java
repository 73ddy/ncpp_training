package com.validator.common.validators.entityvalidators;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.text.WordUtils;

import com.validator.common.operators.Operator;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class AtomicValidator<T> implements Validator<T> {

	private static final String GET_METHOD_PREFIX = "get";
	private static final String IS_METHOD_PREFIX = "is";

	@XmlElement(name = "Field")
	private String field;
	@XmlElement(name = "Value")
	private String value;
	@XmlAnyElement(lax = true)
	private Operator operator = null;

	public boolean validate(T t) {
		Class classType = t.getClass();
		Class fieldType = isValidField(classType);

		String accessorMethod = null;
		Object actualValue = null;
		Object validatorValue = null;
		if (fieldType.toString() == Boolean.class.getSimpleName()) {
			accessorMethod = new StringBuilder(IS_METHOD_PREFIX).append(field)
					.toString();
		} else {
			accessorMethod = new StringBuilder(GET_METHOD_PREFIX).append(WordUtils.capitalize(field))
					.toString();
		}
		if (fieldType == Integer.class) {
			validatorValue = Integer.parseInt(value);
		} else if (fieldType == Boolean.class) {
			validatorValue = Boolean.valueOf(value);
		} else if (fieldType == String.class) {
			validatorValue = value;
		}
		
		actualValue = fetchFieldValue(t, accessorMethod, actualValue);

		
		return operator.operate(validatorValue, actualValue);
	}

	private Object fetchFieldValue(T t, String accessorMethod,
			Object actualValue) {
		try {
			actualValue = t.getClass().getMethod(accessorMethod).invoke(t);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return actualValue;
	}

	private Class isValidField(Class entityClass) {
		String fieldName = null;
		Class fieldType = null;
		boolean isValidFieldName = false;
		for (Field field : entityClass.getDeclaredFields()) {
			fieldName = field.getName();
			/*Annotation[] annotations = field.getDeclaredAnnotations();*/

			if (fieldName.equals(this.field)) {
				isValidFieldName = true;
				fieldType = field.getType();
				break;
			}
		}
		if (!isValidFieldName) {
			throw new IllegalArgumentException(
					"The field specified doesn't exist.");
		}
		return fieldType;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
