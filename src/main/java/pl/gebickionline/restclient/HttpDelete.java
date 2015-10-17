package pl.gebickionline.restclient;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

public class HttpDelete extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpDelete(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    public HttpDelete(final URI uri) {
        super();
        setURI(uri);
    }

    public HttpDelete() {
        super();
    }
}