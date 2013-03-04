package com.validator.common.exceptions;

/**
 * XMLTransformationException occurs whenever something goes wrong in the
 * XML transformations.
 * 
 * @author gaurav1935
 */
public class XmlTransformationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public XmlTransformationException(String message) {
		super(message);
	}

	public XmlTransformationException(String message, Throwable cause) {
		super(message, cause);
	}
}
