package pl.gebickionline.restclient;

import org.json.JSONObject;

public class JsonObjectNode implements ObjectNode {
    private final JSONObject jsonObject;

    public JsonObjectNode(String jsonString) {
        this.jsonObject = isEmpty(jsonString)
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

    @Override
    public Long getLong(String fieldName) {
        return jsonObject.isNull(fieldName)
                ? null
                : jsonObject.getLong(fieldName);
    }

    @Override
    public Double getDouble(String fieldName) {
        return jsonObject.isNull(fieldName)
                ? null
                : jsonObject.getDouble(fieldName);
    }

    @Override
    public Boolean getBoolean(String fieldName) {
        return jsonObject.isNull(fieldName)
                ? null
                : jsonObject.getBoolean(fieldName);
    }

    @Override
    public ObjectNode getObjectNode(String fieldName) {
        return jsonObject.isNull(fieldName)
                ? null
                : new JsonObjectNode(getString(fieldName));
    }

    @Override
    public ObjectNodeList asList(String fieldName) {
        return jsonObject.isNull(fieldName)
                ? null
                : new JsonObjectNodeList(jsonObject.getJSONArray(fieldName).toString());
    }

    private boolean isEmpty(String jsonString) {
        return jsonString == null || jsonString.trim().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonObjectNode that = (JsonObjectNode) o;

        return !(jsonObject != null ? !jsonObject.similar(that.jsonObject) : that.jsonObject != null);

    }

    @Override
    public int hashCode() {
        return jsonObject != null ? jsonObject.hashCode() : 0;
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }
}
