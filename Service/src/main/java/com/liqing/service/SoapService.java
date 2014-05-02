package com.liqing.service;

import com.liqing.domain.StudentsListResponse;
import com.liqing.service.soap.RequestBuilder;
import com.liqing.service.soap.ServiceResponseReader;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class SoapService
{

	public StudentsListResponse createStudent()
	{

		final InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("students.xml");

		String xml = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+ "    <SOAP-ENV:Body>" + "        <listStudentsResp xmlns=\"urn:liqing:schema:com:student\">"
				+ "            <responseHeader>" + "                <responseCreatedTime>1/5/2014</responseCreatedTime>"
				+ "                <operationStatus>SUCCESS</operationStatus>" + "            </responseHeader>"
				+ "            <students>" + "                <student id=\"1\">"
				+ "                    <name>john</name>" + "                    <age>12</age>"
				+ "                </student>" + "                <student id=\"2\">"
				+ "                    <name>smith</name>" + "                    <age>13</age>"
				+ "                </student>" + "            </students>" + "        </listStudentsResp>"
				+ "    </SOAP-ENV:Body>" + "</SOAP-ENV:Envelope>";
		return new ServiceResponseReader().getResult(xml, StudentsListResponse.class);
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
