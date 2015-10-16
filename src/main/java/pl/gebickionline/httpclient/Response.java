package pl.gebickionline.httpclient;

/**
 * Created by £ukasz on 2015-10-16.
 */
public interface Response {
    int statusCode();

    String asString();
}
