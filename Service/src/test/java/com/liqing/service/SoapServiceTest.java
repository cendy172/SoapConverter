package com.liqing.service;

import com.liqing.domain.StudentsListResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SoapServiceTest {

    private SoapService soapService;

    @Before
    public void setUp() {
        soapService = new SoapService();
    }

    @Test
    public void shouldCreateStudentInstance() {
        StudentsListResponse student = soapService.createStudent();

        assertThat(student.getResponseHeader().getResponseCreatedTime(), is(""));
        assertThat(student.getResponseHeader().getOperationStatus(), is("SUCCESS"));
        assertThat(student.getStudents().size(), is(2));
        assertThat(student.getStudents().get(0).getName(), is("john"));
        assertThat(student.getStudents().get(0).getAge(), is("12"));

    }
}
