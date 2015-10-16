package pl.gebickionline.restclient;

public interface Response {
    int statusCode();

    String asString();
}
