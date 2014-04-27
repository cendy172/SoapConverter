package com.liqing.service;

import com.liqing.domain.Student;
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
        Student student = soapService.createStudent();

        assertThat(student.getId(), is(1));

    }
}
