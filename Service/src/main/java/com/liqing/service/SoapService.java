package com.liqing.service;

import com.liqing.domain.Student;

public class SoapService {

    public Student createStudent() {
        Student student = new Student();
        student.setId(1);
        return student;
    }
}
