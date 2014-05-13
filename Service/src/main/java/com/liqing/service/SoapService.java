package com.liqing.service;

import com.liqing.domain.StudentsListResponse;
import com.liqing.service.moco.MocoEndPointService;
import com.liqing.service.soap.RequestBuilder;
import com.liqing.service.soap.ServiceResponseReader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class SoapService {
    private MocoEndPointService mocoEndPointService;
    private RequestBuilder requestBuilder;

    public SoapService(MocoEndPointService mocoEndPointService, RequestBuilder requestBuilder) {
        this.mocoEndPointService = mocoEndPointService;
        this.requestBuilder = requestBuilder;
    }

    public SoapService() {
        this(new MocoEndPointService(), new RequestBuilder());
    }

    public StudentsListResponse getStudents() {
        final InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("response/students.xml");

        String response = mocoEndPointService.send(requestBuilder.getStudentsRequest());
        try {
            final String students = IOUtils.toString(resourceAsStream, "UTF-8");
            return new ServiceResponseReader().getResult(response, StudentsListResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getStudentsXml() {
        final StudentsListResponse studentsList = getStudents();
        try {
            return RequestBuilder.getStudentsRequest(studentsList);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
