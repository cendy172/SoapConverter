package com.liqing.service.moco;

import com.github.dreamhead.moco.HttpServer;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import static com.github.dreamhead.moco.Moco.eq;
import static com.github.dreamhead.moco.Moco.httpserver;
import static com.github.dreamhead.moco.Moco.text;
import static com.github.dreamhead.moco.Moco.xpath;
import static com.github.dreamhead.moco.Runner.running;
import static org.apache.http.entity.ContentType.create;

public class MocoEndPointService {

    private static HttpServer server = httpserver(9090);

    public MocoEndPointService() {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("response/students.xml");
        String file = "";
        try {
            file = IOUtils.toString(resourceAsStream, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.request(eq(xpath("//getStudents/filterType/text()"), "students")).response(text(file));
    }

    public String send(final String request) {
        final String[] responseBody = new String[1];

        try {
            running(server, new com.github.dreamhead.moco.Runnable() {

                @Override
                public void run() throws Exception {
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    HttpPost httpPost = new HttpPost("http://localhost:9090");
                    httpPost.setEntity(new StringEntity(request, create("text/xml", "UTF-8")));
                    CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
                    HttpEntity entity = httpResponse.getEntity();
                    responseBody[0] = EntityUtils.toString(entity);
                    httpResponse.close();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return responseBody[0];
    }

}
