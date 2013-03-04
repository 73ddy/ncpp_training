package com.validator.common.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.validator.common.exceptions.XmlTransformationException;

/**
 * Pool of the unmarshallers used for XML Transformations
 * 
 * @author gaurav1935
 */
@Singleton
public final class UnmarshallerPool extends ObjectPool<Unmarshaller> {

    private final JAXBContext context;


    /**
     * Constructor called by Guice.
     * 
     * @param context JAXBContext to use for unmarshalling.
     */
    @Inject
    public UnmarshallerPool(@Named("responseContext") JAXBContext context) {
        this.context = context;
    }


    /**
     * Creates a new Unmarshaller object for the pool.
     */
    @Override
    protected Unmarshaller create() {
        try {
            return context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new XmlTransformationException("Failed to create jaxb unmarshaller", e);
        }
    }
}
