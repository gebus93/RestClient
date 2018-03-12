package pro.lgebicki.restclient.api;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import pro.lgebicki.restclient.ExecuteRequestException;
import pro.lgebicki.restclient.HttpDelete;
import pro.lgebicki.restclient.ResponseConversionException;
import pro.lgebicki.restclient.ResponseImpl;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class RestClient {
    private static final String CHARSET_NAME = "UTF-8";
    private final HttpRequestBase request;
    private byte[] body;
    private Map<String, String> headers;

    private RestClient(HttpRequestBase request) {
        this.request = request;
        this.headers = new HashMap<>();
        this.headers.put("accept", "*/*");
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

    public RestClient header(String name, String... values) {
        this.headers.put(name, String.join(",", values));
        return this;
    }

    public Response send() {
        HttpResponse response = executeRequest();
        int statusCode = response.getStatusLine().getStatusCode();
        byte[] responseBody = responseBodyOf(response.getEntity());

        Map<String, String> headers = listOf(response.getAllHeaders())
                .stream()
                .collect(Collectors.toMap(Header::getName, Header::getValue, String::concat));

        return new ResponseImpl(headers, statusCode, responseBody);
    }

    private List<Header> listOf(Header[] allHeaders) {
        if (allHeaders == null)
            return Collections.emptyList();
        return Arrays.asList(allHeaders);
    }

    private byte[] responseBodyOf(HttpEntity entity) {
        if (entity == null)
            return null;
        try {
            return EntityUtils.toByteArray(entity);
        } catch (Exception e) {
            throw new ResponseConversionException(e);
        }
    }

    private HttpResponse executeRequest() {
        headers.forEach(request::addHeader);

        if (body != null)
            addBodyToRequest();
        try {
            return httpClient().execute(request);
        } catch (Exception e) {
            throw new ExecuteRequestException(e);
        }
    }

    private void addBodyToRequest() {
        if (request instanceof HttpEntityEnclosingRequestBase)
            ((HttpEntityEnclosingRequestBase) request).setEntity(new ByteArrayEntity(body));
    }

    private HttpClient httpClient() {
        return HttpClientBuilder.create().build();
    }

}
