package com.liqing.service.soap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class RequestBuilder
{

	public static <T> String getStudentsRequest(T object) throws UnsupportedEncodingException
	{
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try
		{
			final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
			final Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.marshal(object, byteArrayOutputStream);
		} catch (JAXBException e)
		{
			throw new RuntimeException(e);
		}

		return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
	}
}
