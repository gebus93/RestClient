package pl.gebickionline.restclient;

import org.json.JSONObject;

public class JsonObjectNode implements ObjectNode {
    private final JSONObject jsonObject;

    public JsonObjectNode(String jsonString) {
        this.jsonObject = isNotBlank(jsonString)
                ? new JSONObject()
                : new JSONObject(jsonString);
    }

    @Override
    public String getString(String fieldName) {
        return jsonObject.isNull(fieldName)
                ? null
                : jsonObject.getString(fieldName);
    }

    @Override
    public Integer getInt(String fieldName) {
        return jsonObject.isNull(fieldName)
                ? null
                : jsonObject.getInt(fieldName);
    }

    private boolean isNotBlank(String jsonString) {
        return jsonString == null || jsonString.trim().isEmpty();
    }
}
