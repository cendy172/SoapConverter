package com.liqing.service;

import com.liqing.domain.StudentsListResponse;
import com.liqing.service.soap.RequestBuilder;
import com.liqing.service.soap.ServiceResponseReader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class SoapService
{

	public StudentsListResponse createStudent()
	{
		final InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("students.xml");
		try
		{
			final String students = IOUtils.toString(resourceAsStream, "UTF-8");
			return new ServiceResponseReader().getResult(students, StudentsListResponse.class);
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}

	}

	public String getStudentsXml()
	{
		final StudentsListResponse studentsList = createStudent();
		try
		{
			return RequestBuilder.getStudentsRequest(studentsList);
		} catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}
}
