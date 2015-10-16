package pl.gebickionline.restclient;

import java.nio.charset.Charset;

class ResponseImpl implements Response {
    public static final String CHARSET_NAME = "UTF-8";
    private final int statusCode;
    private final byte[] responseBody;

    public ResponseImpl(int statusCode, byte[] responseBody) {
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
}
