package pro.lgebicki.restclient.api;

public interface Response {
    int statusCode();

    String asString();

    byte[] asByteArray();

    ObjectNode asJsonObject();

    ObjectNodeList asList();

    String header(String key);
}
