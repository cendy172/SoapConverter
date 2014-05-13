package com.liqing.service;

import com.liqing.domain.StudentsListResponse;
import com.liqing.service.moco.MocoEndPointService;
import com.liqing.service.soap.RequestBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SoapServiceTest
{

	private SoapService soapService;

	@Before
	public void setUp()
	{
		soapService = new SoapService(new MocoEndPointService(), new RequestBuilder());
	}

	@Test
	public void shouldCreateStudentInstance()
	{
		StudentsListResponse student = soapService.getStudents();

		assertThat(student.getResponseHeader().getResponseCreatedTime(), is("1/5/2014"));
		assertThat(student.getResponseHeader().getOperationStatus(), is("SUCCESS"));
		assertThat(student.getStudents().size(), is(2));
		assertThat(student.getStudents().get(0).getId(), is(1));
		assertThat(student.getStudents().get(0).getName(), is("john"));
		assertThat(student.getStudents().get(0).getAge(), is(12));
	}

	@Test
	public void shouldGetXmlFromObject()
	{
		String xml = "<listStudentsResp>" + "<responseHeader>" + "<responseCreatedTime>1/5/2014</responseCreatedTime>"
				+ "<operationStatus>SUCCESS</operationStatus>" + "</responseHeader>" + "<students>"
				+ "<student id=\"1\">" + "<name>john</name>" + "<age>12</age>" + "</student>" + "<student id=\"2\">"
				+ "<name>smith</name>" + "<age>13</age>" + "</student>" + "</students>" + "</listStudentsResp>";
		final String studentsXml = soapService.getStudentsXml();

		assertThat(studentsXml, is(xml));
	}
}
