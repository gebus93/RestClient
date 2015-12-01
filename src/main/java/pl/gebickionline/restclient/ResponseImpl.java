package pl.gebickionline.restclient;

import java.nio.charset.Charset;
import java.util.Map;

class ResponseImpl implements Response {
    public static final String CHARSET_NAME = "UTF-8";
    private Map<String, String> headers;
    private final int statusCode;
    private final byte[] responseBody;

    public ResponseImpl(Map<String, String> headers, int statusCode, byte[] responseBody) {
        this.headers = headers;
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    @Override
    public int statusCode() {
        return statusCode;
    }

    @Override
    public String asString() {
        return responseBody == null
                ? ""
                : new String(responseBody, Charset.forName(CHARSET_NAME));
    }

    @Override
    public byte[] asByteArray() {
        return responseBody;
    }

    @Override
    public ObjectNode asJsonObject() {
        return responseBody == null
                ? null
                : new JsonObjectNode(asString());
    }

    @Override
    public ObjectNodeList asList() {
        return responseBody == null
                ? null
                : new JsonObjectNodeList(asString());
    }

    @Override
    public String header(String key) {
        return headers.get(key);
    }
}
