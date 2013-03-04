package com.validator.common.xml;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.validator.common.exceptions.XmlTransformationException;

/**
 * XML Manager is responsible for XML transformations.
 * 
 * @author gaurav1935
 */
@Singleton
public class XmlManager {
	private static final Logger LOG = Logger.getLogger(XmlManager.class);

	@Inject
	private MarshallerPool marshallerPool;

	@Inject
	private UnmarshallerPool unmarshallerPool;

	/**
	 * Marshalls an object into a StringWriter object containing xml.
	 * 
	 * @param message
	 *            Cbject to marshall to xml.
	 * @param writer
	 *            Contains the resulting xml when method has executed.
	 */
	public void marshal(Object message, StringWriter writer) {
		long start = System.currentTimeMillis();
		Marshaller marshaller = marshallerPool.borrow();
		try {
			marshaller.marshal(message, writer);
		} catch (JAXBException e) {
			throw new XmlTransformationException("Failed to marshal xml", e);
		} finally {
			marshallerPool.release(marshaller);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("MARSHAL: " + (System.currentTimeMillis() - start) + " ms");
		}
	}

	/**
	 * Unmarshals a stream of xml into an Object.
	 * 
	 * @param xml
	 *            Stream to parse.
	 * @return Unmarshalled object.
	 */
	public Object unmarshal(InputStream xml) {
		long start = System.currentTimeMillis();
		Unmarshaller unmarshaller = unmarshallerPool.borrow();
		try {
			BufferedInputStream bufXml = new BufferedInputStream(xml);
			SAXSource saxSource = new SAXSource(createNewXmlReader(), new InputSource(bufXml));
			return unmarshaller.unmarshal(saxSource);
		} catch (JAXBException e) {
			throw new XmlTransformationException("Failed to unmarshal xml", e);
		} finally {
			unmarshallerPool.release(unmarshaller);
			if (LOG.isDebugEnabled()) {
				LOG.debug("UNMARSHAL: " + (System.currentTimeMillis() - start) + " ms");
			}
		}
	}

	/**
	 * Convenience method to create an XML reader that is configured to not
	 * validate DTD files.
	 * 
	 * @return XML reader to be used with SAXSource.
	 */
	private XMLReader createNewXmlReader() {
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			parserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false); // Disable
																												// DTD
																												// validation
			parserFactory.setNamespaceAware(true);
			SAXParser saxParser = parserFactory.newSAXParser();
			return saxParser.getXMLReader();
		} catch (ParserConfigurationException e) {
			throw new XmlTransformationException("Failed to unmarshal xml", e);
		} catch (SAXException e) {
			throw new XmlTransformationException("Failed to unmarshal xml", e);
		}
	}
}
