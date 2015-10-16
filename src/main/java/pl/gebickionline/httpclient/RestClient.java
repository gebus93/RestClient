package pl.gebickionline.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by £ukasz on 2015-10-16.
 */
public class RestClient {
    public static final String CHARSET_NAME = "UTF-8";
    private final HttpRequestBase request;
    private byte[] body;

    private RestClient(HttpRequestBase request) {
        this.request = request;
    }

    public static RestClient put(String targetURL) {
        return new RestClient(new HttpPut(targetURL));
    }

    public static RestClient get(String targetURL) {
        return new RestClient(new HttpGet(targetURL));
    }

    public static RestClient post(String targetURL) {
        return new RestClient(new HttpPost(targetURL));
    }

    public static RestClient delete(String targetURL) {
        return new RestClient(new HttpDelete(targetURL));
    }

    public RestClient body(byte[] body) {
        this.body = body;
        return this;
    }

    public RestClient body(String body) {
        this.body = body == null ? null : body.getBytes(Charset.forName(CHARSET_NAME));
        return this;
    }

    public RestClient header(String name, String value) {
        this.request.addHeader(name, value);
        return this;
    }

    public Response send() {
        System.out.printf("Request to %s\n", request.getURI().toString());
        HttpResponse response = executeRequest();
        System.out.println(response.getStatusLine().getStatusCode());

        return new Response() {
        };
    }

    private HttpResponse executeRequest() {
        try {
            return httpClient().execute(request);
        } catch (IOException e) {
            throw new ExecuteRequestException(e);
        }
    }

    private HttpClient httpClient() {
        return HttpClientBuilder.create().build();
    }

    private class ExecuteRequestException extends RuntimeException {
        public ExecuteRequestException(Exception e) {
            super(e);
        }
    }
}
