package com.liqing.service.soap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class ServiceResponseReader
{

	private static final String XMLNS_URN = "xmlns=\"urn:liqing:schema:com:student\"";

	public <T> T getResult(String xml, Class<T> type)
	{
		final String soapBody = getSoapBody(xml);
		T instance = getInstance(type, soapBody);
		return instance;
	}

	private <T> T getInstance(Class<T> type, String soapBody)
	{
		T instance = null;
		final StringReader stringReader = new StringReader(soapBody.trim());
		try
		{
			JAXBContext jc = JAXBContext.newInstance(type);
			final Unmarshaller u = jc.createUnmarshaller();
			instance = (T) u.unmarshal(stringReader);

		} catch (JAXBException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			if (stringReader != null)
			{
				stringReader.close();
			}
		}
		return instance;
	}

	private String getSoapBody(String xml)

	{
		try
		{
			SOAPMessage message = getSoapMessage(xml);
			Node firstElement = getFirstElement(message);
			return toXML(firstElement);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	private String toXML(Node firstElement) throws TransformerException
	{
		final Transformer transformer = TransformerFactory.newInstance().newTransformer();
		final StringWriter stringWriter = new StringWriter();

		transformer.transform(new DOMSource(firstElement), new StreamResult(stringWriter));
		String string = stringWriter.toString();
		return string.replaceAll(XMLNS_URN, "");
	}

	private Node getFirstElement(SOAPMessage message) throws SOAPException
	{
		final NodeList childNodes = message.getSOAPBody().getChildNodes();
		Node firstElement = null;
		for (int i = 0; i < childNodes.getLength(); i++)
		{
			if (childNodes.item(i) instanceof Element)
			{
				firstElement = childNodes.item(i);
				break;
			}
		}
		return firstElement;
	}

	private SOAPMessage getSoapMessage(String xml) throws SOAPException, IOException
	{
		MessageFactory messageFactory = MessageFactory.newInstance();
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		return messageFactory.createMessage(new MimeHeaders(), byteArrayInputStream);
	}

}
