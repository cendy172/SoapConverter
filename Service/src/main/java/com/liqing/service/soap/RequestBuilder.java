package com.liqing.service.soap;

import com.google.common.collect.ImmutableMap;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class RequestBuilder {

    private Configuration configuration;

    public RequestBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public RequestBuilder() {
        configuration = new Configuration();
    }

    public String getStudentsRequest() {
        ImmutableMap<String, String> data = ImmutableMap.<String, String>builder().put("username", "liqing").put("password", "liqing").build();
        try {
            configuration.setClassForTemplateLoading(RequestBuilder.class, "/template");
            Template template = configuration.getTemplate("get-students-request.ftl");
            StringWriter stringWriter = new StringWriter();
            template.process(data, stringWriter);
            return stringWriter.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EMPTY;
    }

    public static <T> String getStudentsRequest(T object) throws UnsupportedEncodingException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(object, byteArrayOutputStream);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
    }
}
