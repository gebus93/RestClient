package pro.lgebicki.restclient;

import org.json.JSONObject;
import pro.lgebicki.restclient.api.ObjectNode;
import pro.lgebicki.restclient.api.ObjectNodeList;

public class JsonObjectNode implements ObjectNode {
    private final JSONObject jsonObject;

    JsonObjectNode(String jsonString) {
        this.jsonObject = isEmpty(jsonString)
                ? new JSONObject()
                : new JSONObject(jsonString);
    }

    @Override
    public String getString(String fieldName) {
        if (jsonObject.isNull(fieldName))
            return null;

        if (isJsonObject(fieldName))
            return jsonObject.getJSONObject(fieldName).toString();

        if (isJsonArray(fieldName))
            return jsonObject.getJSONArray(fieldName).toString();

        return jsonObject.getString(fieldName);
    }

    private boolean isJsonArray(String fieldName) {
        try {
            jsonObject.getJSONArray(fieldName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isJsonObject(String fieldName) {
        try {
            jsonObject.getJSONObject(fieldName);
            return true;
        } catch (Exception e) {
            return false;
        }
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
    public ObjectNodeList getList(String fieldName) {
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
