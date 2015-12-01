package pl.gebickionline.restclient;

public interface Response {
    int statusCode();

    String asString();

    byte[] asByteArray();

    ObjectNode asJsonObject();

    ObjectNodeList asList();

    String header(String key);
}
