package pl.gebickionline.restclient;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class RestClient {
    public static final String CHARSET_NAME = "UTF-8";
    private final HttpRequestBase request;
    private byte[] body;

    private RestClient(HttpRequestBase request) {
        this.request = request;
        this.request.addHeader("accept", "text/plain");
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
        HttpResponse response = executeRequest();
        int statusCode = response.getStatusLine().getStatusCode();
        byte[] responseBody = responseBodyOf(response.getEntity());
        return new ResponseImpl(statusCode, responseBody);
    }

    private byte[] responseBodyOf(HttpEntity entity) {
        try {
            return EntityUtils.toByteArray(entity);
        } catch (IOException e) {
            throw new ResponseConversionException(e);
        }
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

    private class ResponseConversionException extends RuntimeException {
        public ResponseConversionException(Exception e) {
            super(e);
        }
    }
}
