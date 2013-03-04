package com.validator.common.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.validator.common.exceptions.XmlTransformationException;

/**
 * Pool of the marshallers used for XML Transformations
 * 
 * @author gaurav1935
 */
@Singleton
public final class MarshallerPool extends ObjectPool<Marshaller> {

	private final JAXBContext context;

	/**
	 * Constructor called by Guice.
	 * 
	 * @param context
	 *            JAXBContext to use for marshalling.
	 */
	@Inject
	public MarshallerPool(@Named("requestContext") JAXBContext context) {
		this.context = context;
	}

	/**
	 * Creates a new Marshaller object for the pool.
	 */
	@Override
	protected Marshaller create() {
		try {
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			return marshaller;
		} catch (JAXBException e) {
			throw new XmlTransformationException("Failed to create jaxb marshaller", e);
		}
	}
}
