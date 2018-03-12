package pro.lgebicki.restclient.api;


public interface ObjectNode {
    String getString(String fieldName);

    Integer getInt(String fieldName);

    Long getLong(String fieldName);

    Double getDouble(String fieldName);

    Boolean getBoolean(String fieldName);

    ObjectNode getObjectNode(String fieldName);

    ObjectNodeList getList(String array);
}
