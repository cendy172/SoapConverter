package com.liqing.domain;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "listStudentsResp")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentsListResponse
{

	private ResponseHeader responseHeader;

	@XmlElement(name = "student")
	@XmlElementWrapper(name = "students")
	private List<Student> students;

	public List<Student> getStudents()
	{
		return students;
	}

	public void setStudents(List<Student> students)
	{
		this.students = students;
	}

	public ResponseHeader getResponseHeader()
	{
		return responseHeader;
	}

	public void setResponseHeader(ResponseHeader responseHeader)
	{
		this.responseHeader = responseHeader;
	}
}
